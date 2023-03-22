package cc.forim.armagin.server.infra.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Kafka Topic配置类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/22 14:26
 */

@Configuration
public class KafkaConfig {

    @Value("${armagin.transformEvent.topic.name}")
    private String transformEventTopic;

    @Bean
    public NewTopic logTopic() {
        return TopicBuilder.name(transformEventTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }
}