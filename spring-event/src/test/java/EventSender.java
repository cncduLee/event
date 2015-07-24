/**
 * Copyright (c) 2015, bitium.com. All rights reserved.
 */

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * <b>项目名</b>： wallet-customer <br>
 * <b>包名称</b>：  <br>
 * <b>类名称</b>： EventSender <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/24 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
@Component
public class EventSender implements ApplicationContextAware {
    Logger logger = Logger.getLogger(String.valueOf(this.getClass()));
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void send(ApplicationEvent event) {
        logger.info("*****************send event begin*****************");
        context.publishEvent(event);
        logger.info("*****************send  event  end*****************");
    }
}
