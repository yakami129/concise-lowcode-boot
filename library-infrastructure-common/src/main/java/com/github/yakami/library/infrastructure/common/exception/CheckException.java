package com.github.yakami.library.infrastructure.common.exception;

/**
 * Created by alan on 2022/6/9.
 * 业务参数检查异常
 */
public class CheckException extends BaseException {

    public CheckException(String message, Object... o) {
        super(message, o);
    }

    /**
     * 重写此方法，直接返回，避免调用本地方法爬堆栈信息提高性能
     *
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
