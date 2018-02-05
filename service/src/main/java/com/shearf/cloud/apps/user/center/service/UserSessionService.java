package com.shearf.cloud.apps.user.center.service;

import com.shearf.cloud.apps.commons.foundation.mybatis.IGenericService;
import com.shearf.cloud.apps.user.center.domain.bean.UserDetailsBean;
import com.shearf.cloud.apps.user.center.domain.model.UserSessionModel;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2018/2/2
 */
public interface UserSessionService extends IGenericService<UserSessionModel, Integer> {
    /**
     * 根据用户ID获得用户session信息
     *
     * @param id
     * @return
     */
    UserSessionModel getByUserId(Integer id);

    /**
     * 保存用户session并返回sessionID
     * @param userDetailsBean
     * @return
     */
    String saveUserSession(UserDetailsBean userDetailsBean);

    /**
     * 获得用户的sessionID
     *
     * @param userId
     * @return
     */
    String getSessionId(Integer userId);
}
