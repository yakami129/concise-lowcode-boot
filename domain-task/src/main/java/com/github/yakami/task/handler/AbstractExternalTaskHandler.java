package com.github.yakami.task.handler;

import com.alibaba.fastjson2.JSONObject;
import com.github.yakami.task.interceptor.core.ExternalTaskInterceptorFactory;
import com.github.yakami.task.interceptor.core.ExternalTaskRequest;
import com.github.yakami.task.interceptor.core.ExternalTaskResponse;
import com.github.yakami.task.interceptor.core.ExternalTaskTargetInvocation;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by alan on 2023/3/27.
 */
@Slf4j
public abstract class AbstractExternalTaskHandler implements ExternalTaskHandler {

    @Autowired
    public ExternalTaskInterceptorFactory externalTaskInterceptorFactory;

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        // 设置请求参数
        final ExternalTaskRequest externalTaskRequest = settingExternalTaskRequest(externalTask, externalTaskService);
        ExternalTaskTargetInvocation externalTaskTargetInvocation = new ExternalTaskTargetInvocation(externalTaskInterceptorFactory, externalTaskRequest);
        ExternalTaskResponse externalTaskResponse = null;
        try {
            // 执行操作
            externalTaskTargetInvocation.setExternalTaskTarget(() -> {
                return run(externalTask, externalTaskService, externalTask.getAllVariables(), externalTask.getExtensionProperties());
            });
            externalTaskResponse = externalTaskTargetInvocation.invoke();
        } catch (Exception ex) {

            log.warn(ex.getMessage(), ex);
            //  TODO 异常处理

        } finally {
            Map<String, Object> responseVariables = Maps.newLinkedHashMap();
            responseVariables.put("externalTaskResponse", JSONObject.toJSONString(externalTaskResponse));
            externalTaskService.complete(externalTask, responseVariables);
        }

    }

    private ExternalTaskRequest settingExternalTaskRequest(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        ExternalTaskRequest externalTaskRequest = ExternalTaskRequest
                .builder()
                .externalTask(externalTask)
                .externalTaskService(externalTaskService)
                .allVariables(externalTask.getAllVariables())
                .extensionProperties(externalTask.getExtensionProperties())
                .build();
        return externalTaskRequest;
    }

    public abstract ExternalTaskResponse run(ExternalTask externalTask,
                                             ExternalTaskService externalTaskService,
                                             Map<String, Object> allVariables,
                                             Map<String, String> extensionProperties);
}
