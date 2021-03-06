package com.shearf.cloud.apps.user.center.web.controller;

import com.shearf.cloud.apps.commons.foundation.response.Response;
import com.shearf.cloud.apps.user.center.common.constants.SessionKey;
import com.shearf.cloud.apps.user.center.common.error.UserError;
import com.shearf.cloud.apps.user.center.common.exception.ServiceException;
import com.shearf.cloud.apps.user.center.domain.bean.UserDetailsBean;
import com.shearf.cloud.apps.user.center.domain.model.UserModel;
import com.shearf.cloud.apps.user.center.domain.param.RegisterParam;
import com.shearf.cloud.apps.user.center.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author xiahaihu2009@gmail.com
 */
@Controller
public class RegisterController {

    @Resource
    private UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Response<String> doRegister(@RequestBody @Valid RegisterParam param, HttpServletRequest request) {

        String redirect = request.getParameter("redirect");
        redirect = StringUtils.isNoneBlank(redirect) ? redirect : "/login";
        HttpSession session = request.getSession();
        String captcha = (String) session.getAttribute(SessionKey.CAPTCHA);
        if (captcha == null || !captcha.equals(param.getCaptcha())) {
            throw new ServiceException(UserError.REGISTER_USER_FAIL_CAPTCHA_NOT_MATCH);
        }

        if (!param.getPassword().equals(param.getRetypePassword())) {
            throw new ServiceException(UserError.REGISTER_PASSWORD_NOT_MATCH);
        }

        UserModel userModel = new UserModel();
        userModel.setEmail(param.getEmail());
        userModel.setName(param.getName());
        userModel.setPassword(param.getPassword());
        userService.createUser(UserDetailsBean.generateByUserModel(userModel));

        return Response.success(redirect);
    }
}
