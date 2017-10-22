package com.shearf.cloud.apps.user.center.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiahaihu2009@gmail.com
 */
@Controller
public class UserController {

    @GetMapping("login")
    public String login() {
        return "login";
    }
}
