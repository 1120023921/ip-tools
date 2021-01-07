package com.cintsoft.ace.business.provider;

import com.cintsoft.ace.business.provider.common.utils.jwt.JwtTokenUtil;
import com.cintsoft.ace.business.provider.config.bean.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
@EnableConfigurationProperties({JwtTokenUtil.class, WebConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
