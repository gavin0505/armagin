package cc.forim.armagin.shorturl.dao;

import cc.forim.armagin.shorturl.infra.entity.CompressionCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 压缩码映射DB处理类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 11:51
 */
@Mapper
public interface CompressionCodeMapper extends BaseMapper<CompressionCode> {

    /**
     * 自定义批量插入
     *
     * @param batchList 批量插入的数据
     * @return 插入结果影响行数
     */
    int insertBatchSomeColumn(@Param("list") List<Object> batchList);

    /**
     * 获取一个最新可用的压缩码
     *
     * @return 压缩码
     */
    CompressionCode getLatestAvailableCompressionCode();
}