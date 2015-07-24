/**
 * Copyright (c) 2015, bitium.com. All rights reserved.
 */

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import javax.annotation.Resource;

/**
 * <b>项目名</b>： wallet-customer <br>
 * <b>包名称</b>：  <br>
 * <b>类名称</b>： Test <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/24 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class SpringEventTest {

    @Resource
    EventSender eventSender;

    @Test
    public void test() {
        RealNameAfterEvent event = new RealNameAfterEvent("hello-source-data","bitium",NotifyType.MAIL);
        eventSender.send(event);
        eventSender.send(event);
        eventSender.send(event);
        eventSender.send(event);
        eventSender.send(event);
        eventSender.send(event);
    }
//    http://my.oschina.net/jilujia/blog/40329
}
