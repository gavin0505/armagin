package cc.forim.armagin.user.dao;

import cc.forim.armagin.user.infra.dto.RegisterMsgDto;
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

    /**
     * 通过用户名获取用户id
     */
    Integer selectIdByUsername(@Param("username") String username);

    /**
     * 通过邮箱获取用户id
     */
    Integer selectIdByEmail(@Param("email") String email);

    /**
     * 新增用户
     */
    Integer insertUser(@Param("dto") RegisterMsgDto dto);

    /**
     * 新增用户角色映射
     */
    Integer insertUserRole(@Param("id") Integer id, @Param("role") Integer role);
}