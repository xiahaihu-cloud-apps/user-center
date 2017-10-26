package com.shearf.cloud.apps.user.center.web.controller;

import com.shearf.cloud.apps.user.center.domain.param.RegisterParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author xiahaihu2009@gmail.com
 */
@Controller
public class RegisterController {

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid RegisterParam param) {
        return "redirect:/login";
    }
}
