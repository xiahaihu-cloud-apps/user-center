package com.shearf.cloud.apps.user.center.service.test;

import com.shearf.cloud.apps.user.center.domain.bean.UserDetailsBean;
import com.shearf.cloud.apps.user.center.service.UserService;
import com.shearf.cloud.apps.user.center.service.test.config.AppContextConfig;
import com.shearf.cloud.apps.user.center.service.test.config.DruidDatasourceConfig;
import com.shearf.cloud.apps.user.center.service.test.config.WebSecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppContextConfig.class, DruidDatasourceConfig.class, WebSecurityConfig.class})
public class UserServiceImplTest {

    @Resource
    private UserService userService;

    @Resource
    private ShaPasswordEncoder passwordEncoder;

    @Test
    @Rollback
    public void createUser() throws Exception {
        UserDetailsBean userDetailsBean = new UserDetailsBean();
        userDetailsBean.setPassword("shearf");
        userDetailsBean.setUsername("xiahaihu@126.com");
        userService.createUser(userDetailsBean);
    }

    @Test
    public void updateUser() throws Exception {
    }

    @Test
    public void deleteUser() throws Exception {
    }

    @Test
    public void changePassword() throws Exception {
    }

    @Test
    public void userExists() throws Exception {
    }

    @Test
    public void loadUserByUsername() throws Exception {
        userService.loadUserByUsername("xiahaihu@126.com");
    }

}