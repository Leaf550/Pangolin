package com.yuheng.pangolin.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Component
public class ImagesWebConfig implements WebMvcConfigurer {

    private final ImagePathConfig imagePathConfig;

    @Autowired
    ImagesWebConfig(
            ImagePathConfig imagePathConfig
    ) {
        this.imagePathConfig = imagePathConfig;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry.addResourceHandler(
                imagePathConfig.getBasePath() + imagePathConfig.getBbsPath() + "**"
                )
                .addResourceLocations("file:" + imagePathConfig.getLocalPath() + imagePathConfig.getBbsPath());

        registry.addResourceHandler(
                        imagePathConfig.getBasePath() + imagePathConfig.getProfilePath() + "**"
                )
                .addResourceLocations("file:" + imagePathConfig.getLocalPath() + imagePathConfig.getProfilePath());
    }
}
