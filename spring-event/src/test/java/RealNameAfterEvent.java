/**
 * Copyright (c) 2015, bitium.com. All rights reserved.
 */

import org.springframework.context.ApplicationEvent;

/**
 * <b>项目名</b>： wallet-customer <br>
 * <b>包名称</b>：  <br>
 * <b>类名称</b>： Booking <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/24 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class RealNameAfterEvent extends ApplicationEvent {
    private String address;
    private NotifyType type;

    public RealNameAfterEvent(Object source) {
        super(source);
    }

    public RealNameAfterEvent(Object source, String address, NotifyType type) {
        super(source);
        this.address = address;
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public NotifyType getType() {
        return type;
    }
}
