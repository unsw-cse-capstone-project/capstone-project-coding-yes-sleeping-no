package com.edwin.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
        String s = ResourceUtils.getURL("classpath:").getPath() + "/**";
        registry.addResourceHandler("/photos/**").addResourceLocations(s);
//        registry.addResourceHandler("/image/**").addResourceLocations("/Users/edwin/capstone-project-coding-yes-sleeping-no/target/classes/static/photos/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
