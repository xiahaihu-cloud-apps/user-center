package com.shearf.cloud.apps.user.center.domain.bean;

import lombok.Data;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2017/11/11
 */
@Data
public class ConfigValue {

    /**
     * 简单验证码服务api
     */
    private String simpleCaptchaApi;

    /**
     * 简单验证码服务需要的AppKey
     */
    private String simpleCaptchaAppKey;

    /**
     * 简单验证码服务需要的AppSecret
     */
    private String simpleCaptchaAppSecret;

}
