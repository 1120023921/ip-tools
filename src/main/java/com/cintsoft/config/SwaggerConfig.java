package com.cintsoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * @author 胡昊
 * Description: Mybatis配置
 * Date: 2020/7/23
 * Time: 21:21
 * Mail: huhao9277@gmail.com
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "华智单体项目接口文档",
                "华智单体应用框架",
                "API V1.0",
                "http://cintsoft.com",
                new Contact("胡昊", "http://cintsoft.com", "huhao9277@gmail.com"),
                "Apache", "http://www.apache.org/", Collections.emptyList());
    }
}
