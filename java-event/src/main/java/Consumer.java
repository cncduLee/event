/**
 * Copyright (c) 2015, jdpay.com. All rights reserved.
 */

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： PACKAGE_NAME <br>
 * <b>类名称</b>： Consumer <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/24 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class Consumer implements ValueChangeListener
{
    @Override
    public void performed(ValueChangeEvent e) {
        System.out.println("value changed, new value = " + e.getValue());
    }
}
