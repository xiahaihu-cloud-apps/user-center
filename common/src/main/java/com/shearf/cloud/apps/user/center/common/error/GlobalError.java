package com.shearf.cloud.apps.user.center.common.error;

import com.shearf.cloud.apps.commons.foundation.error.IError;
import lombok.AllArgsConstructor;

/**
 * @author xiahaihu2009@gmai.com
 */
@AllArgsConstructor
public enum GlobalError implements IError {

    /**
     * 数据验证错误
     */
    VALIDATE_ERROR(-10001, "数据验证错误"),

    /**
     * csrf验证失败
     */
    INVALID_CSRF(-10002, "invalid csrf"),

    /**
     * 验证码不匹配
     */
    CAPTCHA_NOT_MATCH(-10003, "验证码不匹配"),

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
