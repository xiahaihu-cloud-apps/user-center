package com.shearf.cloud.apps.user.center.web.advice;

import com.shearf.cloud.apps.commons.foundation.util.StringUtil;
import com.shearf.cloud.apps.user.center.domain.bean.ConfigValue;
import com.shearf.cloud.apps.user.center.domain.bean.UserDetailsBean;
import com.shearf.cloud.apps.user.center.domain.model.UserSessionModel;
import com.shearf.cloud.apps.user.center.service.UserSessionService;
import org.joda.time.DateTime;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2018/2/2
 */
public class UserAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private UserSessionService userSessionService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ConfigValue configValue;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsBean userDetailsBean = (UserDetailsBean) currentUser.getPrincipal();

        String ticket = StringUtil.createRandomUniqueKey();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(ticket, userDetailsBean, configValue.getSessionExpireTime(), TimeUnit.SECONDS);

        DateTime expireDateTime = new DateTime();
        expireDateTime.plusSeconds(configValue.getSessionExpireTime());
        UserSessionModel currentSessionModel = userSessionService.getByUserId(userDetailsBean.getUserModel().getId());
        UserSessionModel userSessionModel = new UserSessionModel();
        userSessionModel.setSessionId(ticket);
        userSessionModel.setLoginTime(new Date());
        userSessionModel.setExpiredTime(expireDateTime.toDate());
        if (currentSessionModel == null) {
            userSessionService.insertSelective(userSessionModel);
        } else {
            String existsTicket = currentSessionModel.getSessionId();
            redisTemplate.delete(existsTicket);
            userSessionModel.setId(currentSessionModel.getId());
            userSessionService.updateSelective(userSessionModel);
        }
    }
}
