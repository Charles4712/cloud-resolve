package com.charles.cloud.common.log.util;

import cn.hutool.http.HttpUtil;
import com.charles.cloud.admin.api.entity.SysLog;
import com.charles.cloud.common.core.constant.CommonConstants;
import com.charles.cloud.common.core.request.ReactiveRequestContextHolder;
import lombok.experimental.UtilityClass;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
@UtilityClass
public class SysLogUtils {
    /**
     * 获取系统日志
     *
     * @return SysLog
     */
    public SysLog getSysLog() {
        ServerHttpRequest request = ReactiveRequestContextHolder.getRequest().block();

        SysLog sysLog = new SysLog();
        sysLog.setCreateBy(Objects.requireNonNull(getUsername()));
        sysLog.setType(CommonConstants.STATUS_NORMAL);
        sysLog.setRemoteAddr(request.getRemoteAddress().getHostName());
        sysLog.setRequestUri(request.getPath().value());
        sysLog.setMethod(request.getMethodValue());
        List<String> list = request.getHeaders().get("user-agent");
        String userAgent = Optional.ofNullable(list).map(li -> list.get(0)).orElse("");
        sysLog.setUserAgent(userAgent);
        sysLog.setParams(HttpUtil.toParams(request.getQueryParams()));
        sysLog.setServiceId(getClientId());
        return sysLog;
    }

    /**
     * 获取客户端
     *
     * @return clientId
     */
    private String getClientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
            return auth2Authentication.getOAuth2Request().getClientId();
        }
        return null;
    }

    /**
     * 获取用户名称
     *
     * @return username
     */
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }
}
