package com.shearf.cloud.apps.user.center.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiahaihu2009@gmail.com
 */
@Controller
public class UserController {

    @GetMapping("")
    @ResponseBody
    public String index() {
        return "index";
    }
}
