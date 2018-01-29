package com.shearf.cloud.apps.user.center.common.error;

import lombok.AllArgsConstructor;

/**
 * @author xiahaihu2009@gmai.com
 */
@AllArgsConstructor
public enum GlobalError implements Error {

    /**
     * 数据验证错误
     */
    VALIDATE_ERROR(-10001, "数据验证错误"),

    INVALID_CSRF(-10002, "invalid csrf"),

    ;

    private int code;

    private String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
