package com.shls.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 04/09/2017.
 */
@Configuration
@EnableSwagger2
public class MySwaggerConfig
{

    private static List<String> filterPathList;

    static{
        filterPathList = new ArrayList<>();

    }


    @Bean
    public Docket ProductApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                //.paths(s -> !filterPathList.contains(s))
                .build()
                .apiInfo(productApiInfo());
    }

    private ApiInfo productApiInfo() {
        ApiInfo apiInfo = new ApiInfo("中登网服务对外接口API文档",
                "中登网服务对外接口API文档",
                "v0.1",
                "API TERMS URL",
                "song@tj-factoring.com",
                "license",
                "license url");
        return apiInfo;
    }
}