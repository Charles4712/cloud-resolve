package com.charles.cloud.cloud.security.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * 认证授权相关工具类
 *
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/8
 */
@UtilityClass
public class AuthUtils {
    private final String BASIC_ = "Basic ";

    /**
     * 从header 请求中的clientId/clientSecret
     *
     * @param header header中的参数
     * @return String[]
     */
    public String[] extractAndDecodeHeader(String header) {

        byte[] base64Token = header.substring(6).getBytes(CharsetUtil.CHARSET_UTF_8);
        byte[] decode = Base64.decode(base64Token);
        String token = new String(decode, CharsetUtil.CHARSET_UTF_8);
        return StringUtils.split(token, ":");
    }

    /**
     * 从header 请求中的clientId/clientSecret
     *
     * @param request 请求对象
     * @return String[]
     */
    public String[] extractAndDecodeHeader(ServerHttpRequest request) {
        String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(BASIC_)) {
            throw new RuntimeException("请求头中client信息为空");
        }

        return extractAndDecodeHeader(header);
    }
}
