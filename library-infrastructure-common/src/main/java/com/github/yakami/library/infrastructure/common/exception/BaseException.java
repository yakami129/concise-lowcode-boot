package com.github.yakami.library.infrastructure.common.exception;

import java.text.MessageFormat;

/**
 * Created by alan on 2022/6/9.
 * 基础业务异常类
 */
public class BaseException extends RuntimeException {

    public BaseException(String message, Object... o) {
        super(MessageFormat.format(message, o));
    }

}
