package com.shearf.cloud.apps.user.center.web.bean;

import com.shearf.cloud.apps.user.center.domain.bean.ConfigValue;
import com.shearf.cloud.apps.user.center.web.config.AppContextConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2017/11/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppContextConfig.class})
public class ConfigValueTest {

    @Resource
    private ConfigValue configValue;

    @Test
    public void simpleCaptchaApiTest() {
        Assert.assertTrue(configValue.getSimpleCaptchaApi().equals("http://captcha.shearf.com/captcha/simple"));
    }
}