/**
 * Copyright (c) 2015, bitium.com. All rights reserved.
 */
package com.bitium.event.event;

import com.bitium.event.event.anno.SafeEvents;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import static com.bitium.event.event.utils.Assert.checkNotNull;

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： com.bitium.event.event <br>
 * <b>类名称</b>： Subscriber <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/21 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class Subscriber {
    private EventBus bus;
    private final Object target;
    private final Method method;
    private final Executor executor;

    public Subscriber(EventBus bus, Object target, Method method) {
        this.bus = bus;
        this.target = target;
        this.method = method;
        method.setAccessible(true);

        this.executor = bus.executor();
    }

    public static Subscriber create(EventBus bus, Object listener, Method method) {
        return isDeclaredThreadSafe(method) ? new Subscriber(bus, listener, method) : new SynchronizedSubscriber(bus, listener, method);
    }

    public final void dispatchEvent(final Object event) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    invokeSubscriberMethod(event);
                } catch (InvocationTargetException e) {
                    bus.handleSubscriberException(e.getCause(), context(event));
                }
            }
        });
    }

    protected void invokeSubscriberMethod(Object event) throws InvocationTargetException {
        try {
            method.invoke(target, checkNotNull(event));
        } catch (IllegalArgumentException e) {
            throw new Error("Method rejected target/argument: " + event, e);
        } catch (IllegalAccessException e) {
            throw new Error("Method became inaccessible: " + event, e);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof Error) {
                throw (Error) e.getCause();
            }
            throw e;
        }
    }

    private static boolean isDeclaredThreadSafe(Method method) {
        return method.getAnnotation(SafeEvents.class) != null;
    }

    private EventContext context(Object event) {
        return new EventContext(bus, event, target, method);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof Subscriber) {
            Subscriber that = (Subscriber) obj;
            return target == that.target && method.equals(that.method);
        }
        return false;
    }

    final static class SynchronizedSubscriber extends Subscriber {
        SynchronizedSubscriber(EventBus bus, Object target, Method method) {
            super(bus, target, method);
        }

        @Override
        protected void invokeSubscriberMethod(Object event) throws InvocationTargetException {
            synchronized (this) {
                super.invokeSubscriberMethod(event);
            }
        }
    }
}
