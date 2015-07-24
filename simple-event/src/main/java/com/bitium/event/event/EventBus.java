/**
 * Copyright (c) 2015, bitium.com. All rights reserved.
 */
package com.bitium.event.event;

import com.bitium.event.event.dispatch.ThreadLocalDispatcher;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.bitium.event.event.utils.Assert.checkNotNull;

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： com.bitium.event.event <br>
 * <b>类名称</b>： EventBus <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/21 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class EventBus {
    private static final Logger logger = Logger.getLogger(EventBus.class.getName());

    private final String identifier;
    private final Executor executor;
    private final SubscriberExceptionHandler exceptionHandler;
    private final Registry subscribers = new Registry(this);
    private final Dispatcher dispatcher;

    public EventBus() {
        this("#Default#");
    }

    public EventBus(String identifier) {
        this(identifier,Executors.newFixedThreadPool(10),new LoggingHandler(),new ThreadLocalDispatcher());
    }

    public EventBus(String identifier, Executor executor, Dispatcher dispatcher) {
        this(identifier,executor,new LoggingHandler(),dispatcher);
    }

    public EventBus(String identifier, Executor executor, SubscriberExceptionHandler exceptionHandler, Dispatcher dispatcher) {
        this.identifier = checkNotNull(identifier);
        this.executor = checkNotNull(executor);
        this.exceptionHandler = checkNotNull(exceptionHandler);
        this.dispatcher = checkNotNull(dispatcher);
    }

    public void register(Object listener) {
        this.subscribers.register(listener);
    }

    public void unRegister(Object listener) {
        this.subscribers.unRegister(listener);
    }

    public void produce(Object event) {
        Iterator<Subscriber> eventSubscribers = subscribers.getSubscribers(event).iterator();
        while (eventSubscribers.hasNext()) {
            this.dispatcher.dispatch(event,eventSubscribers);
        }
    }

    public Executor executor() {
        return this.executor;
    }

    private String identifier() {
        return this.identifier;
    }

    public void handleSubscriberException(Throwable e, EventContext context) {
        checkNotNull(e);
        checkNotNull(context);
        try {
            exceptionHandler.handle(checkNotNull(e), checkNotNull(context));
        } catch (Throwable e2) {
            logger.info("");
        }
    }

    /////////////////////////////
    /////////////////////////////

    static final class LoggingHandler implements SubscriberExceptionHandler {
        static final LoggingHandler INSTANCE = new LoggingHandler();

        private static Logger logger(EventContext context) {
            return Logger.getLogger(EventBus.class.getName() + "." + context.getEventBus().identifier());
        }

        private static String message(EventContext context) {
            Method method = context.getSubscriberMethod();
            return new StringBuffer("Exception thrown by subscriber method ").append(
                    method.getName()).append('(').append(method.getParameterTypes()[0].getName()).append(
                    " on subscriber ").append(context.getSubscriber()).append(
                    " when dispatching event: ").append( context.getEvent()).toString();
        }

        @Override
        public void handle(Throwable exception, EventContext context) {
            Logger logger = logger(context);
            if (logger.isLoggable(Level.SEVERE)) {
                logger.log(Level.SEVERE, message(context), exception);
            }
        }
    }

}
