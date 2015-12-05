package com.aly.controller;

import com.googlecode.flyway.core.Flyway;

import java.util.Properties;

/**
 * MySQL Database Migration.
 */
public class FlywayDBMigration {
    /**
     * 清空76的MySQL数据库(alyweb)。
     */
    public static void clean76MysqlDBTables() {
        Properties properties = new Properties();
        properties.put("flyway.driver", "com.mysql.jdbc.Driver");
        properties.put("flyway.url", "jdbc:mysql://192.168.0.76:3306/alyweb?useUnicode=true&characterEncoding=utf-8");

        properties.put("flyway.user", "alyweb");
        properties.put("flyway.password", "yrr7pIdA");

        Flyway flyway = new Flyway();
        flyway.setValidateOnMigrate(true);
        flyway.configure(properties);
        flyway.clean();//清空数据库——包括所有的表结构
//        flyway.init();//初始化Flyway的schema表
        flyway.migrate();//执行数据库迁移，即resources目录下db.migration文件夹中的各个脚本
    }

    /**
     * 清空本地MySQL数据库(alyweb)。
     */
    public static void cleanLocalMysqlDBTables() {
        Properties properties = new Properties();
        properties.put("flyway.driver", "com.mysql.jdbc.Driver");
        properties.put("flyway.url", "jdbc:mysql://127.0.0.1:3306/alyweb?useUnicode=true&characterEncoding=utf-8");

        properties.put("flyway.user", "root");
        properties.put("flyway.password", "root");

        Flyway flyway = new Flyway();
        flyway.setValidateOnMigrate(true);
        flyway.configure(properties);
        flyway.clean();//清空数据库——包括所有的表结构
//        flyway.init();//初始化Flyway的schema表
        flyway.migrate();//执行数据库迁移，即resources目录下db.migration文件夹中的各个脚本
    }

    public static void main(String[] args) {
//        clean76MysqlDBTables();
        cleanLocalMysqlDBTables();
    }
}
