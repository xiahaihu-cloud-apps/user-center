package com.shearf.cloud.apps.user.center.web.controller;

import com.shearf.cloud.apps.user.center.domain.bean.ConfigValue;
import com.shearf.cloud.apps.user.center.domain.bean.UserDetailsBean;
import com.shearf.cloud.apps.user.center.service.UserSessionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author xiahaihu2009@gmail.com
 */
@Controller
@RequestMapping("login")
public class AuthController {

    @Resource
    private UserSessionService userSessionService;

    @GetMapping("")
    public String login(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null && StringUtils.isNotBlank(principal.getName())) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("success")
    @ResponseBody
    public String success() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsBean userDetailsBean = (UserDetailsBean) currentUser.getPrincipal();

        return userSessionService.saveUserSession(userDetailsBean);
    }

    @GetMapping("fail")
    @ResponseBody
    public String fail() {
        return "fail";
    }
}
