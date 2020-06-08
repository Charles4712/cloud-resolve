package com.charles.cloud.common.datasource.config;

import com.baomidou.dynamic.datasource.processor.DsProcessor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;

/**
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
public class LastParamDsProcessor extends DsProcessor {
    private static final String LAST_PREFIX = "#last";

    /**
     * 抽象匹配条件 匹配才会走当前执行器否则走下一级执行器
     *
     * @param key DS注解里的内容
     * @return 是否匹配
     */
    @Override
    public boolean matches(String key) {
        return StringUtils.startsWith(key, LAST_PREFIX);
    }

    /**
     * 抽象最终决定数据源
     *
     * @param methodInvocation 方法执行信息
     * @param key              DS注解里的内容
     * @return 数据源名称
     */
    @Override
    public String doDetermineDatasource(MethodInvocation methodInvocation, String key) {
        Object[] arguments = methodInvocation.getArguments();
        return String.valueOf(arguments[arguments.length - 1]);
    }
}
