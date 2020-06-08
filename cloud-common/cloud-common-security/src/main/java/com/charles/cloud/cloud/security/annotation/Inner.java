package com.charles.cloud.cloud.security.annotation;

/**
 * 服务调用不鉴权注解
 *
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
public @interface Inner {
    /**
     * 是否AOP统一处理
     *
     * @return boolean
     */
    boolean value() default true;

    /**
     * 需要特殊判空的字段
     *
     * @return String[]
     */
    String[] field() default {};
}
