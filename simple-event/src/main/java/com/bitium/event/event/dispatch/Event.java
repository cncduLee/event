/**
 * Copyright (c) 2015, bitium.com. All rights reserved.
 */
package com.bitium.event.event.dispatch;


import com.bitium.event.event.Subscriber;

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： com.bitium.event.event.dispatch <br>
 * <b>类名称</b>： EventWithSubscriber <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/21 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
final class Event {
    final Object event;
    final Subscriber subscriber;

    Event(Object event, Subscriber subscriber) {
        this.event = event;
        this.subscriber = subscriber;
    }
}
