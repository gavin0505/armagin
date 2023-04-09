package cc.forim.armagin.server.kafka;

import cc.forim.armagin.server.infra.entity.TransformEventRecord;
import cc.forim.armagin.server.infra.support.MappedDiagnosticContextProvider;
import cc.forim.armagin.server.service.TransformEventService;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Kafka消费者
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/27 13:49
 */
@Component
@Slf4j
public class RecordConsumer {

    @Resource(name = "transformEventServiceImpl")
    private TransformEventService transformEventService;

    @Resource(name = "mappedDiagnosticContextProvider")
    private MappedDiagnosticContextProvider mappedDiagnosticContextProvider;

    @KafkaListener(topics = "#{'${armagin.transformEvent.topic.name}'}", groupId = "#{'${armagin.transformEvent.groupId.name}'}")
    public void consumer(ConsumerRecord<?, String> consumerRecord) {

        mappedDiagnosticContextProvider.process(() -> {
            // 获取消费信息
            Optional<String> kafkaMessage = Optional.ofNullable(consumerRecord.value());
            if (kafkaMessage.isPresent()) {
                TransformEvent transformEvent = JSONUtil.toBean(kafkaMessage.get(), TransformEvent.class);
                log.info("接收到URL转换事件：{}", transformEvent);

                // 转换到TransformEventRecord
                TransformEventRecord transformEventRecord = event2Record(transformEvent);

                // 执行记录持久化
                transformEventService.recordTransform(transformEventRecord);
                log.info("记录URL转换事件完成！");
            }
        });
    }

    /**
     * Event转Record
     */
    private TransformEventRecord event2Record(TransformEvent event) {
        return TransformEventRecord.builder()
                .clientIp(event.getClientIp())
                .compressionCode(event.getCompressionCode())
                .userAgent(event.getUserAgent())
                .cookieValue(event.getCookieValue())
                .shortUrl(event.getShortUrlString())
                .longUrl(event.getLongUrlString())
                .transformStatus(event.getTransformStatusValue())
                .build();
    }
}