package com.cintsoft.ace.business.provider;

import com.cintsoft.ace.business.provider.config.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
@EnableConfigurationProperties({WebConfig.class})
public class IPApplication {

    public static void main(String[] args) {
        SpringApplication.run(IPApplication.class, args);
    }
}
