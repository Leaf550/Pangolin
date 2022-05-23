package com.yuheng.pangolin.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "image-path")
@Component
@Data
public class ImagePathConfig {
    private String LocalPath;
    private String basePath;
    private String bbsPath;
    private String profilePath;
}
