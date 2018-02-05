package com.shearf.cloud.apps.user.center.service.impl;

import com.shearf.cloud.apps.commons.foundation.mybatis.AbstractGenericService;
import com.shearf.cloud.apps.commons.foundation.util.StringUtil;
import com.shearf.cloud.apps.user.center.dal.mapper.UserSessionMapper;
import com.shearf.cloud.apps.user.center.domain.bean.ConfigValue;
import com.shearf.cloud.apps.user.center.domain.bean.UserDetailsBean;
import com.shearf.cloud.apps.user.center.domain.model.UserSessionModel;
import com.shearf.cloud.apps.user.center.service.UserSessionService;
import org.joda.time.DateTime;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2018/2/2
 */
@Service
public class UserSessionServiceImpl extends AbstractGenericService<UserSessionModel, Integer, UserSessionMapper> implements UserSessionService {

    @Resource
    private UserSessionMapper userSessionMapper;

    @Resource
    private ConfigValue configValue;

    @Resource
    private RedisTemplate<String, UserDetailsBean> redisTemplate;

    @Override
    protected UserSessionMapper getMapper() {
        return userSessionMapper;
    }

    @Override
    public UserSessionModel getByUserId(Integer id) {
        return userSessionMapper.getByUserId(id);
    }

    @Override
    public String saveUserSession(UserDetailsBean userDetailsBean) {
        String ticket = StringUtil.createRandomUniqueKey();
        ValueOperations<String, UserDetailsBean> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(ticket, userDetailsBean, configValue.getSessionExpireTime(), TimeUnit.SECONDS);

        DateTime expireDateTime = new DateTime();
        expireDateTime.plusSeconds(configValue.getSessionExpireTime());
        UserSessionModel currentSessionModel = getByUserId(userDetailsBean.getUserModel().getId());
        UserSessionModel userSessionModel = new UserSessionModel();
        userSessionModel.setSessionId(ticket);
        userSessionModel.setUserId(userDetailsBean.getUserModel().getId());
        userSessionModel.setLoginTime(new Date());
        userSessionModel.setExpiredTime(expireDateTime.toDate());
        if (currentSessionModel == null) {
            insertSelective(userSessionModel);
        } else {
            String existsTicket = currentSessionModel.getSessionId();
            redisTemplate.delete(existsTicket);
            userSessionModel.setId(currentSessionModel.getId());
            updateSelective(userSessionModel);
        }
        return ticket;
    }

    @Override
    public String getSessionId(Integer userId) {
        UserSessionModel sessionModel = getByUserId(userId);
        if (sessionModel != null) {
            return sessionModel.getSessionId();
        }
        return null;
    }
}
