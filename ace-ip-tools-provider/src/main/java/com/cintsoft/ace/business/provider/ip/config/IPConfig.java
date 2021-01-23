package com.cintsoft.ace.business.provider.ip.config;

import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IPConfig {

    @Value("${ip.db-file}")
    private String dbFile;

    @Bean
    public DbSearcher dbSearcher() {
        try {
            final DbConfig config = new DbConfig();
            return new DbSearcher(config, dbFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
