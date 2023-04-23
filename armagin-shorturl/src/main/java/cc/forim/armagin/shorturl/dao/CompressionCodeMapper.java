package cc.forim.armagin.shorturl.dao;

import cc.forim.armagin.shorturl.infra.entity.CompressionCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 获取一个最新可用的压缩码
     *
     * @param domainConfId 短链域名配置id
     * @return 压缩码
     */
    CompressionCode getLatestAvailableCompressionCode(@Param("domainConfId") Long domainConfId);

    /**
     * 更新压缩码状态
     *
     * @param record 压缩码
     * @return 改变结果
     */
    int updateByPrimaryKeySelective(CompressionCode record);
}