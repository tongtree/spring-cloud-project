package com.tong.consume;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class MybatisPlusGenerator {

    @Test
    public void run() {
        // 数据库连接url
        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8";
        // 数据库用户名和密码
        String username = "root";
        String password = "root";
        // 代码生成模块地址
        String moudlePath = System.getProperty("user.dir") + "/src/main/java";
        // xml文件生成地址
        String xmlPath = System.getProperty("user.dir") + "/src/main/resources/mapper";
        // 需要生成的数据表
        String[] tableArr = new String[]{
                "user"
        };

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("niutong") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir()
                            .dateType(DateType.ONLY_DATE)
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(moudlePath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.tong.consume") // 设置模块名
                            .mapper("mapper") // 设置mapper包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, xmlPath)); // 设置xml文件生成目录
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableArr) // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .entityBuilder()
                            .enableLombok()
                            .serviceBuilder()
                            .formatServiceFileName("%sService"); // //去掉Service接口的首字母I
                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER); // 设置禁止生成controller
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
