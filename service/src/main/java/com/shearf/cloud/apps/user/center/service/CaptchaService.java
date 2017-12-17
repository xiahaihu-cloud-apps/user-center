package com.shearf.cloud.apps.user.center.service;

import com.shearf.cloud.apps.user.center.domain.entity.CaptchaAndImage;

/**
 *
 * @author xiahaihu2009@gmail
 * @date 2017/11/9
 */
public interface CaptchaService {

    /**
     * 返回验证码和验证码图片
     * 
     * @return 返回验证码和验证码图片
     */
    CaptchaAndImage getCaptchaAndImage();
}
