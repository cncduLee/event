/**
 * Copyright (c) 2015, jdpay.com. All rights reserved.
 */

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： PACKAGE_NAME <br>
 * <b>类名称</b>： Producer <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/24 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class Producer {
    ListenerRegister register = new ListenerRegister();
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int newValue) {
        if (value != newValue) {
            value = newValue;
            ValueChangeEvent event = new ValueChangeEvent(this, value);
            fireAEvent(event);
        }
    }

    public void addListener(ValueChangeListener a) {
        register.addListener(a);
    }

    public void removeListener(ValueChangeListener a) {
        register.removeListener(a);
    }

    public void fireAEvent(ValueChangeEvent event) {
        register.fireAEvent(event);
    }
}
