package cc.forim.armagin.user.service;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.user.dto.GetUserInfoByTokenDto;
import cc.forim.armagin.user.vo.UserInfoVo;

/**
 * Token服务接口
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/6/4 22:10
 */
public interface TokenService {

    /**
     * 通过token获取用户信息
     *
     * @param getUserInfoByTokenDto dto
     * @return 用户信息
     */
    ResultVo<UserInfoVo> getUserInfoByToken(GetUserInfoByTokenDto getUserInfoByTokenDto);
}
