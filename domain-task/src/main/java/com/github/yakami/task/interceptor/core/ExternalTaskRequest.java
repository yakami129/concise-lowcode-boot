package com.github.yakami.task.interceptor.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

import java.util.Map;

/**
 * Created by alan on 2023/3/27.
 * 执行目标请求参数
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExternalTaskRequest {

    private ExternalTask externalTask;
    private ExternalTaskService externalTaskService;
    private Map<String, Object> allVariables;
    private Map<String, String> extensionProperties;

}
