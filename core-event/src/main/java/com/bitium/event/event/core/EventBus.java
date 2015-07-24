/**
 * Copyright (c) 2015, bitium@126.com. All rights reserved.
 */
package com.bitium.event.event.core;

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： com.jdpay.event.event.core <br>
 * <b>类名称</b>： EventBus <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/23 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class EventBus {
    //注册中心
    private final Registry registry;
    //分发中心
    private final Dispatcher dispatcher;
    //总线名称
    private final String profiler;

    public EventBus(Registry registry, Dispatcher dispatcher, String profiler) {
        this.registry = registry;
        this.dispatcher = dispatcher;
        this.profiler = profiler;
    }

    /**
     * 发布一个事件
     * @param event
     */
    void publish(Object event) {

    }

    /**
     * 订阅事件
     * @param eventHandler
     */
    void subscribe(Object eventHandler) {

    }

    /**
     * 取消订阅
     * @param eventHandler
     */
    void unSubscribe(Object eventHandler) {

    }
}
