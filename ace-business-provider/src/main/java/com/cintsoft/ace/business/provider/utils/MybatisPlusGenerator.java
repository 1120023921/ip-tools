package com.cintsoft.ace.business.provider.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisPlusGenerator {

    private final static String packageName = "com.cintsoft.ace.business.provider.business";
    private final static String dbUserName = "root";
    private final static String dbPassword = "!Cint@2019#hz";
    private final static String dbUrl = "jdbc:mysql://mariadb.cintsoft.com:3308/cloud_app_demo";

    @Test
    public void getTable() {

        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
        generateByTables(DbType.MYSQL, serviceNameStartWithI, packageName, "ace-business-provider-api", "visit_sys_menu");
    }

    private static Map<DbType, String> driverMap = new HashMap<DbType, String>() {
        {
            put(DbType.MYSQL, "com.mysql.cj.jdbc.Driver");
            put(DbType.ORACLE, "oracle.jdbc.driver.OracleDriver");
        }
    };

    private static void generateByTables(DbType dbType, boolean serviceNameStartWithI, String packageName, String apiName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String basePath = System.getProperty("user.dir") + "/src/main/";
        String baseBizPath = System.getProperty("user.dir").substring(0, System.getProperty("user.dir").indexOf("\\application")) + "/application/" + apiName + "/src/main/";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(dbType)
                .setUrl(dbUrl)
                .setUsername(dbUserName)
                .setPassword(dbPassword)
                .setDriverName(driverMap.get(dbType));
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
//                .setDbColumnUnderline( true )
                .setRestControllerStyle(true)

                .setTablePrefix("info")
                .setVersionFieldName("version")
                .setControllerMappingHyphenStyle(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setAuthor("胡昊")
                .setIdType(IdType.ASSIGN_ID)
                .setSwagger2(true)
                .setOutputDir(basePath + "java")
                .setFileOverride(false);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return basePath + "java/com/cintsoft/ace/business/provider/business/dao/" + tableInfo.getEntityName() + ".xml";
            }
        });
        // entity
        focList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseBizPath + "java/com/cintsoft/ace/business/provider/business/model/" + tableInfo.getEntityName() + ".java";
            }
        });
//        controller生成目录
//        focList.add( new FileOutConfig("/templates/controller.java.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return baseWebPath+"java/com/sanke/ding/manage/controller/" + tableInfo.getEntityName() + "Controller.java";
//            }
//        } );


        cfg.setFileOutConfigList(focList);

        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        tc.setEntity(null);

//         如上任何一个模块如果设置 空 OR Null 将不生成该模块。

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setEntity("model")
                                .setService("service")
                                .setServiceImpl("service.impl")
                                .setMapper("dao")
                                .setController("controller")
                ).setTemplate(tc).setCfg(cfg).execute();
    }

    private void generateByTables(DbType dbType, String packageName, String apiName, String... tableNames) {
        generateByTables(dbType, true, packageName, apiName, tableNames);
    }

}
