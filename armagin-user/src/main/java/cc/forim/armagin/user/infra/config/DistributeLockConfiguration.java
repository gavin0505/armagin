package cc.forim.armagin.user.infra.config;

import cc.forim.armagin.common.lock.DistributeLockFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分布式锁配置
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/17 23:57
 */
@Configuration
public class DistributeLockConfiguration {

    @Bean
    public DistributeLockFactory distributeLockFactory() {
        return new DistributeLockFactory();
    }
}