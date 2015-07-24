/**
 * Copyright (c) 2015, bitium.com. All rights reserved.
 */
package com.bitium.event.event.dispatch;

import com.bitium.event.event.Dispatcher;
import com.bitium.event.event.Subscriber;

import java.util.Iterator;

import static com.bitium.event.event.utils.Assert.checkNotNull;

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： com.bitium.event.event <br>
 * <b>类名称</b>： ImmediateDispatcher <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/21 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ImmediateDispatcher implements Dispatcher {
    @Override
    public void dispatch(Object event, Iterator<Subscriber> subscribers) {
        checkNotNull(event);
        while (subscribers.hasNext()) {
            subscribers.next().dispatchEvent(event);
        }
    }
}
