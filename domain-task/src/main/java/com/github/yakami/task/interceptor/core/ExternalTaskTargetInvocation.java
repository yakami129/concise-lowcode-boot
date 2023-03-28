package com.github.yakami.task.interceptor.core;

import com.github.yakami.task.interceptor.ExternalTaskInterceptor;

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by alan on 2023/3/27.
 * <p>
 * 用于控制前置或者后者调用拦截器
 */
public class ExternalTaskTargetInvocation {

    private ExternalTaskInterceptorFactory externalTaskInterceptorFactory;
    private List<ExternalTaskInterceptor> interceptorList;
    private Iterator<ExternalTaskInterceptor> interceptors;
    private Supplier<ExternalTaskResponse> externalTaskTarget;
    private ExternalTaskRequest externalTaskRequest;

    public ExternalTaskTargetInvocation(ExternalTaskInterceptorFactory externalTaskInterceptorFactory, ExternalTaskRequest externalTaskRequest) {
        this.externalTaskInterceptorFactory = externalTaskInterceptorFactory;
        this.externalTaskRequest = externalTaskRequest;
        interceptorList = externalTaskInterceptorFactory.getExternalTaskInterceptors();
        interceptors = externalTaskInterceptorFactory.getExternalTaskInterceptors().iterator();
    }

    public ExternalTaskResponse invoke() {
        if (interceptors.hasNext()) {
            final ExternalTaskInterceptor next = interceptors.next();
            return next.interceptor(this);
        } else {
            return externalTaskTarget.get();
        }
    }

    public void setExternalTaskTarget(Supplier<ExternalTaskResponse> externalTaskTarget) {
        this.externalTaskTarget = externalTaskTarget;
    }

    public ExternalTaskRequest getExternalTaskRequest() {
        return externalTaskRequest;
    }
}
