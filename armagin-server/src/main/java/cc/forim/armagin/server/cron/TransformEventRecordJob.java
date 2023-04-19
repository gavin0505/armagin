package cc.forim.armagin.server.cron;

import cc.forim.armagin.server.infra.entity.TransformEventRecord;
import cc.forim.armagin.server.infra.utils.RedisUtil;
import cc.forim.armagin.server.mapper.TransformEventRecordMapper;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
    @Transactional(rollbackFor = Exception.class)
    public void job() {

        // 统计这一批的事件个数
        Long size = redisUtil.lSize(TER_TEMP_CACHE_LIST.getKey());

        if (size > 0L) {
            // 防止Date格式因Object自动转换为时间戳，因此这里重新使用JSON解析
            List<Object> obj = redisUtil.rPop(TER_TEMP_CACHE_LIST.getKey(), size);
            String json = JSONUtil.toJsonStr(obj);
            // help GC
            obj = null;

            List<TransformEventRecord> records = JSONUtil.toList(json, TransformEventRecord.class);

            Assert.notNull(records, "records为空");
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
                if (transformEventRecordMapper.insertBatchTransformEventRecord(records.subList(i * batchSize, (i * batchSize + batchSize))) > 0) {
                    log.info("分片插入成功！");
                } else {
                    log.warn("分片插入失败...");
                }
            }
        }
    }
}