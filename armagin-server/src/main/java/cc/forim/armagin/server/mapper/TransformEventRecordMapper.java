package cc.forim.armagin.server.mapper;

import cc.forim.armagin.server.infra.entity.TransformEventRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 转换事件记录Mapper
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/6 23:14
 */

@Mapper
public interface TransformEventRecordMapper extends BaseMapper<TransformEventRecord> {
}