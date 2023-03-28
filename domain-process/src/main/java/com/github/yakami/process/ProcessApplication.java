package com.github.yakami.process;

import com.github.yakami.library.infrastructure.common.base.BaseApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
public class ProcessApplication extends BaseApplication {

    public static void main(String[] args) {
        initialization(args, ProcessApplication.class);
    }

}
