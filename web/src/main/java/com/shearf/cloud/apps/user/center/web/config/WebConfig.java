package com.shearf.cloud.apps.user.center.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("com.shearf.cloud.apps.user.center.web")
public class WebConfig extends WebMvcConfigurerAdapter {

}
