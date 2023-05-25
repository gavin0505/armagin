package cc.forim.armagin.gateway.cron;

import cn.dev33.satoken.same.SaSameUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * sa-token的same-token刷新定时任务
 *
 * @author Gavin Zhang
 * @version V1.0
 * @link <a href="https://sa-token.cc/doc.html#/micro/same-token">sa-token官网关于same-token的解释</a>
 * @since 2023/5/25 22:24
 */
@Configuration
public class SameTokenRefreshJob {

    /**
     * 每5分钟刷新一次same-token
     */
    @Scheduled(cron = "0 0/5 * * * ? ")
    public void refreshToken() {
        SaSameUtil.refreshToken();
    }
}