package com.shearf.cloud.apps.user.center.common.error;

import com.shearf.cloud.apps.commons.foundation.error.IError;
import lombok.AllArgsConstructor;


/**
 * @author xiahaihu2009@gmail.com
 */

@AllArgsConstructor
public enum  UserError implements IError {

    /**
     * 登录失败
     */
    LOGIN_FAIL(-20001, "登录失败"),

    /**
     * 密码与重复密码不匹配
     */
    REGISTER_PASSWORD_NOT_MATCH(-20002, "密码与重复密码不匹配"),

    /**
     * Email地址已经注册
     */
    REGISTER_USER_FAIL_EMAIL_ALREADY_EXISTS(-20003, "该email地址已经被注册"),

    /**
     * 验证码不正确
     */
    REGISTER_USER_FAIL_CAPTCHA_NOT_MATCH(-20004, "验证码不正确"),

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
