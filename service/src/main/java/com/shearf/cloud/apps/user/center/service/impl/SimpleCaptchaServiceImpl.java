package com.shearf.cloud.apps.user.center.service.impl;

import com.google.common.collect.Lists;
import com.shearf.cloud.apps.commons.foundation.response.Response;
import com.shearf.cloud.apps.commons.foundation.util.DateUtil;
import com.shearf.cloud.apps.user.center.domain.bean.ConfigValue;
import com.shearf.cloud.apps.user.center.domain.entity.CaptchaAndImage;
import com.shearf.cloud.apps.user.center.service.CaptchaService;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2017/11/9
 */
@Service("simpleCaptchaService")
public class SimpleCaptchaServiceImpl implements CaptchaService {

    @Resource
    private ConfigValue configValue;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public CaptchaAndImage getCaptchaAndImage() {
        String simpleCaptchaApi = configValue.getSimpleCaptchaApi();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON_UTF8));
        headers.setAcceptCharset(Lists.newArrayList(Charset.forName("utf8")));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        String time = DateUtil.formatDateTime(new Date());
        String appKey = configValue.getSimpleCaptchaAppKey();
        String appSecret = configValue.getSimpleCaptchaAppSecret();
        String sign = createSimpleCaptchaSign(appKey, time, appSecret);
        String url = simpleCaptchaApi + "?app_key=" + appKey +
                "&time=" + time +
                "&sign=" + sign;

        ParameterizedTypeReference<Response<CaptchaAndImage>> typeReference = new ParameterizedTypeReference<Response<CaptchaAndImage>>() {
        };
        ResponseEntity<Response<CaptchaAndImage>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, typeReference);
        Response<CaptchaAndImage> captchaAndImageResponse = responseEntity.getBody();
        return captchaAndImageResponse.getData();
    }

    private String createSimpleCaptchaSign(String appKey, String time, String appSecret) {
        Date timeNow = DateTime.parse(time, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        return DigestUtils.sha1Hex(appKey + timeNow.getTime() + appSecret);
    }
}
