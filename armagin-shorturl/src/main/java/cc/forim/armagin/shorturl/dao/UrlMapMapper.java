package cc.forim.armagin.shorturl.dao;

import cc.forim.armagin.shorturl.infra.entity.UrlMap;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UrlMap映射DB处理
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 14:30
 */

@Mapper
public interface UrlMapMapper extends BaseMapper<UrlMap> {

    /**
     * 通过id使映射失效
     *
     * @param id 主键
     */
    Integer expiredByIds(@Param("ids") List<Long> id);

}