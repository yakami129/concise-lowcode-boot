package com.github.yakami.platform.security;

import com.github.yakami.library.infrastructure.common.base.BaseApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
public class SecurityApplication extends BaseApplication {

    public static void main(String[] args) {
        initialization(args, SecurityApplication.class);
    }

}
