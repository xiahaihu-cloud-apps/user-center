package com.shearf.cloud.apps.user.center.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiahaihu2009@gmail.com
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.shearf.cloud.apps.user.center.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

//        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
//        messageConverter.setDefaultCharset(utf8Charset());
//        messageConverter.setSupportedMediaTypes(Lists.newArrayList(
//                MediaType.TEXT_PLAIN,
//                MediaType.TEXT_HTML,
//                MediaType.APPLICATION_JSON_UTF8
//        ));

        List<MediaType> mediaTypes = Lists.newArrayList(
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_HTML,
                MediaType.APPLICATION_JSON_UTF8
        );
//
        FastJsonHttpMessageConverter httpMessageConverter = new FastJsonHttpMessageConverter();
        httpMessageConverter.setDefaultCharset(utf8Charset());
        httpMessageConverter.setFastJsonConfig(fastJsonConfig());
        httpMessageConverter.setSupportedMediaTypes(mediaTypes);
        converters.add(httpMessageConverter);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        Map<String, MediaType> mediaTypeMap = new HashMap<>(8);
        mediaTypeMap.put("json", MediaType.APPLICATION_JSON_UTF8);
        mediaTypeMap.put("html", MediaType.TEXT_HTML);
        configurer.mediaTypes(mediaTypeMap);
    }

    @Bean
    public Charset utf8Charset() {
        return Charset.forName("UTF-8");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(internalResourceViewResolver());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setContentType("text/html; charset=utf-8");
        viewResolver.setRequestContextAttribute("request");
        viewResolver.setPrefix("/static/pages/");
        viewResolver.setCache(false);
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("*");
        super.addCorsMappings(registry);
    }

    @Bean
    public FastJsonConfig fastJsonConfig() {
        FastJsonConfig jsonConfig = new FastJsonConfig();
        jsonConfig.setCharset(utf8Charset());
        jsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        return jsonConfig;
    }
}
