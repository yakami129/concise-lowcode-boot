package com.github.yakami.library.infrastructure.common.emuns;

/**
 * Created by alan on 2022/6/9.
 */
public enum ResultCodeEnum {

    SUCCESS(200, "flow.http.success"),
    FAIL(500, "flow.http.fail"),
    UNAUTHORIZED(401, "flow.http.unauthorized");

    private Integer code;

    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
