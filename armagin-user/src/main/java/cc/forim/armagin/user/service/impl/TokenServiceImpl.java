package cc.forim.armagin.user.service.impl;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.common.utils.StpAdminUtil;
import cc.forim.armagin.common.utils.StpSuperAdminUtil;
import cc.forim.armagin.common.utils.StpUserUtil;
import cc.forim.armagin.user.dao.UserMapper;
import cc.forim.armagin.user.dto.GetUserInfoByTokenDto;
import cc.forim.armagin.user.service.TokenService;
import cc.forim.armagin.user.vo.UserInfoVo;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static cc.forim.armagin.user.infra.common.RoleCommon.*;

/**
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/6/4 22:10
 */

@Service("tokenServiceImpl")
public class TokenServiceImpl implements TokenService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ResultVo<UserInfoVo> getUserInfoByToken(GetUserInfoByTokenDto getUserInfoByTokenDto) {

        // 1. 获取角色
        String role = getUserTypeByToken(getUserInfoByTokenDto.getToken());

        // 2. 组装UserInfo
        UserInfoVo userInfoVo = assembleUserInfoByRole(role);

        if (ObjectUtil.isNotNull(userInfoVo)) {
            return ResultVo.success(userInfoVo);
        }
        return ResultVo.error("获取用户信息错误，请检查token");
    }

    /**
     * 通过Token获取用户角色
     *
     * @param token token
     * @return 对应角色
     */
    private String getUserTypeByToken(String token) {

        if (ObjectUtil.isNotNull(StpUserUtil.getLoginIdByToken(token))) {
            return StpUserUtil.getLoginType();
        } else if (ObjectUtil.isNotNull(StpAdminUtil.getLoginIdByToken(token))) {
            return StpAdminUtil.getLoginType();
        } else if (ObjectUtil.isNotNull(StpSuperAdminUtil.getLoginIdByToken(token))) {
            return StpSuperAdminUtil.getLoginType();
        }
        return StrUtil.EMPTY;
    }

    /**
     * 通过角色组装UserInfo
     *
     * @param role 角色
     * @return UserInfo
     */
    private UserInfoVo assembleUserInfoByRole(String role) {

        SaTokenInfo tokenInfo = null;
        switch (role) {
            case SUPER_ADMIN -> {
                tokenInfo = StpSuperAdminUtil.getTokenInfo();
                assembleUserInfo(tokenInfo);
                return assembleUserInfo(tokenInfo);
            }
            case ADMIN -> {
                tokenInfo = StpAdminUtil.getTokenInfo();
                assembleUserInfo(tokenInfo);
                return assembleUserInfo(tokenInfo);
            }
            case USER -> {
                tokenInfo = StpUserUtil.getTokenInfo();
                assembleUserInfo(tokenInfo);
                return assembleUserInfo(tokenInfo);
            }
        }
        return null;
    }

    /**
     * 从SaTokenInfo组装UserInfo
     */
    private UserInfoVo assembleUserInfo(SaTokenInfo saTokenInfo) {

        UserInfoVo userInfoVo = new UserInfoVo();

        // 获取用户id
        Object id = saTokenInfo.getLoginId();

        // 查询用户名
        if (ObjectUtil.isNotNull(id)) {
            String username = userMapper.selectUsernameById(Integer.parseInt(id.toString()));
            userInfoVo.setUsername(username);
        }
        return userInfoVo;
    }
}