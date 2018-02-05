package com.shearf.cloud.apps.user.center.web.advice;

import com.shearf.cloud.apps.commons.foundation.response.Response;
import com.shearf.cloud.apps.user.center.common.error.GlobalError;
import com.shearf.cloud.apps.user.center.web.helper.HttpHelper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2018/1/29
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        if (e instanceof CsrfException) {
            if (HttpHelper.isAjaxRequest(request)) {
                Response res = Response.result(GlobalError.INVALID_CSRF.getCode(), GlobalError.INVALID_CSRF.getMessage(), null);
                HttpHelper.responseJson(res, response);
            }
        }
    }
}
