package com.cintsoft.ace.business.provider.config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 胡昊
 * Description: 虚拟目录配置
 * Date: 2020/7/31
 * Time: 10:30
 * Mail: huhao9277@gmail.com
 */
@Data
@ConfigurationProperties(prefix = "web")
public class WebConfig {

    private String upload;

    private String mapping;
}
