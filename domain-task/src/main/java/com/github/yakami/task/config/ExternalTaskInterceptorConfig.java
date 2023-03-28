package com.github.yakami.task.config;

import com.github.yakami.task.interceptor.Impl.LogInterceptorImpl;
import com.github.yakami.task.interceptor.core.ExternalTaskInterceptorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alan on 2023/3/27.
 */
@Slf4j
@Configuration
public class ExternalTaskInterceptorConfig {

    @Bean
    public ExternalTaskInterceptorFactory externalTaskInterceptorFactory() {
        ExternalTaskInterceptorFactory externalTaskInterceptorFactory = new ExternalTaskInterceptorFactory();
        externalTaskInterceptorFactory.addPreInterceptor(new LogInterceptorImpl());
        return externalTaskInterceptorFactory;
    }

}
