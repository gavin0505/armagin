package cc.forim.armagin.server.filter;

import cc.forim.armagin.server.infra.spring.WebFluxServerResponseWriter;
import cc.forim.armagin.server.infra.utils.IpUtil;
import cc.forim.armagin.server.infra.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

import static cc.forim.armagin.server.infra.enums.CacheKeyEnum.BLACK_IP;

/**
 * 【2】黑名单IP拦截器
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/23 12:45
 */

@Order(Integer.MIN_VALUE + 2)
@Component
@Slf4j
public class BlackListIpFilter implements WebFilter {

    @Resource
    private WebFluxServerResponseWriter webFluxServerResponseWriter;

    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    @Value("${armagin.server.common.errorUrl}")
    private String errorUrl;

    @Override
    @NotNull
    public Mono<Void> filter(@NotNull ServerWebExchange exchange, @NotNull WebFilterChain chain) {

        // 获取请求的ip
        ServerHttpRequest request = exchange.getRequest();
        String clientIp = IpUtil.X.extractClientIp(request);
        
        // 若请求ip不在黑名单，则继续执行
        if (!redisUtil.sHasKey(BLACK_IP.getKey(), clientIp)) {
            return chain.filter(exchange);
        }
        // 否则重定向到错误页面
        log.warn("请求异常,命中IP黑名单[i:{}:{}],跳转到error页面", clientIp, request.getURI());
        return webFluxServerResponseWriter.redirect(exchange, errorUrl);
    }
}