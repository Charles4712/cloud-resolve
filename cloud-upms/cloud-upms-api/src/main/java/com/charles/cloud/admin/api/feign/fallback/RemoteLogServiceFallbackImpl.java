package com.charles.cloud.admin.api.feign.fallback;

import com.charles.cloud.admin.api.entity.SysLog;
import com.charles.cloud.admin.api.feign.RemoteLogService;
import com.charles.cloud.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
@Slf4j
@Component
public class RemoteLogServiceFallbackImpl implements RemoteLogService {
    @Setter
    Throwable cause;

    @Override
    public R<Boolean> saveLog(SysLog sysLog, String from) {
        log.error("feign 插入日志失败", cause);
        return null;
    }
}
