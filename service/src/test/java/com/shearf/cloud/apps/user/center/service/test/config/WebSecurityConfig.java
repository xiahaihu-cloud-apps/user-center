package com.shearf.cloud.apps.user.center.service.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * @author xiahaihu2009@gmail.com
 */
@Configuration
public class WebSecurityConfig {

    @Bean
    public ShaPasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder(256);
    }

//    @Bean
//    public SaltSource saltSource() {
//        ReflectionSaltSource reflectionSaltSource = new ReflectionSaltSource();
//        reflectionSaltSource.setUserPropertyToUse("salt");
//        return new ReflectionSaltSource();
//    }
}