package cc.forim.armagin.server.service.impl;

import cc.forim.armagin.server.infra.entity.VisitStatistics;
import cc.forim.armagin.server.mapper.TransformEventRecordMapper;
import cc.forim.armagin.server.mapper.VisitStatisticsMapper;
import cc.forim.armagin.server.service.StatisticsService;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/29 23:44
 */

@Service("statisticsServiceImpl")
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private TransformEventRecordMapper transformEventRecordMapper;

    @Resource
    private VisitStatisticsMapper visitStatisticsMapper;

    @Override
    public void processVisitStatisticsDuration(OffsetDateTime start, OffsetDateTime end) {

        // 获取对应时间段内的统计信息
        List<VisitStatistics> selective = transformEventRecordMapper.selectVisitStatisticsDuration(start, end);
        for (VisitStatistics visitStatistics : selective) {
            // 获取单条统计信息的历史情况
            VisitStatistics selectiveVisitStatistics
                    = visitStatisticsMapper.selectByUniqueKey(visitStatistics.getStatisticsDate(),
                    visitStatistics.getCompressionCode(), visitStatistics.getShortUrlDigest(), visitStatistics.getLongUrlDigest());

            if (ObjectUtil.isNull(selectiveVisitStatistics)) {
                // 未统计过的，插入统计
                visitStatisticsMapper.insert(visitStatistics);
            } else {
                // 已统计过的，更新统计
                visitStatistics.setId(selectiveVisitStatistics.getId());
                visitStatisticsMapper.updateById(visitStatistics);
            }
        }
    }
}