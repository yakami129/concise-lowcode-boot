package com.github.yakami.task.handler.impl;

import com.github.yakami.task.handler.AbstractExternalTaskHandler;
import com.github.yakami.task.interceptor.core.ExternalTaskResponse;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by alan on 2023/3/27.
 */
@Slf4j
@Component
@ExternalTaskSubscription(topicName = "notion-topic", includeExtensionProperties = true)
public class NotionExternalTaskHandler extends AbstractExternalTaskHandler {

    @Override
    public ExternalTaskResponse run(ExternalTask externalTask,
                                    ExternalTaskService externalTaskService,
                                    Map<String, Object> allVariables,
                                    Map<String, String> extensionProperties) {
        ExternalTaskResponse externalTaskResponse = ExternalTaskResponse.builder().build();
        externalTaskResponse.addVariable("notion", "notion");
        return externalTaskResponse;
    }

}
