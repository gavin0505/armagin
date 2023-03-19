package cc.forim.armagin.server.infra.spring;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;

import static org.springframework.web.cors.CorsConfiguration.ALL;

/**
 * 响应执行类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/18 23:41
 */
@Component
public class WebFluxServerResponseWriter {

    public Runnable redirectAction(ServerWebExchange exchange, String url) {
        return () -> {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FOUND);
            response.getHeaders().setLocation(URI.create(url));
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ALL);
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ALL);
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString());
        };
    }
}