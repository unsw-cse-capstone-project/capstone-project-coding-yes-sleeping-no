package com.edwin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("Edwin").select().build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("Edwin", "https://github.com/EdwinInAu", "wcs2019inau@gmail.com");

        return new ApiInfo(
                "coding yes slepping no",
                "back end api documentation",
                "1.0",
                "wcs2019inau@gmail.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }

}
