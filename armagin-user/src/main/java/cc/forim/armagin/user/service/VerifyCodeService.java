package cc.forim.armagin.user.service;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.user.infra.dto.VerifyCodeDto;

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

    /**
     * 注册验证码是否有效
     *
     * @param account 邮箱
     * @param code    验证码
     * @return true-有效；false-无效
     */
    Boolean ifRegisterVerifyCodeValid(String account, String code);
}