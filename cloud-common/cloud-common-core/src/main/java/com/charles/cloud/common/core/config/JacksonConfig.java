package com.charles.cloud.common.core.config;

import cn.hutool.core.date.DatePattern;
import com.charles.cloud.common.core.jackson.CustomizerJavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * JacksonConfig
 *
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfig {
    /**
     * jackson 自定义配置
     *
     * @return Jackson2ObjectMapperBuilderCustomizer
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.locale(Locale.CHINA)
                    .timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
                    .simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN)
                    .modules(new CustomizerJavaTimeModule());
        };
    }
}
