package com.shearf.cloud.apps.user.center.web.controller;

import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.shearf.cloud.apps.user.center.common.constants.SessionKey;
import com.shearf.cloud.apps.user.center.domain.entity.CaptchaAndImage;
import com.shearf.cloud.apps.user.center.service.CaptchaService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @author xiahaihu2009@gmail.com
 */
@Controller
public class KitController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KitController.class);

    @Resource
    private ConfigurableCaptchaService captchaService;

    @Resource
    private CaptchaService simpleCaptchaService;

    @RequestMapping("captcha")
    @ResponseBody
    public String captcha(HttpServletRequest request) {
        final String captchaBasePath = "/public/captcha";

        HttpSession session = request.getSession();

        final String webappRealPath = request.getServletContext().getRealPath("");
        String captchaPath = createRandomCaptchaImagePath(captchaBasePath, webappRealPath + captchaBasePath, ".png");
        String captchaRealPath = webappRealPath + captchaPath;

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(captchaRealPath);
            session.setAttribute(SessionKey.CAPTCHA, EncoderHelper.getChallangeAndWriteImage(captchaService, "png", fos));
        } catch (IOException e) {
            LOGGER.error("创建验证码图片失败, imageFile:{}", captchaRealPath);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return captchaPath;
    }

    /**
     * 下发简单的验证码
     *
     * @param request http请求
     * @return 验证码图片地址
     */
    @GetMapping("captcha/simple")
    @ResponseBody
    public String simpleCaptcha(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CaptchaAndImage captchaAndImage = simpleCaptchaService.getCaptchaAndImage();
        if (captchaAndImage != null) {
            session.setAttribute(SessionKey.CAPTCHA, captchaAndImage.getCaptcha());
            return captchaAndImage.getImgUrl();
        }
        return null;
    }

    @GetMapping("csrf")
    @ResponseBody
    public CsrfToken csrfToken(CsrfToken csrfToken, HttpServletRequest request) {
        return csrfToken;
    }

    /**
     * 创建随机的验证码图片地址
     * @param basePath
     * @param ext
     * @return
     */
    private String createRandomCaptchaImagePath(final String basePath, final String realBasePath, final String ext) {
        Date now = new Date();
        String today = DateFormatUtils.format(now, "yyyyMMdd");
        String captchaDirPath = realBasePath + "/" + today;
        File captchaDir = new File(captchaDirPath);
        if (!captchaDir.exists()) {
            if (!captchaDir.mkdirs()) {
                LOGGER.error("创建验证码文件夹目录出错, 目录:{}", captchaDirPath);
            }
        }
        String randomStr = StringUtils.leftPad("0", 6, String.valueOf(RandomUtils.nextInt(0, 999999)));
        String fileName = DateFormatUtils.format(now, "HHmmss").concat(randomStr);
        return basePath + "/" + today + "/" + fileName + ext;
    }
}
