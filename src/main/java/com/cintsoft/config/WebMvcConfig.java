package com.cintsoft.config;

import com.cintsoft.config.bean.WebConfig;
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
        if (!StringUtils.isEmpty(webConfig.getMapping()) && !StringUtils.isEmpty(webConfig.getUpload())) {
            registry.addResourceHandler(webConfig.getMapping()).addResourceLocations(webConfig.getUpload());
        }
    }
}
