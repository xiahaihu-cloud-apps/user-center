package com.shearf.cloud.apps.user.center.web.interceptor;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2018/1/26
 */
public class CsrfCleanInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsrfCleanInterceptor.class);

    private static HashSet allowedNoCsrfHttpMethods = Sets.newHashSet("GET", "HEAD", "TRACE", "OPTIONS");
    private static String CSRF_TOKEN = HttpSessionCsrfTokenRepository.class.getName().concat(".CSRF_TOKEN");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!allowedNoCsrfHttpMethods.contains(request.getMethod())) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(CSRF_TOKEN);
                LOGGER.debug("clean csrf token");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
