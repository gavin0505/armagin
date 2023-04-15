package cc.forim.armagin.shorturl.infra.config;

import cc.forim.armagin.shorturl.infra.keygen.SequenceGenerator;
import cc.forim.armagin.shorturl.infra.keygen.SnowflakeSequenceGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * 序列生成器自动注入
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/15 23:32
 */
public class SequenceGeneratorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SequenceGenerator snowflakeSequenceGenerator(@Value("${armagin.snowflake.worker.id}") Long workerId,
                                                        @Value("${armagin.snowflake.data.center.id}") Long dataCenterId) {
        SnowflakeSequenceGenerator sequenceGenerator = new SnowflakeSequenceGenerator(workerId, dataCenterId);
        sequenceGenerator.init();
        return sequenceGenerator;
    }
}