package com.charles.cloud.common.log.event;

import com.charles.cloud.admin.api.entity.SysLog;
import com.charles.cloud.admin.api.feign.RemoteLogService;
import com.charles.cloud.common.core.constant.SecurityConstants;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 异步监听日志事件
 *
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
@AllArgsConstructor
public class SysLogListener {
    private final RemoteLogService remoteLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent sysLogEvent) {
        SysLog sysLog = (SysLog) sysLogEvent.getSource();
        remoteLogService.saveLog(sysLog, SecurityConstants.FROM_IN);
    }
}
