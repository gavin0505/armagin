package cc.forim.armagin.server.mapper;

import cc.forim.armagin.server.infra.entity.TransformEventRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 转换事件记录Mapper
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/6 23:14
 */

@Mapper
public interface TransformEventRecordMapper extends BaseMapper<TransformEventRecord> {

    /**
     * 批量插入转换事件记录
     *
     * @param transformEventRecords 批量插入的数据
     * @return 插入结果影响行数
     */
    int insertBatchTransformEventRecord(@Param("list") List<TransformEventRecord> transformEventRecords);
}