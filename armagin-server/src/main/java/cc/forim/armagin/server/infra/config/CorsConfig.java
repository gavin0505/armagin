package cc.forim.armagin.server.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * 跨域配置
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/22 14:25
 */
@Configuration
public class CorsConfig implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许访问路径
        registry.addMapping("/**")
                // 允许跨域访问的源
                .allowedOrigins("*")
                // 允许跨域访问的方法
                .allowedMethods("POST", "GET", "DELETE", "PUT", "OPTIONS")
                // 预检间隔时间
                .maxAge(10000);
    }
}