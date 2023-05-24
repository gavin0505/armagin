package cc.forim.armagin.user.service;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.user.dto.VerifyCodeDto;

/**
 * 验证码服务接口
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/23 22:31
 */
public interface VerifyCodeService {

    /**
     * 获取验证码
     *
     * @param verifyCodeDto 验证码
     * @return 验证码
     */
    ResultVo<String> getVerifyCode(VerifyCodeDto verifyCodeDto);
}