package com.charles.cloud.common.log.event;

import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 *
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
public class SysLogEvent extends ApplicationEvent {
    public SysLogEvent(Object source) {
        super(source);
    }
}
