package com.shearf.cloud.apps.user.center.web.advice;

import com.alibaba.fastjson.JSON;
import com.shearf.cloud.apps.commons.foundation.response.Response;
import com.shearf.cloud.apps.user.center.common.constants.Constant;
import com.shearf.cloud.apps.user.center.common.error.GlobalError;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfException;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2018/1/29
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        if (e instanceof CsrfException) {
            if (isAjax(request)) {
                Response res = Response.result(GlobalError.INVALID_CSRF.getCode(), GlobalError.INVALID_CSRF.getMessage(), null);
                responseJson(res, response);
            }
        }
    }

    private boolean isAjax(HttpServletRequest request) {
        String xRequestWith = request.getHeader(Constant.X_REQUEST_HEADER);
        return StringUtils.isNotBlank(xRequestWith) && Constant.X_REQUEST_HEADER_VALUE.equals(xRequestWith);
    }

    private void responseJson(Response response, HttpServletResponse servletResponse) {
        try {
            servletResponse.setContentType(String.valueOf(ContentType.DEFAULT_TEXT.withCharset(Charset.forName("UTF-8"))));
            PrintWriter writer = servletResponse.getWriter();
            writer.write(JSON.toJSONString(response));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
