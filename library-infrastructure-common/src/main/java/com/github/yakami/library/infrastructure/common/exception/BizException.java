package com.github.yakami.library.infrastructure.common.exception;

/**
 * Created by alan on 2022/12/1.
 */
public class BizException extends BaseException {

    public BizException(String message, Object... o) {
        super(message, o);
    }

}
