package com.shearf.cloud.apps.user.center.web.config;

import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.word.AdaptiveRandomWordFactory;
import com.google.common.collect.Lists;
import com.shearf.cloud.apps.user.center.domain.bean.ConfigValue;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.awt.*;

/**
 * @author xiahaihu2009@gmail.com
 */
@Configuration
@ComponentScan(value = "com.shearf.cloud.apps.user.center")
public class AppContextConfig implements EnvironmentAware {

    private Environment environment;

    @Bean
    public ConfigurableCaptchaService captchaService() {
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));

        AdaptiveRandomWordFactory wordFactory = new AdaptiveRandomWordFactory();
        wordFactory.setMinLength(6);
        wordFactory.setMaxLength(6);

        cs.setWordFactory(wordFactory);
        return cs;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Lists.newArrayList(
                new FormHttpMessageConverter(),
                new StringHttpMessageConverter(),
                new MappingJackson2HttpMessageConverter()
        ));
        return restTemplate;
    }

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
