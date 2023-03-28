package com.github.yakami.platform.gateway;

import com.github.yakami.library.infrastructure.common.base.BaseApplication;
import com.github.yakami.library.infrastructure.common.env.LoadLocalEnvConfigFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
public class GatewayApplication extends BaseApplication {

    public static void main(String[] args) {
        LoadLocalEnvConfigFactory.loadLocalEnv();
        SpringApplication.run(GatewayApplication.class, args);
    }

}
