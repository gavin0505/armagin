package cc.forim.armagin.user.service.impl;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.common.utils.StpAdminUtil;
import cc.forim.armagin.common.utils.StpSuperAdminUtil;
import cc.forim.armagin.common.utils.StpUserUtil;
import cc.forim.armagin.user.dao.RoleMapper;
import cc.forim.armagin.user.dao.RoleMappingMapper;
import cc.forim.armagin.user.dao.UserMapper;
import cc.forim.armagin.user.dto.LoginDto;
import cc.forim.armagin.user.dto.LogoutDto;
import cc.forim.armagin.user.dto.RegisterMsgDto;
import cc.forim.armagin.user.service.AccountService;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

import static cc.forim.armagin.user.RoleCommon.*;

/**
 * 账户服务实现类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/22 22:48
 */

@Service("accountServiceImpl")
@Slf4j
public class AccountServiceImpl implements AccountService {

    private static final int LOGIN_BY_USERNAME = 1;

    private static final int NULL = 0;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMappingMapper roleMappingMapper;

    @Override
    public ResultVo<String> register(RegisterMsgDto registerMsgDto) {
        return null;
    }

    @Override
    public ResultVo<String> login(LoginDto loginDto) {

        // 通过Username登录
        if (loginDto.getType() == LOGIN_BY_USERNAME) {
            // 获取账号id
            Integer id = userMapper.login(loginDto.getUsername(), loginDto.getPassword());
            Optional<Integer> optionalValue = Optional.ofNullable(id);
            id = optionalValue.orElse(NULL);

            // 账号存在即登录
            if (id > NULL) {
                // 获取账号角色
                String role = getRoleCodeByUserId(id);
                String token = null;

                // 根据不同角色进行登录
                switch (role) {
                    case SUPER_ADMIN -> {
                        StpSuperAdminUtil.login(id);
                        token = StpSuperAdminUtil.getTokenInfo().getTokenValue();
                    }
                    case ADMIN -> {
                        StpAdminUtil.login(id);
                        token = StpAdminUtil.getTokenInfo().getTokenValue();
                    }
                    case USER -> {
                        StpUserUtil.login(id);
                        token = StpUserUtil.getTokenInfo().getTokenValue();
                    }
                }

                // 更新最后登录时间
                userMapper.updateLogged(id, DateUtil.date());

                log.info("【用户】id: {} 登录成功", id);

                return ResultVo.success(token);
            }
        }
        return ResultVo.error("登录失败，用户名或密码错误");
    }

    /**
     * 获取用户所属角色
     *
     * @param userId 用户id
     * @return 对应角色状态码，如，超级管理员：super-admin
     */
    private String getRoleCodeByUserId(Integer userId) {
        Integer roleId = roleMappingMapper.getRoleIdFromUserId(userId);
        return roleMapper.getRoleCodeById(roleId);
    }

    @Override
    public ResultVo<String> logout(LogoutDto logoutDto) {

        // user登出
        Object userId = StpUserUtil.getLoginIdByToken(logoutDto.getToken());
        if (ObjectUtil.isNotNull(userId)) {
            StpUserUtil.logout(userId);
            log.info("【用户】id: {} 登出成功", userId);
            return ResultVo.success("用户登出成功！");
        }

        // admin登出
        Object adminId = StpAdminUtil.getLoginIdByToken(logoutDto.getToken());
        if (ObjectUtil.isNotNull(adminId)) {
            StpAdminUtil.logout(adminId);
            log.info("【管理员】id: {} 登出成功", adminId);
            return ResultVo.success("管理员登出成功！");
        }

        // super-admin登出
        Object superAdminId = StpSuperAdminUtil.getLoginIdByToken(logoutDto.getToken());
        if (ObjectUtil.isNotNull(superAdminId)) {
            StpSuperAdminUtil.logout(superAdminId);
            log.info("【超级管理员】id: {} 登出成功", superAdminId);
            return ResultVo.success("超级管理员登出成功！");
        }

        return ResultVo.error("token错误，登出失败");
    }
}