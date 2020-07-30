package com.edwin.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * Web mvc configuration
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
    @SneakyThrows
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        File pathRoot = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!pathRoot.exists()) {
            pathRoot = new File("");
        }
        String saveFile = "file:" + pathRoot.getAbsolutePath() +"/static/";

//        String s = "file:" + ResourceUtils.getURL("classpath:").getPath() + "/";
//        registry.addResourceHandler("/photos/**").addResourceLocations(s);
//        registry.addResourceHandler("/image/**").addResourceLocations(s);
//        registry.addResourceHandler("/image/**").addResourceLocations("file:/Users/edwin/Desktop/static/");

        registry.addResourceHandler("/photos/**").addResourceLocations(saveFile.toString());

        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
