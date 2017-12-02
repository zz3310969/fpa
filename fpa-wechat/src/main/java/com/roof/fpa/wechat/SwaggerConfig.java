package com.roof.fpa.wechat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //必须存在
@EnableSwagger2 //必须存在
@EnableWebMvc //必须存在
@ComponentScan(basePackages = { "com.roof.fpa","org.roof.web.user.action" })
//@ComponentScan(basePackages = {"com.roof.vote"}) //必须存在 扫描的API Controller package name 也可以直接扫描class (basePackageClasses)
public class SwaggerConfig {
/*    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.roof.vote"))
                .paths(PathSelectors.ant("**"))
                .build();
        *//*return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());*//*
    }*/

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()

                .apis(RequestHandlerSelectors.basePackage("com.roof.fpa"))
                //.apis(RequestHandlerSelectors.basePackage("org.roof.web.cache.controller"))
                .paths(PathSelectors.any())
                //.paths(PathSelectors.ant("/cache/controller/*"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Fpa", "http://www.cnblogs.com/getupmorning/", "zhaoming0018@126.com");
        return new ApiInfoBuilder()
                .title("前台API接口")
                .description("前台API接口")
                .contact(contact)
                .version("1.1.0")
                .build();
    }
}