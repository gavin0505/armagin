package cc.forim.armagin.server.cron;

import cc.forim.armagin.common.lock.DistributeLockFactory;
import cc.forim.armagin.common.lock.DistributedLock;
import cc.forim.armagin.server.infra.enums.LockKeyEnum;
import cc.forim.armagin.server.infra.utils.RedisUtil;
import cc.forim.armagin.server.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

/**
 * 【定时】将前一天的访问数据统计信息持久化
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/29 22:22
 */
@Component
@EnableScheduling
@Slf4j
public class VisitStatisticsJob {

    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    @Resource(name = "distributeLockFactory")
    private DistributeLockFactory distributedLockFactory;

    @Resource(name = "statisticsServiceImpl")
    private StatisticsService statisticsService;


    /**
     * 每天三点执行一次
     */
    @Scheduled(cron = "0 0 3 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void job() {
        log.info("执行定时任务");
        // 分配锁
        DistributedLock lock = distributedLockFactory.provideDistributedLock(LockKeyEnum.VISITOR_STATS_TASK.getCode());
        boolean tryLock = lock.tryLock(LockKeyEnum.VISITOR_STATS_TASK.getWaitTime(), LockKeyEnum.VISITOR_STATS_TASK.getReleaseTime(), TimeUnit.SECONDS);
        if (tryLock) {
            try {
                // 获取昨天的开始和结束时间
                OffsetDateTime now = OffsetDateTime.now(ZoneOffset.of("+08:00"));
                OffsetDateTime start = now.minusDays(1L).withNano(0).withSecond(0).withMinute(0).withHour(0);
                OffsetDateTime end = start.withNano(0).withSecond(59).withMinute(59).withHour(23);

                // 执行统计
                statisticsService.processVisitStatisticsDuration(start, end);
            } finally {
                lock.unlock();
            }
        }
    }
}