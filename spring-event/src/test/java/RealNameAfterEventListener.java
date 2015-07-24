/**
 * Copyright (c) 2015, bitium.com. All rights reserved.
 */

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * <b>项目名</b>： wallet-customer <br>
 * <b>包名称</b>：  <br>
 * <b>类名称</b>： RealNameAfterEventListener <br>
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
public class RealNameAfterEventListener implements ApplicationListener<RealNameAfterEvent> {
    Logger logger = Logger.getLogger(String.valueOf(this.getClass()));

    @Override
    public void onApplicationEvent(RealNameAfterEvent event) {
        switch (event.getType()) {
            case SMS:
                logger.info("sms received:" + event);
                break;
            case LOG:
                logger.info("log received:" + event);
                break;
            case MAIL:
                logger.info("mail received:" + event);
                break;
        }
    }
}
