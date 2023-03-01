package com.zhi.mymall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class swaggerConfig {
    //Docket封装接口文档信息
    @Bean
    public Docket getDocket(){

        ApiInfoBuilder apiInfoBuilder=new ApiInfoBuilder();
        apiInfoBuilder.title("零食系统接口")
                .version("v 1.0.0")
                .contact(new Contact("le","","451880327@qq.com"));
        ApiInfo apiInfo=apiInfoBuilder.build();
        Docket docket=new Docket(DocumentationType.SWAGGER_2)//指定文档风格
                //指定文档封面信息
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhi.retaildemo.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
