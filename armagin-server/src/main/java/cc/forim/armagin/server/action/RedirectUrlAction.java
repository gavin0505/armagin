package cc.forim.armagin.server.action;

import cc.forim.armagin.server.infra.enums.TransformEnum;
import cc.forim.armagin.server.infra.enums.TransformStatus;
import cc.forim.armagin.server.infra.spring.WebFluxServerResponseWriter;
import cc.forim.armagin.server.pipeline.BusinessProcess;
import cc.forim.armagin.server.pipeline.TransformContext;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 【3】重定向处理
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/17 22:47
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class RedirectUrlAction implements BusinessProcess {

    @Resource
    private WebFluxServerResponseWriter webFluxServerResponseWriter;

    @Override
    public void process(TransformContext context) {

        // 若短链映射找到了
        if (ObjectUtil.equals(context.getTransformStatus(), TransformStatus.TRANSFORM_SUCCESS)) {
            // 获取长链接
            String longUrl = context.getParam(TransformEnum.LU.getValue());
            try {
                if (StrUtil.isNotBlank(longUrl)) {
                    // 获取重定向任务
                    Runnable redirectAction = webFluxServerResponseWriter.redirectAction(
                            context.getParam(TransformEnum.SWE.getValue()),
                            longUrl
                    );
                    // 将重定向任务载入上下文
                    context.setRedirectAction(redirectAction);
                    context.setTransformStatus(TransformStatus.REDIRECTION_SUCCESS);
                } else {
                    context.setTransformStatus(TransformStatus.REDIRECTION_FAIL);
                    log.warn("重定向到长链接失败,长链值为空,压缩码:{}", context.getCompressionCode());
                }
            } catch (Exception e) {
                log.error("重定向到长链接[{}]失败,压缩码:{}", longUrl, context.getCompressionCode(), e);
                context.setTransformStatus(TransformStatus.REDIRECTION_FAIL);
            }
        }
    }
}