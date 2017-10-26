package com.shearf.cloud.apps.user.center.web.config;

import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.AdaptiveRandomWordFactory;
import com.github.bingoohuang.patchca.word.WordFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xiahaihu2009@gmail.com
 */
@Configuration
@ComponentScan("com.shearf.cloud.apps.user.center")
public class AppContextConfig {

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
}
