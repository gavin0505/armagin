package cc.forim.armagin.server.service;

import java.time.OffsetDateTime;

/**
 * 统计服务接口
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/29 23:42
 */
public interface StatisticsService {

    /**
     * 处理周期性的访客统计
     *
     * @param start 开始时间
     * @param end   结束时间
     */
    void processVisitStatisticsDuration(OffsetDateTime start, OffsetDateTime end);
}