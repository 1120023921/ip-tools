package com.cintsoft.ace.business.provider.config;

import com.cintsoft.ace.business.provider.config.bean.WebConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private WebConfig webConfig;

    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        if (StringUtils.hasText(webConfig.getMapping()) && StringUtils.hasText(webConfig.getUpload())) {
            registry.addResourceHandler(webConfig.getMapping()).addResourceLocations(webConfig.getUpload());
        }
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("*")
//                .allowedHeaders("*")
//                .exposedHeaders("access-control-allow-headers",
//                        "access-control-allow-methods",
//                        "access-control-allow-origin",
//                        "access-control-max-age",
//                        "X-Frame-Options")
//                .allowCredentials(true).maxAge(3600);
//    }
}
