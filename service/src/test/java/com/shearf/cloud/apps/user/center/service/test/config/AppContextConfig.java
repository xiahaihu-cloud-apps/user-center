package com.shearf.cloud.apps.user.center.service.test.config;

import com.shearf.cloud.apps.user.center.domain.bean.ConfigValue;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author xiahaihu2009@gmail.com
 */
@Configuration
@ComponentScan("com.shearf.cloud.apps.user.center")
@PropertySource("classpath:application.properties")
public class AppContextConfig implements EnvironmentAware {

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
