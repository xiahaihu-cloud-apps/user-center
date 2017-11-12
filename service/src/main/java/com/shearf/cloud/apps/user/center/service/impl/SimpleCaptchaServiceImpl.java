package com.shearf.cloud.apps.user.center.service.impl;

import com.shearf.cloud.apps.user.center.domain.bean.ConfigValue;
import com.shearf.cloud.apps.user.center.domain.entity.CaptchaAndImage;
import com.shearf.cloud.apps.user.center.service.CaptchaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2017/11/9
 */
@Service("simpleCaptchaService")
public class SimpleCaptchaServiceImpl implements CaptchaService {

    @Resource
    private ConfigValue configValue;

    @Override
    public CaptchaAndImage getCaptchaAndImage() {
        return null;
    }
}
