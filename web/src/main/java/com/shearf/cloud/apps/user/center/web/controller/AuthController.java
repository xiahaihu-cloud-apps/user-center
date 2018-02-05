package com.shearf.cloud.apps.user.center.web.controller;

import com.shearf.cloud.apps.user.center.domain.bean.UserDetailsBean;
import com.shearf.cloud.apps.user.center.service.UserSessionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.security.Principal;

/**
 * @author xiahaihu2009@gmail.com
 */
@Controller
@RequestMapping("login")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Resource
    private UserSessionService userSessionService;

    @GetMapping("")
    public String login(HttpServletRequest request, ModelMap modelMap) {
        String redirect = request.getParameter("redirect");
        modelMap.put("redirect", StringUtils.isNotBlank(redirect) ? redirect : "");
        Principal principal = request.getUserPrincipal();
        if (principal != null && StringUtils.isNotBlank(principal.getName())) {
            if (StringUtils.isNotBlank(redirect)) {
                Authentication authentication = (Authentication) request.getUserPrincipal();
                UserDetailsBean userDetailsBean = (UserDetailsBean) authentication.getPrincipal();
                String ticket = userSessionService.getSessionId(userDetailsBean.getUserModel().getId());
                if (ticket == null) {
                    ticket = userSessionService.saveUserSession(userDetailsBean);
                }
                try {
                    URIBuilder uriBuilder = new URIBuilder(redirect);
                    uriBuilder.setParameter("ticket", ticket);
                    return "redirect://" + uriBuilder.build().toString();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    LOGGER.error("原跳转地址无效, redirectURL:{}", redirect);
                }
                return "login";
            }
        }
        return "login";
    }

    @GetMapping("success")
    @ResponseBody
    public String success() {
        return "success";
    }

    @GetMapping("fail")
    @ResponseBody
    public String fail() {
        return "fail";
    }
}
