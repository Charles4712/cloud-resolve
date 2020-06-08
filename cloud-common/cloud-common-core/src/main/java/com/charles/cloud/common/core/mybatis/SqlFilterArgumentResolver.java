package com.charles.cloud.common.core.mybatis;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 解决Mybatis Plus Order By SQL注入问题
 *
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
public class SqlFilterArgumentResolver implements HandlerMethodArgumentResolver {
    private final static String[] KEYWORDS = {"master", "truncate", "insert",
            "select", "delete", "update", "declare", "alter", "drop", "sleep"};

    /**
     * 判断Controller是否包含page 参数
     *
     * @param parameter 参数
     * @return 是否过滤
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Page.class);
    }

    /**
     * @param parameter      入参集合
     * @param bindingContext 要使用的绑定上下文
     * @param exchange       当前exchange
     * @return page
     */
    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
        List<String> ascs = params.get("ascs");
        List<String> descs = params.get("descs");
        String current = params.get("current").get(0);
        String size = params.get("size").get(0);
        Page<Object> page = new Page<>();
        if (StringUtils.isNotBlank(current)) {
            page.setCurrent(Long.parseLong(current));
        }

        if (StringUtils.isNotBlank(size)) {
            page.setSize(Long.parseLong(size));
        }

        List<OrderItem> orderItems = Lists.newArrayList();
        Optional.ofNullable(ascs).ifPresent(asc -> orderItems.addAll(
                asc.stream().filter(sqlInjectPredicate()).map(OrderItem::asc).collect(Collectors.toList())
        ));

        Optional.ofNullable(descs).ifPresent(desc -> orderItems.addAll(
                desc.stream().filter(sqlInjectPredicate()).map(OrderItem::asc).collect(Collectors.toList())
        ));


        return Mono.just(page);
    }

    /**
     * 判断用户输入里面有没有关键字
     *
     * @return Predicate
     */
    private Predicate<String> sqlInjectPredicate() {
        return sql -> Arrays.stream(KEYWORDS).noneMatch(keyword -> StrUtil.containsIgnoreCase(sql, keyword));
    }
}
