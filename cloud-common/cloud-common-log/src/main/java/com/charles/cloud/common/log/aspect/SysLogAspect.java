package com.charles.cloud.common.log.aspect;

import com.charles.cloud.common.log.annotation.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
@Slf4j
@Aspect
public class SysLogAspect {
    @Around("@annotation(sysLog))")
    public Object around(ProceedingJoinPoint point, SysLog sysLog) {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        log.debug("[类名]:{},[方法]:{}", className, methodName);
        return null;
    }
}
