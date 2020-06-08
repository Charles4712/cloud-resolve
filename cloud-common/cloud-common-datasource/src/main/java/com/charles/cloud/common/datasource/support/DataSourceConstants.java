package com.charles.cloud.common.datasource.support;

/**
 * @author 周贝贝
 * @version 1.0
 * @date 2020/6/7
 */
public interface DataSourceConstants {
    /**
     * 数据源名称
     */
    String DS_NAME = "name";

    /**
     * 默认驱动
     */
    String DS_DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * 默认数据源（master）
     */
    String DS_MASTER = "master";

    /**
     * jdbc url
     */
    String DS_JDBC_URL = "url";

    /**
     * 用户名
     */
    String DS_USER_NAME = "username";

    /**
     * 密码
     */
    String DS_USER_PWD = "password";
}
