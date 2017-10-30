package com.shearf.cloud.apps.user.center.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xiahaihu2009@gmai.com
 */
@Controller
public class ErrorController {

    @RequestMapping("error")
    public String error(ModelMap map) {
        map.put("error", "error");
        return "error";
    }
}
