package cc.forim.armagin.user.service.impl;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.common.utils.RedisUtil;
import cc.forim.armagin.user.infra.dto.VerifyCodeDto;
import cc.forim.armagin.user.infra.enums.CacheKey;
import cc.forim.armagin.user.infra.pending.TaskPendingHolder;
import cc.forim.armagin.user.service.VerifyCodeService;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

import static cc.forim.armagin.user.infra.enums.CommonConstant.COLON;
import static cc.forim.armagin.user.infra.enums.CommonConstant.STRING;
import static cc.forim.armagin.user.infra.enums.EmailContentEnum.VERIFY_CODE_TO_LOGIN;

/**
 * 验证码服务实现类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/23 22:32
 */

@RefreshScope
@Service("verifyCodeServiceImpl")
@Slf4j
public class VerifyCodeServiceImpl implements VerifyCodeService {

    /**
     * type: Email
     */
    private static final int EMAIL = 1;

    /**
     * type: Phone
     */
    private static final int PHONE = 2;

    /**
     * 失效时间(邮件，登录) 单位：分钟
     */
    private static final int MAIL_EXIPRE_MINUTE_LOGIN = 2;

    /**
     * 验证码长度
     */
    private static final int VERIFY_CODE_LENGTH = 6;

    @Value("${email.verifyCode.from}")
    private String emailFrom;

    @Value("${email.verifyCode.user}")
    private String emailUser;

    @Value("${email.verifyCode.password}")
    private String emailPassword;

    @Value("${email.verifyCode.port}")
    private Integer emailPort;

    @Value("${email.verifyCode.host}")
    private String emailHost;

    @Value("${email.verifyCode.sslEnable}")
    private Boolean sslEnable;

    @Value("${email.verifyCode.authEnable}")
    private Boolean authEnable;

    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    @Resource(name = "taskPendingHolder")
    private TaskPendingHolder taskPendingHolder;


    @Override
    public ResultVo<String> getVerifyCode(VerifyCodeDto verifyCodeDto) {

        // 异步发送邮件
        CompletableFuture.runAsync(() -> {
            // 生成6位验证码
            String code = RandomUtil.randomNumbers(VERIFY_CODE_LENGTH);

            switch (verifyCodeDto.getType()) {
                case EMAIL -> sendVerifyCodeByEmail(code, verifyCodeDto.getAccount(), MAIL_EXIPRE_MINUTE_LOGIN);
                case PHONE -> sendVerifyCodeByPhone(code, verifyCodeDto.getAccount());
            }
        }, taskPendingHolder.route());

        return ResultVo.success("已发送验证码");
    }

    /**
     * 通过邮件发送验证码
     */
    private void sendVerifyCodeByEmail(String code, String receiver, Integer expireMinute) {
        try {
            // 邮件配置
            MailAccount account = new MailAccount();
            account.setHost(emailHost);
            account.setPort(emailPort);
            account.setSslEnable(sslEnable);
            account.setAuth(authEnable);
            account.setFrom(emailFrom);
            account.setUser(emailUser);
            account.setPass(emailPassword);

            // 标题替换
            String title = StrUtil.format(VERIFY_CODE_TO_LOGIN.getTitle(), code);
            // 内容替换
            String content = StrUtil.format(VERIFY_CODE_TO_LOGIN.getContent(), code, expireMinute);

            // 发送邮件，此处可以用流量控制做防盗刷，本次没做
            String send = MailUtil.send(account, receiver, title, content, false);

            // 保存到Redis
            saveVerifyCode(receiver, code);

        } catch (Exception e) {
            log.error("邮件发送异常！影响到的用户: {}", receiver);
        }
    }

    /**
     * 通过手机号发送验证码
     */
    private void sendVerifyCodeByPhone(String code, String account) {
    }

    /**
     * 保存验证码
     */
    private void saveVerifyCode(String account, String code) {

        // 验证码存Redis，格式：armagin:user:register:verify:账号名:string
        Boolean flag = redisUtil.set(CacheKey.USER_REGISTER_VERIFY_CODE_STRING_PREFIX.getKey() + account + COLON + STRING,
                code,
                CacheKey.USER_REGISTER_VERIFY_CODE_STRING_PREFIX.getExpireSeconds()
        );

        if (!flag) {
            log.error("验证码存储失败，请检查网络或Redis连接情况，用户:【{}】，验证码：【{}】", account, code);
        }
    }

    /**
     * 获取已生成的验证码
     *
     * @param account 账号
     */
    private String getGeneratedVerifyCode(String account) {
        return ObjectUtil.toString(redisUtil.get(CacheKey.USER_REGISTER_VERIFY_CODE_STRING_PREFIX.getKey() + account + COLON + STRING));
    }

    @Override
    public Boolean ifRegisterVerifyCodeValid(String account, String code) {
        if (StrUtil.equals(code, getGeneratedVerifyCode(account))) {
            return true;
        }
        return false;
    }
}