package com.james.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class PlusGenerator {

    public static void main(String[] args) {
        //代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("JamesMybatisPlusSpringBoot");
        //生成文件的输出目录
        gc.setOutputDir(projectPath + "/src/main/java");
        //开发人
        gc.setAuthor("james");
        //是否打开输出目录
        gc.setOpen(false);
        //是否覆盖已有文件，默认false
        gc.setFileOverride(true);
        //实体属性 Swagger2 注解
        gc.setSwagger2(true);
        //mapper.xml中生成通用查询结果列
        gc.setBaseColumnList(true);
        //mapper.xml中生成通用查询映射结果
        gc.setBaseResultMap(true);
        //设置Controller、Mapper、mapper.xml等命名方式，使用默认即可。
        /*gc.setServiceName("%sService");
        gc.setControllerName();
        gc.setMapperName();
        gc.setEntityName();
        gc.setXmlName();
        gc.setServiceImplName();*/
        mpg.setGlobalConfig(gc);


        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/developer?useUnicode=true&useSSL=false&characterEncoding=utf8");
        //数据库 schema name
        // dsc.setSchemaName("public");
        //数据库驱动
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        //数据库连接用户名
        dsc.setUsername("developer");
        //数据库连接密码
        dsc.setPassword("developer");
        mpg.setDataSource(dsc);


        //包配置
        PackageConfig pc = new PackageConfig();
        //父包模块名
        pc.setModuleName("auth");
        //父包名，如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setParent("com.james.mpsb");
        //设置Controller、Mapper、mapper.xml等存放的包名
        /*pc.setController();
        pc.setEntity();
        ...*/
        mpg.setPackageInfo(pc);

        //自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        //传入模板的参数，在模板中通过cfg.servicePackage的方式来获取
        Map<String, Object> map = new HashMap<>();
        map.put("servicePackage", pc.getParent() + "." + pc.getService());
        map.put("entityPackage", pc.getParent() + "." + pc.getEntity());
        cfg.setMap(map);
        mpg.setCfg(cfg);

//        //配置模板
//        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig.setController("/Users/qian.jiang/workspace/JamesMybatisPlusSpringBoot/src/main/resources/ftl/controller.java.ftl");
//        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //是否为lombok模型，默认false
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        //需要包含的表名，允许正则表达式
        strategy.setInclude("t_ds_session");
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //表前缀
        strategy.setTablePrefix("t_ds_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}