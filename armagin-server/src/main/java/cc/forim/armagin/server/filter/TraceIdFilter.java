package cc.forim.armagin.server.filter;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * 【1】TraceId 拦截器
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/23 12:19
 */

@Order(Integer.MIN_VALUE + 1)
@Component
@Slf4j
public class TraceIdFilter implements WebFilter {

    @Override
    @NotNull
    public Mono<Void> filter(@NotNull ServerWebExchange exchange, @NotNull WebFilterChain chain) {
        String uuid = UUID.randomUUID().toString();
        MDC.put("TRACE_ID", uuid);
        return chain.filter(exchange).then(Mono.fromRunnable(() -> MDC.remove("TRACE_ID")));
    }
}