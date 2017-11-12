package com.shearf.cloud.apps.user.center.domain.entity;

import lombok.Data;

/**
 *
 * @author xiahaihu2009@gmail
 * @date 2017/11/9
 */
@Data
public class CaptchaAndImage {

    /**
     * 验证码图片地址
     */
    private String imgUrl;

    /**
     * 验证码
     */
    private String captcha;
}
