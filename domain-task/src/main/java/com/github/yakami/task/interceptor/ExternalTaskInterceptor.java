package com.github.yakami.task.interceptor;

import com.github.yakami.task.interceptor.core.ExternalTaskResponse;
import com.github.yakami.task.interceptor.core.ExternalTaskTargetInvocation;

/**
 * Created by alan on 2023/3/27.
 * 拦截器接口
 */
public interface ExternalTaskInterceptor {

    /**
     * 执行拦截器
     *
     * @param externalTaskTargetInvocation 拦截器调用模块
     * @return
     */
    ExternalTaskResponse interceptor(ExternalTaskTargetInvocation externalTaskTargetInvocation);

}
