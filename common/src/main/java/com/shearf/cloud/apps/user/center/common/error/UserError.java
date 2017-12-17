package com.shearf.cloud.apps.user.center.common.error;

import lombok.AllArgsConstructor;


/**
 * @author xiahaihu2009@gmail.com
 */

@AllArgsConstructor
public enum  UserError implements Error {

    LOGIN_FAIL(-20001, "登录失败"),
    REGISTER_PASSWORD_NOT_MATCH(-20002, "密码与重复密码不匹配"),
    REGISTER_USER_FAIL_EMAIL_ALREAY_EXISTS(-20003, "该email地址已经被注册"),
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
