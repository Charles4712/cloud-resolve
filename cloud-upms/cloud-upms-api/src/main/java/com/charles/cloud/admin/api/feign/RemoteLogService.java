package com.charles.cloud.admin.api.feign;

import com.charles.cloud.admin.api.entity.SysLog;
import com.charles.cloud.admin.api.feign.factory.RemoteLogServiceFallbackFactory;
import com.charles.cloud.common.core.constant.SecurityConstants;
import com.charles.cloud.common.core.constant.ServiceNameConstants;
import com.charles.cloud.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.UMPS_SERVICE, fallbackFactory = RemoteLogServiceFallbackFactory.class)
public interface RemoteLogService {
    @PostMapping("/log")
    R<Boolean> saveLog(@RequestBody SysLog sysLog, @RequestHeader(SecurityConstants.FROM) String from);
}
