package cc.forim.armagin.user.dao;

import cc.forim.armagin.user.infra.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 用户映射类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/22 23:17
 */

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {

    /**
     * 登录
     *
     * @return 用户id
     */
    Integer login(@Param("username") String username, @Param("password") String password);

    /**
     * 更新最后登录时间
     */
    Integer updateLogged(@Param("id") Integer id, @Param("loggedTime") Date time);

    /**
     * 通过id获取用户名
     */
    String selectUsernameById(@Param("id") Integer id);
}