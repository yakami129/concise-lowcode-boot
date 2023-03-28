package com.github.yakami.task.interceptor.Impl;

import com.alibaba.fastjson2.JSON;
import com.github.yakami.task.interceptor.ExternalTaskInterceptor;
import com.github.yakami.task.interceptor.core.ExternalTaskResponse;
import com.github.yakami.task.interceptor.core.ExternalTaskTargetInvocation;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by alan on 2023/3/27.
 * 日志拦截器
 */
@Slf4j
public class LogInterceptorImpl implements ExternalTaskInterceptor {

    @Override
    public ExternalTaskResponse interceptor(ExternalTaskTargetInvocation externalTaskTargetInvocation) {

        if (log.isDebugEnabled()) {
            log.debug("[BIZ] # ExternalTaskHandler Start # externalTaskRequest:{}", JSON.toJSONString(externalTaskTargetInvocation.getExternalTaskRequest()));
        }

        final ExternalTaskResponse taskResponse = externalTaskTargetInvocation.invoke();

        if (log.isDebugEnabled()) {
            log.debug("[BIZ] # ExternalTaskHandler End # externalTaskResponse:{}", JSON.toJSONString(taskResponse));
        }

        return taskResponse;
    }

}
