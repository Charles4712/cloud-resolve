package com.charles.cloud.common.log;

import com.charles.cloud.admin.api.feign.RemoteLogService;
import com.charles.cloud.common.log.aspect.SysLogAspect;
import com.charles.cloud.common.log.event.SysLogListener;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
@EnableAsync
@RequiredArgsConstructor
@ConditionalOnWebApplication
@Configuration(proxyBeanMethods = false)
public class LogAutoConfiguration {
    private final RemoteLogService remoteLogService;

    /**
     * 异步监听日志事件
     *
     * @return SysLogListener
     */
    @Bean
    public SysLogListener sysLogListener() {
        return new SysLogListener(remoteLogService);
    }

    /**
     * 日志aop
     *
     * @return SysLogAspect
     */
    @Bean
    public SysLogAspect sysLogAspect() {
        return new SysLogAspect();
    }
}
