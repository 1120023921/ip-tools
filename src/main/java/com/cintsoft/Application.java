package com.cintsoft;

import com.cintsoft.common.utils.jwt.JwtTokenUtil;
import com.cintsoft.config.bean.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtTokenUtil.class, WebConfig.class})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}
