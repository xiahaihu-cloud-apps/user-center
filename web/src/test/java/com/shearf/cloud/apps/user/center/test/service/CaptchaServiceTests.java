package com.shearf.cloud.apps.user.center.test.service;

import com.shearf.cloud.apps.user.center.domain.entity.CaptchaAndImage;
import com.shearf.cloud.apps.user.center.service.CaptchaService;
import com.shearf.cloud.apps.user.center.web.config.AppContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2017/12/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppContextConfig.class})
@WebAppConfiguration
public class CaptchaServiceTests {

    @Resource
    private CaptchaService simpleCaptchaService;

    @Test
    public void simpleCaptchaServiceTest() {
        CaptchaAndImage captchaAndImage = simpleCaptchaService.getCaptchaAndImage();

    }
}
