/**
 * Copyright (c) 2015, jdpay.com. All rights reserved.
 */
package com.bitium.event.event;

import org.testng.annotations.Test;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： com.jdpay.event.event <br>
 * <b>类名称</b>： CopyOnWriteArraySetTest <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/22 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class CopyOnWriteArraySetTest {
    @Test
    public void addAfterGet() {
        CopyOnWriteArraySet<String>  set = new CopyOnWriteArraySet<String>();
        set.add("a");
        set.add("b");
        set.add("c");
    }
}
