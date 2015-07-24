/**
 * Copyright (c) 2015, bitium@126.com. All rights reserved.
 */
package com.bitium.event.event.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： com.jdpay.event.event.core <br>
 * <b>类名称</b>： EventHandler <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/24 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class Subscriber {
    private final EventBus eventBus;
    private final Object target;
    private final Method method;
    private final ExceptionHandler exceptinHandler;

    public Subscriber(EventBus eventBus, Object target, Method method, ExceptionHandler exceptinHandler) {
        this.eventBus = eventBus;
        this.target = target;
        this.method = method;
        this.exceptinHandler = exceptinHandler;
    }

    public void dispatchEvent(Object event) {
        try {
            try {
                method.invoke(target,event);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            exceptinHandler.handle(e,new Context(event,this,method,eventBus));
        }
    }
}
