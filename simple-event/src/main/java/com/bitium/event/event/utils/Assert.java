/**
 * Copyright (c) 2015, bitium.com. All rights reserved.
 */
package com.bitium.event.event.utils;

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： com.bitium.event.event.utils <br>
 * <b>类名称</b>： Assert <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/21 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public final class Assert {
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
