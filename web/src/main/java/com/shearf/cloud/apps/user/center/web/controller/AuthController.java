package com.shearf.cloud.apps.user.center.web.controller;

import com.shearf.cloud.apps.user.center.domain.bean.UserDetailsBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author xiahaihu2009@gmail.com
 */
@Controller
@RequestMapping("login")
public class AuthController {

    @GetMapping("")
    public String login(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null && StringUtils.isNotBlank(principal.getName())) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("")
    @ResponseBody
    public String doLogin() {
        return "doLogin";
    }

    @GetMapping("success")
    @ResponseBody
    public String success() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsBean userDetailsBean = (UserDetailsBean) currentUser.getPrincipal();
        return userDetailsBean.toString();
    }

    @GetMapping("fail")
    @ResponseBody
    public String fail() {
        return "fail";
    }
}
