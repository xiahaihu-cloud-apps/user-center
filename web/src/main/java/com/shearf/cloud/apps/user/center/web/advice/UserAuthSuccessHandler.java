package com.shearf.cloud.apps.user.center.web.advice;

import com.shearf.cloud.apps.user.center.domain.bean.UserDetailsBean;
import com.shearf.cloud.apps.user.center.service.UserSessionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2018/2/2
 */
public class UserAuthSuccessHandler implements AuthenticationSuccessHandler {

    private UserSessionService userSessionService;

    public UserAuthSuccessHandler(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetailsBean userDetailsBean = (UserDetailsBean) authentication.getPrincipal();
        String ticket = userSessionService.saveUserSession(userDetailsBean);
        String redirect = request.getParameter("redirect");
        boolean hasRedirect = false;
        if (StringUtils.isNotBlank(redirect)) {
            try {
                URIBuilder urlBuilder = new URIBuilder(redirect);
                urlBuilder.setParameter("ticket", ticket);
                hasRedirect = true;
                response.sendRedirect(urlBuilder.build().toString());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        if (!hasRedirect) {
            response.sendRedirect("/login/success");
        }
    }
}
