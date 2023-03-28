package com.github.yakami.task.interceptor.core;

import com.github.yakami.task.interceptor.ExternalTaskInterceptor;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by alan on 2023/3/27.
 */
public class ExternalTaskInterceptorFactory {

    private List<ExternalTaskInterceptor> externalTaskInterceptors;

    public ExternalTaskInterceptorFactory() {
        this.externalTaskInterceptors = Lists.newLinkedList();
    }

    public void addPreInterceptor(ExternalTaskInterceptor externalTaskInterceptor) {
        externalTaskInterceptors.add(externalTaskInterceptor);
    }

    public List<ExternalTaskInterceptor> getExternalTaskInterceptors() {
        return externalTaskInterceptors;
    }


}
