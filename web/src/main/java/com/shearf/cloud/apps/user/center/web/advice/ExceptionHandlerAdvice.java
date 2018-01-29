package com.shearf.cloud.apps.user.center.web.advice;

import com.alibaba.fastjson.JSON;
import com.shearf.cloud.apps.commons.foundation.response.Response;
import com.shearf.cloud.apps.user.center.common.constants.Constant;
import com.shearf.cloud.apps.user.center.common.error.GlobalError;
import com.shearf.cloud.apps.user.center.common.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * @author xiahaihu2009@gmai.com
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(BindException.class)
    public ModelAndView handleBindException(BindException e, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.error("数据验证错误, {}", e.getFieldErrors());
        if (isAjax(request)) {
            Response res = Response.result(GlobalError.VALIDATE_ERROR.getCode(), GlobalError.VALIDATE_ERROR.getMessage(), null);
            responseJson(res, response);
        } else {
            return new ModelAndView("error").addObject("error", "验证失败");
        }
        return null;
    }

    @ExceptionHandler(ServiceException.class)
    public ModelAndView handleServiceException(ServiceException e, HttpServletRequest request, HttpServletResponse response) {
        if (isAjax(request)) {
            Response res = Response.result(e.getCode(), e.getMessage(), e.getObject());
            responseJson(res, response);
        } else {
            return new ModelAndView("error").addObject("error", e.getMessages());
        }
        return null;
    }

    private boolean isAjax(HttpServletRequest request) {
        String xRequestWith = request.getHeader(Constant.X_REQUEST_HEADER);
        return StringUtils.isNotBlank(xRequestWith) && Constant.X_REQUEST_HEADER_VALUE.equals(xRequestWith);
    }

    @ResponseBody
    private void responseJson(Response response, HttpServletResponse servletResponse) {
        try {
            servletResponse.setContentType(String.valueOf(ContentType.DEFAULT_TEXT.withCharset(Charset.forName("UTF-8"))));
            PrintWriter writer = servletResponse.getWriter();
            writer.write(JSON.toJSONString(response));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("无法返回json请求");
        }

    }
}
