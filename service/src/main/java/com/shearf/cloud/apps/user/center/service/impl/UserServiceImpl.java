package com.shearf.cloud.apps.user.center.service.impl;

import com.shearf.cloud.apps.commons.foundation.mybatis.AbstractGenericService;
import com.shearf.cloud.apps.user.center.dal.mapper.UserMapper;
import com.shearf.cloud.apps.user.center.domain.model.UserModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends AbstractGenericService<UserModel, Integer, UserMapper> {

    @Resource
    private UserMapper userMapper;

    @Override
    protected UserMapper getMapper() {
        return userMapper;
    }
}
