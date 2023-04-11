package cc.forim.armagin.shorturl.infra.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/12 00:00
 */

@Configuration
@MapperScan("cc.forim.armagin.shorturl")
public class MyBatisPlusConfig {
}
