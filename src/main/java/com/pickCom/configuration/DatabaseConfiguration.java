package com.pickCom.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {
    /*@Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikatiConfig hikariConfig() {
        return new HikatiConfig();
    }*/
}
