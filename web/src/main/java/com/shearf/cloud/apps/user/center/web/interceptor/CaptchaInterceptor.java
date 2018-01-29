package com.shearf.cloud.apps.user.center.web.interceptor;

import com.shearf.cloud.apps.user.center.common.constants.SessionKey;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2018/1/26
 */
public class CaptchaInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(SessionKey.CAPTCHA);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(SessionKey.CAPTCHA);
        }
    }
}
