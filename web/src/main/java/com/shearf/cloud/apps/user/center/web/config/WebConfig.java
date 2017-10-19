package com.shearf.cloud.apps.user.center.web.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author xiahaihu2009@gmail.com
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.shearf.cloud.apps.user.center.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setDefaultCharset(utf8Charset());

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setDefaultCharset(utf8Charset());
        messageConverter.setSupportedMediaTypes(Lists.newArrayList(
                new MediaType("text/plain"),
                new MediaType("text/html"),
                new MediaType("text/json"),
                new MediaType("application/json")
        ));

        converters.add(stringHttpMessageConverter);
        converters.add(messageConverter);
    }

    @Bean
    public Charset utf8Charset() {
        return Charset.forName("UTF-8");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(internalResourceViewResolver());
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setContentType("text/html; charset=utf-8");
        viewResolver.setRequestContextAttribute("request");
        viewResolver.setPrefix("/static/pages");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
}
