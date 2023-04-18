package cc.forim.armagin.shorturl.dao;

import cc.forim.armagin.shorturl.infra.entity.DomainConf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 短链配置映射DB处理
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 13:40
 */

@Mapper
public interface DomainConfMapper extends BaseMapper<DomainConf> {

    /**
     * 查找短链域名是否存在
     *
     * @param domain 需要判断的域名
     * @return 结果
     */
    DomainConf selectByDomain(@Param("domain") String domain, @Param("bizType")String bizType);
}