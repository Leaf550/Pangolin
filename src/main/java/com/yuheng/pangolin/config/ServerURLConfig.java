package com.yuheng.pangolin.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "server-url")
public class ServerURLConfig {
    private String protocol;
    private String host;
    private String port;
}
