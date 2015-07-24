/**
 * Copyright (c) 2015, bitium.com. All rights reserved.
 */
package com.bitium.event.event;

import com.bitium.event.event.anno.Subscribe;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： com.bitium.event.event <br>
 * <b>类名称</b>： SubscriberRegistry <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/21 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
final class Registry {
    //订阅者中心
    private final ConcurrentMap<Class,CopyOnWriteArraySet<Subscriber>> subscribers = new ConcurrentHashMap<Class, CopyOnWriteArraySet<Subscriber>>();
    //事件总线
    private final EventBus bus;
    //
    private final static  ConcurrentMap<Class,List<Method>>  subscriberMethodsCache = new ConcurrentHashMap<Class, List<Method>>();

    public Registry(EventBus bus) {
        this.bus = bus;
    }

    /**
     * 添加订阅
     * @param listener
     */
    public void register(Object listener) {
        List<Method> methods = getAnnotatedMethods(listener.getClass());
        for(Method m : methods) {
            Class parameterTypes = m.getParameterTypes()[0];
            CopyOnWriteArraySet<Subscriber> subs = subscribers.get(parameterTypes);
            if(subs == null) {
                subs = new CopyOnWriteArraySet<Subscriber>();
            }
            subs.add(Subscriber.create(this.bus,listener,m));
            subscribers.put(parameterTypes,subs);
        }
    }

    /**
     * 取消订阅
     * @param listener
     */
    public void unRegister(Object listener) {
        List<Method> methods = getAnnotatedMethods(listener.getClass());
        for(Method m : methods) {
            Class parameterTypes = m.getParameterTypes()[0];
            if(!subscribers.get(parameterTypes).remove(Subscriber.create(this.bus,listener,m))) {
                throw new IllegalArgumentException("错误的监听对象，监听对象必须先注册才能执行取消注册操作！");
            }
        }
    }

    /**
     * 获取事件的所有订阅者
     * @param event
     * @return
     */
    public CopyOnWriteArraySet<Subscriber> getSubscribers(Object event) {
        return subscribers.get(event.getClass());
    }

    private List<Method> getAnnotatedMethods(Class clazz) {
        List<Method> list = subscriberMethodsCache.get(clazz);
        if(list == null || list.isEmpty()) {
            list = getAnnotatoinMethodDiret(clazz);
            subscriberMethodsCache.put(clazz,list);
        }
        return list;
    }

    private List<Method> getAnnotatoinMethodDiret(Class clazz) {
        List<Method> methods = new ArrayList<Method>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class) && !method.isSynthetic()) {
                if(method.getParameterTypes().length != 1) {
                    throw new IllegalArgumentException("监听方法有且只能有一个参数！");
                }
                methods.add(method);
            }
        }
        return methods;
    }
}
