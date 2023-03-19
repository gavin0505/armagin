package cc.forim.armagin.server.infra.spring;

import cc.forim.armagin.server.exceptions.RedirectToErrorPageException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 重定向异常处理器
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/19 13:45
 */
@Component
public class WebRedirectErrorHandler implements ErrorWebExceptionHandler {

    @Value("${armagin.server.common.errorUrl}")
    private String errorUrl;
    @Resource
    private WebFluxServerResponseWriter webFluxServerResponseWriter;

    @Override
    public Mono<Void> handle(@NotNull ServerWebExchange exchange, @NotNull Throwable ex) {
        if (ex instanceof RedirectToErrorPageException) {
            // 重定向到错误页面
            return webFluxServerResponseWriter.redirect(
                    exchange,
                    errorUrl
            );
        }
        // todo 改为非空
        return null;
    }
}