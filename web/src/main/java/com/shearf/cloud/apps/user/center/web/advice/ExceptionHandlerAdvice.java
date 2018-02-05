package com.shearf.cloud.apps.user.center.web.advice;

import com.shearf.cloud.apps.commons.foundation.response.Response;
import com.shearf.cloud.apps.user.center.common.error.GlobalError;
import com.shearf.cloud.apps.user.center.common.exception.ServiceException;
import com.shearf.cloud.apps.user.center.web.helper.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiahaihu2009@gmai.com
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(BindException.class)
    public ModelAndView handleBindException(BindException e, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.warn("数据验证错误, {}", e.getFieldErrors());
        if (HttpHelper.isAjaxRequest(request)) {
            Response res = Response.result(GlobalError.VALIDATE_ERROR.getCode(), GlobalError.VALIDATE_ERROR.getMessage(), null);
            HttpHelper.responseJson(res, response);
        } else {
            return new ModelAndView("error").addObject("error", "验证失败");
        }
        return null;
    }

    @ExceptionHandler(ServiceException.class)
    public ModelAndView handleServiceException(ServiceException e, HttpServletRequest request, HttpServletResponse response) {
        if (HttpHelper.isAjaxRequest(request)) {
            Response res = Response.result(e.getCode(), e.getMessage(), e.getObject());
            HttpHelper.responseJson(res, response);
        } else {
            return new ModelAndView("error").addObject("error", e.getMessages());
        }
        return null;
    }
}
