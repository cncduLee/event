/**
 * Copyright (c) 2015, bitium@126.com. All rights reserved.
 */
package com.bitium.event.event.core;

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： com.jdpay.event.event.core <br>
 * <b>类名称</b>： EventExecutor <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/23 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class EventExecutor {

    public EventExecutor() {

    }

    /**
     * 基于类型的事件消息
     *
     * @param event
     */
    public void publish(Object event) {

    }

    /**
     * 基于topic的事件消息
     *
     * @param topic
     * @param event
     */
    public void publish(String topic,Object event) {

    }

    /**
     * 订阅消息#消息类型与eventHandler的方法注解有关#
     *
     * @param eventHandler
     */
    public void subscribe(Object eventHandler) {

    }

    /**
     * 订阅消息#消息类型与eventHandler的方法注解有关#
     *
     * @param eventHandler
     * @param eventType
     */
    public void subscribe(Object eventHandler,EventType eventType) {

    }

    /**
     * 取消订阅消息#消息类型与eventHandler的方法注解有关#
     *
     * @param eventHandler
     */
    public void unSubscribe(Object eventHandler) {

    }

    /**
     * 取消订阅消息#消息类型与eventHandler的方法注解有关#
     *
     * @param eventHandler
     * @param eventType
     */
    public void unSubscribe(Object eventHandler,EventType eventType) {

    }
}
