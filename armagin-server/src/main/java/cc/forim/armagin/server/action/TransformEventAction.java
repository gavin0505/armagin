package cc.forim.armagin.server.action;

import cc.forim.armagin.server.infra.enums.TransformEnum;
import cc.forim.armagin.server.pipeline.BusinessProcess;
import cc.forim.armagin.server.pipeline.TransformContext;
import cn.hutool.json.JSONUtil;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 【4】转换事件发送流程
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/17 23:02
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class TransformEventAction implements BusinessProcess {

    @Resource
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Value("${armagin.transformEvent.topic.name}")
    private String topic;

    @Override
    public void process(TransformContext context) {

        // 构建URL转换事件记录实体
        UrlTransformRecordEvent urlTransformRecordEvent = UrlTransformRecordEvent.builder()
                .clientIp(context.getParam(TransformEnum.CLIENT_ID.getValue()))
                .timestamp(System.currentTimeMillis())
                .compressionCode(context.getCompressionCode())
                .userAgent(context.getParam(TransformEnum.UA.getValue()))
                .cookieValue(context.getParam(TransformEnum.COOKIE.getValue()))
                .shortUrlString(context.getParam(TransformEnum.SU.getValue()))
                .longUrlString(context.getParam(TransformEnum.LU.getValue()))
                .transformStatusValue(context.getTransformStatus().getCode())
                .build();

        // JSON化记录
        String message = JSONUtil.toJsonStr(urlTransformRecordEvent);

        log.info("消息转换记录：{}", message);

        // 发送记录到Kafka
        kafkaTemplate.send(topic, message);
    }

    /**
     * URL转换事件记录实体
     */
    @Data
    @Builder
    private static class UrlTransformRecordEvent {

        /**
         * 客户端IP
         */
        private String clientIp;

        /**
         * 压缩码
         */
        private String compressionCode;

        /**
         * user-agent
         */
        private String userAgent;

        /**
         * cookie
         */
        private String cookieValue;

        /**
         * 记录时间戳
         */
        private Long timestamp;

        /**
         * 短链字符串
         */
        private String shortUrlString;

        /**
         * 长链字符串
         */
        private String longUrlString;

        /**
         * 转换状态值
         */
        private Integer transformStatusValue;
    }
}