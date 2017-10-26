package com.shearf.cloud.apps.user.center.web.advice;

import com.alibaba.fastjson.JSON;
import com.shearf.cloud.apps.user.center.common.constants.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xiahaihu2009@gmai.com
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(BindException.class)
    public ModelAndView handleBindException(BindException e, HttpServletRequest request, HttpServletResponse response) {
        if (isAjax(request)) {
            try {
                PrintWriter writer = response.getWriter();
                String str = "bindException";
                String json = JSON.toJSONString(str);
                writer.write(json);
                writer.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            return null;
        }
        return null;
    }

    private boolean isAjax(HttpServletRequest request) {
        String xRequestWith = request.getHeader(Constant.X_REQUEST_HEADER);
        return StringUtils.isNotBlank(xRequestWith) && Constant.X_REQUEST_HEADER_VALUE.equals(xRequestWith);
    }
}
