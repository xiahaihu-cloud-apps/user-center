package com.shearf.cloud.apps.user.center.web.config;

import com.shearf.cloud.apps.user.center.service.UserService;
import com.shearf.cloud.apps.user.center.web.advice.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

/**
 * @author xiahaihu2009@gmail.com
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;

    @Bean
    public AuthenticationProvider authenticationProvider(SaltSource saltSource) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setSaltSource(saltSource);
        return authenticationProvider;
    }

    @Bean
    public ShaPasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder(256);
    }

    @Bean
    public SaltSource saltSource() {
        ReflectionSaltSource reflectionSaltSource = new ReflectionSaltSource();
        reflectionSaltSource.setUserPropertyToUse("salt");
        return reflectionSaltSource;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/login/fail").permitAll()
                .antMatchers("/register*").permitAll()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/captcha/**").permitAll()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login.html")
                    .passwordParameter("password").usernameParameter("username")
                    .defaultSuccessUrl("/login/success")
                    .failureUrl("/login/fail")
                .and().csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                .and().logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login.html")
                .invalidateHttpSession(true)
                .and().exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and().cors().disable()
        ;
    }

}