package com.shearf.cloud.apps.user.center.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import com.shearf.cloud.apps.user.center.web.interceptor.CsrfCleanInterceptor;
import com.shearf.cloud.apps.user.center.web.interceptor.CaptchaInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

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
        List<MediaType> mediaTypes = Lists.newArrayList(
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_HTML,
                MediaType.APPLICATION_JSON_UTF8
        );
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
        registry.viewResolver(freeMarkerViewResolver());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CaptchaInterceptor()).addPathPatterns("/register");
        registry.addInterceptor(new CsrfCleanInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public ViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver freeMarkerView = new FreeMarkerViewResolver();
        freeMarkerView.setContentType("text/html; charset=utf-8");
        freeMarkerView.setSuffix(".html");
        freeMarkerView.setRequestContextAttribute("rc");
        freeMarkerView.setCache(false);
        freeMarkerView.setViewClass(FreeMarkerView.class);
        return freeMarkerView;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setDefaultEncoding("UTF-8");
        configurer.setTemplateLoaderPath("/static/pages/");
        return configurer;
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
