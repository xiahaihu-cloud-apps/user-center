package com.shearf.cloud.apps.user.center.service.impl;

import com.shearf.cloud.apps.commons.foundation.mybatis.AbstractGenericService;
import com.shearf.cloud.apps.user.center.common.error.UserError;
import com.shearf.cloud.apps.user.center.common.exception.ServiceException;
import com.shearf.cloud.apps.user.center.dal.mapper.UserMapper;
import com.shearf.cloud.apps.user.center.domain.bean.UserDetailsBean;
import com.shearf.cloud.apps.user.center.domain.model.UserModel;
import com.shearf.cloud.apps.user.center.service.UserService;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author xiahaihu2009@gmail.com
 */
@Service
public class UserServiceImpl extends AbstractGenericService<UserModel, Integer, UserMapper> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ShaPasswordEncoder passwordEncoder;

    @Override
    protected UserMapper getMapper() {
        return userMapper;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        if (userExists(userDetails.getUsername())) {
            throw new ServiceException(UserError.REGISTER_USER_FAIL_EMAIL_ALREADY_EXISTS);
        }

        final String salt = UUID.randomUUID().toString().trim().replaceAll("-", "");
        UserModel userModel = ((UserDetailsBean) userDetails).getUserModel();
        userModel.setPassword(passwordEncoder.encodePassword(userDetails.getPassword(), salt));
        userModel.setSalt(salt);
        userModel.setStatus(UserModel.Status.ENABLED.getValue());
        userModel.setCreateTime(new Date());
        userModel.setUpdateTime(new Date());
        insert(userModel);
    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String s) {
        UserModel userModel = userMapper.getByEmail(s);
        if (userModel != null) {
            delete(userModel.getId());
        }
    }

    @Override
    public void changePassword(String s, String s1) {
//        Authentication currUser = SecurityContextHolder.getContext().getAuthentication();
//        if (currUser != null) {
//
//        }
    }

    @Override
    public boolean userExists(String s) {
        UserModel userModel = userMapper.getByEmail(s);
        return userModel != null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserModel userModel = userMapper.getByEmail(s);
        if (userModel == null) {
            throw new UsernameNotFoundException("can not find user ["+ s +"]");
        }
        return UserDetailsBean.generateByUserModel(userModel);
    }
}
