package cc.forim.armagin.server.mapper;

import cc.forim.armagin.server.infra.entity.VisitStatistics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * 访问统计Mapper
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/29 23:59
 */

@Mapper
public interface VisitStatisticsMapper extends BaseMapper<VisitStatistics> {

    VisitStatistics selectByUniqueKey(@Param("statisticsDate") LocalDate statisticsDate,
                                      @Param("compressionCode") String compressionCode,
                                      @Param("shortUrlDigest") String shortUrlDigest,
                                      @Param("longUrlDigest") String longUrlDigest);
}