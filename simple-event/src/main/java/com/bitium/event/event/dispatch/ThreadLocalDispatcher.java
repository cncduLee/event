/**
 * Copyright (c) 2015, bitium.com. All rights reserved.
 */
package com.bitium.event.event.dispatch;

import com.bitium.event.event.Dispatcher;
import com.bitium.event.event.Subscriber;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： com.bitium.event.event <br>
 * <b>类名称</b>： ThreadDispatcher <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/21 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ThreadLocalDispatcher implements Dispatcher {
    private final ThreadLocal<Queue<EventSet>> queue = new ThreadLocal<Queue<EventSet>>() {
        @Override
        protected Queue<EventSet> initialValue() {
            return new ArrayDeque<EventSet>();
        }
    };
    private final ThreadLocal<Boolean> dispatching = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    @Override
    public void dispatch(Object event, Iterator<Subscriber> subscribers) {
        if(null == event || subscribers == null) {
            throw new NullPointerException();
        }
        Queue<EventSet> queueForThread = queue.get();//当前线程的消息队列
        queueForThread.offer(new EventSet(event, subscribers));//插入一则消息

        if(!dispatching.get()) {
            dispatching.set(true);//设为处理中
            try {
                EventSet nextEvent;
                while ((nextEvent = queueForThread.poll()) != null) {
                    while (nextEvent.subscribers.hasNext()) {
                        nextEvent.subscribers.next().dispatchEvent(nextEvent.event);
                    }
                }
            } finally {
                dispatching.remove();
                queue.remove();
            }
        }
    }
}
