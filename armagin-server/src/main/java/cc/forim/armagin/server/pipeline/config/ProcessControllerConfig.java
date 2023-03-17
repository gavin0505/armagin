package cc.forim.armagin.server.pipeline.config;

import cc.forim.armagin.server.pipeline.ProcessController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 责任链控制器装配
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/17 11:53
 */
@Configuration
public class ProcessControllerConfig {

    @Bean("processController")
    public ProcessController processController() {
        return new ProcessController();
    }
}