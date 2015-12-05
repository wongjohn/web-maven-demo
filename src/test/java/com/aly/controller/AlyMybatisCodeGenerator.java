package com.aly.controller;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.NullProgressCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 用来从数据库表结构生成Mybatis相关的Domain/Dao/Mapper XML等相关文件.
 * 数据库连接、生成的文件目录等内容，请在generator.xml文件中进行配置。
 */
public class AlyMybatisCodeGenerator {
    public static void main(String[] args) throws Exception{
        FlywayDBMigration.cleanLocalMysqlDBTables();

        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);

        File configurationFile = new File("src/test/resources/generator.xml");
        Configuration config = cp.parseConfiguration(configurationFile);

        DefaultShellCallback shellCallback = new DefaultShellCallback(true);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);
        myBatisGenerator.generate(new NullProgressCallback());
    }
}
