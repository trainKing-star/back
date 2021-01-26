package com.service.textmessage.config;


import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Bean
    public Docket apiDocket(Environment environment){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("A")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.service.textmessage.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket apiDocket2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("B");
    }

    @Bean
    public Docket apiDocket3(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("C");
    }


    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("浩东","https://www.haodong.com/","1193299045@qq.com");
        return new ApiInfo(
                "服务外包项目Api",
                "一个简单的可调式的Api文档，详情请询问浩东",
                "1.0v",
                "",
                contact,
                "",
                "",
                new ArrayList<>()
        );
    }
}

