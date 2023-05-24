package cc.forim.armagin.user.service;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.user.dto.LoginDto;
import cc.forim.armagin.user.dto.LogoutDto;
import cc.forim.armagin.user.dto.RegisterMsgDto;

/**
 * 账户服务
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/22 22:43
 */
public interface AccountService {

    /**
     * 注册
     *
     * @param registerMsgDto 注册信息载体
     * @return 注册结果
     */
    ResultVo<String> register(RegisterMsgDto registerMsgDto);


    /**
     * 登录
     *
     * @param loginDto 登录信息载体
     * @return token
     */
    ResultVo<String> login(LoginDto loginDto);

    /**
     * 登出
     *
     * @param logoutDto 登出信息载体
     * @return token
     */
    ResultVo<String> logout(LogoutDto logoutDto);
}