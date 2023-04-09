package cc.forim.armagin.server.action;

import cc.forim.armagin.server.infra.enums.HttpHeaderEnum;
import cc.forim.armagin.server.infra.enums.TransformEnum;
import cc.forim.armagin.server.infra.utils.IpUtil;
import cc.forim.armagin.server.pipeline.BusinessProcess;
import cc.forim.armagin.server.pipeline.TransformContext;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 【1】Header处理流程
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/17 17:22
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HeaderAction implements BusinessProcess {

    private static final String EMPTY = "";
    @Override
    public void process(TransformContext context) {
        try {
            // 获取User-Agent和Cookie并添加到上下文参数
            String ua = context.getHeader(HttpHeaderEnum.UA.getValue());
            String cookie = context.getHeader(HttpHeaderEnum.COOKIES.getValue());

            if(StrUtil.hasBlank(cookie)) {
                cookie = EMPTY;
            }
            context.setParam(TransformEnum.UA.getValue(), ua);
            context.setParam(TransformEnum.COOKIE.getValue(), cookie);

            // 获取客户端主机名并添加到上下文参数
            String clientHostName = context.getParam(TransformEnum.RHN.getValue());
            context.setParam(TransformEnum.CLIENT_ID.getValue(), IpUtil.X.extractClientIp(context.getHeaders(), clientHostName));
        } finally {
            // 释放上下文的header
            context.releaseHeaders();
        }
    }
}