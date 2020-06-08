package com.charles.cloud.common.log.annotation;

import java.lang.annotation.*;

/**
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    /**
     * 描述
     *
     * @return {String}
     */
    String value();
}
