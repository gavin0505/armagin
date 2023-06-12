package cc.forim.armagin.server.infra.config;

import cc.forim.armagin.common.lock.DistributeLockFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分布式锁工厂注入
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/29 22:35
 */
@Configuration
public class DistributeLockConfiguration {

    @Bean
    public DistributeLockFactory distributeLockFactory() {
        return new DistributeLockFactory();
    }
}