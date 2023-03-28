package com.github.yakami.task;

import com.github.yakami.library.infrastructure.common.base.BaseApplication;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@Slf4j
@EnableDiscoveryClient
public class TaskApplication extends BaseApplication {

    public static void main(String[] args) {
        initialization(args, TaskApplication.class);
    }

}
