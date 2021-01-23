package com.cintsoft.ace.business.provider.config.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author 胡昊
 * Description: 虚拟目录配置
 * Date: 2020/7/31
 * Time: 10:30
 * Mail: huhao9277@gmail.com
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "web")
public class WebConfig {

    private String upload;

    private String mapping;
}
