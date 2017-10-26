package com.shearf.cloud.apps.user.center.web.exception;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiahaihu2009@gmail.com
 */
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {

        if (e instanceof BindException) {
            System.out.println("bindException");
        }
        return null;
    }
}
