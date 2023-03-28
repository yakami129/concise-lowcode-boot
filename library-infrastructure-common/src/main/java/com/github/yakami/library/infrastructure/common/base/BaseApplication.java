package com.github.yakami.library.infrastructure.common.base;

import com.github.yakami.library.infrastructure.common.env.LoadLocalEnvConfigFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by alan on 2022/12/1.
 */
@SpringBootApplication(scanBasePackages = {"com.github.yakami"})
public class BaseApplication {

    public static void initialization(String[] args, Class<?> clazz) {
        LoadLocalEnvConfigFactory.loadLocalEnv();
        SpringApplication.run(clazz, args);
    }

}
