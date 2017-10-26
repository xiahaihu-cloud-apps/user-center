package com.shearf.cloud.apps.user.center.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiahaihu2009@gmai.com
 */
@Controller
public class ErrorController {

    @RequestMapping("error")
    @ResponseBody
    public String error() {
        return "error";
    }
}
