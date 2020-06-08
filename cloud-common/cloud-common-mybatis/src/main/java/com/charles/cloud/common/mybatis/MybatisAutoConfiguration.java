package com.charles.cloud.common.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis plus 统一配置
 *
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
@Configuration(proxyBeanMethods = false)
public class MybatisAutoConfiguration {

    /**
     * 分页插件
     *
     * @return 分页拦截器
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
