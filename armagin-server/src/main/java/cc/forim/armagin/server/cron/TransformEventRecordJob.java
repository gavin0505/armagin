package cc.forim.armagin.server.cron;

import cc.forim.armagin.server.infra.utils.RedisUtil;
import cc.forim.armagin.server.mapper.TransformEventRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static cc.forim.armagin.server.infra.enums.CacheKeyEnum.TER_TEMP_CACHE_LIST;

/**
 * 【定时】事件转换记录批量写入数据库
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/6 23:31
 */
@Component
@EnableScheduling
@Slf4j
public class TransformEventRecordJob {

    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    @Resource(name = "transformEventRecordMapper")
    private TransformEventRecordMapper transformEventRecordMapper;

    /**
     * 10s执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void job() {

        Long size = 0L;
        size = redisUtil.lSize(TER_TEMP_CACHE_LIST.getKey());

        if (size > 0L) {
            List<Object> records = redisUtil.rPop(TER_TEMP_CACHE_LIST.getKey(), size);

            // 分片插入（每 1000 条执行一次批量插入）
            int batchSize = 1000;
            int total = records.size();
            // 需要执行的次数
            int insertTimes = total / batchSize;
            // 最后一次执行需要提交的记录数（防止可能不足 1000 条）
            int lastSize = batchSize;
            if (total % batchSize != 0) {
                insertTimes++;
                lastSize = total % batchSize;
            }

            for (int i = 0; i < insertTimes; i++) {
                if (insertTimes == i + 1) {
                    batchSize = lastSize;
                }
                // 分片执行批量插入
                if (transformEventRecordMapper.insertBatchSomeColumn(records.subList(i * batchSize, (i * batchSize + batchSize))) > 0) {
                    log.info("分片插入成功！");
                } else {
                    log.warn("分片插入失败...");
                }
            }
        }
    }
}