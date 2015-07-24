/**
 * Copyright (c) 2015, jdpay.com. All rights reserved.
 */
package com.bitium.event.event;

import com.bitium.event.event.anno.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： com.jdpay.event.event <br>
 * <b>类名称</b>： Sub <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/23 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class Sub {
    List<Object> events = new ArrayList<Object>();

    @Subscribe
    public void doSub(String event) {
        System.out.println("sub recieved String event:" + event);
        events.add(event);
    }

    @Subscribe
    public void doSub(Long event) {
        System.out.println("sub recieved Long event:" + event);
        events.add(event);
    }

    @Subscribe
    public void doSub(Float event) {
        System.out.println("sub recieved Float event:" + event);
        events.add(event);
    }

    public List<Object> getEvents() {
        return events;
    }
}
