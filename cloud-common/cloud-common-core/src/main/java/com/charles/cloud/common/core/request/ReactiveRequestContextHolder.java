package com.charles.cloud.common.core.request;

import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

/**
 * 获取ServerHttpRequest
 *
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
public class ReactiveRequestContextHolder {
    public static final Class<ServerHttpRequest> CONTEXT_KEY = ServerHttpRequest.class;

    /**
     * 获取请求对象
     *
     * @return
     */
    public static Mono<ServerHttpRequest> getRequest() {
        return Mono.subscriberContext()
                .map(ctx -> ctx.get(CONTEXT_KEY));
    }
}
