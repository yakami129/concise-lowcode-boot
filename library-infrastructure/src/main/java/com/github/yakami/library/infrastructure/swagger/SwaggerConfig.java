package com.github.yakami.library.infrastructure.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;


@Slf4j
@Configuration
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String appName;
    @Value("${project.packagePath}")
    private String packagePath;

    @Bean
    @ConditionalOnProperty(value = "project.packagePath")
    public Docket swaggerDocket() {
        SwaggerService swaggerService = new SwaggerService();
        log.info("Swagger Initializing packagePath '{}'", packagePath);
        return swaggerService.getDocket(appName, packagePath);
    }
}
