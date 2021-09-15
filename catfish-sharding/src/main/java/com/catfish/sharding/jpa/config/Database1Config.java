package com.catfish.sharding.jpa.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by qiuxiaotong on 2021/8/22
 */
//@Data
//@ConfigurationProperties(prefix = "database1")
//@Component
//public class Database1Config {
//
//    private String url;
//    private String username;
//    private String password;
//    private String driverClassName;
//    private String databaseName;
//
//    public DataSource createDataSource() {
//        HikariDataSource result = new HikariDataSource();
//        result.setJdbcUrl(url);
//        result.setUsername(username);
//        result.setPassword(password);
//        result.setDriverClassName(driverClassName);
//        return result;
//    }
//}