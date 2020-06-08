package com.charles.cloud.common.log.init;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 通过环境变量的形式注入
 * 自动维护 Spring Boot Admin Logger Viewer
 *
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
public class ApplicationLoggerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String applicationName = environment.getProperty("spring.application.name");
        String logBase = environment.getProperty("LOGGING_PATH", "logs");
        System.setProperty("logging.file.name", String.format("%s/%s/debug.log", logBase, applicationName));
    }
}
