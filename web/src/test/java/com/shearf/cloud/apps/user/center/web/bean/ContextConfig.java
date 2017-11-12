package com.shearf.cloud.apps.user.center.web.bean;

import com.shearf.cloud.apps.user.center.domain.bean.ConfigValue;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2017/11/11
 */
@Configuration
@ComponentScan("com.shearf.cloud.apps.user.center")
public class ContextConfig implements EnvironmentAware {

    private Environment environment;

    @Bean
    public ConfigValue configValue() {
        ConfigValue configValue = new ConfigValue();
        configValue.setSimpleCaptchaApi(environment.getProperty("app.simple.captcha.api"));
        return configValue;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
