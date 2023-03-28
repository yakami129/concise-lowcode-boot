package com.github.yakami.library.infrastructure.swagger;

import io.swagger.models.auth.In;
import org.jetbrains.annotations.NotNull;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwaggerService {

    private ApiInfo getApiInfo(String appName) {
        StringVendorExtension extension = new StringVendorExtension("serviceName", appName);
        return new ApiInfoBuilder()
                .title("标题：" + appName + "使用Swagger3构建API接口文档")
                .description("描述：用于" + appName + "接口查看")
                .contact(getContact())
                .extensions(Collections.singletonList(extension))
                .version("版本号：3.0")
                .build();
    }

    @NotNull
    private Contact getContact() {
        return new Contact("lowcode", "", "");
    }

    private List<SecurityScheme> getPars(String jkUserInfo) {
        List<SecurityScheme> list = new ArrayList<>();
        ApiKey apiKey = new ApiKey(jkUserInfo, jkUserInfo, In.HEADER.toValue());
        list.add(apiKey);
        return list;
    }

    public Docket getDocket(String appName, String packagePath) {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(getApiInfo(appName))
                .select()
                .apis(RequestHandlerSelectors.basePackage(packagePath))
                .paths(PathSelectors.any())
                .build();
    }
}
