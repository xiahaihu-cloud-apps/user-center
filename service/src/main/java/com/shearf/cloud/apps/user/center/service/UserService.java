package com.shearf.cloud.apps.user.center.service;

import com.shearf.cloud.apps.commons.foundation.mybatis.IGenericService;
import com.shearf.cloud.apps.user.center.domain.model.UserModel;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * @author xiahaihu2009@gmail.com
 */
public interface UserService extends IGenericService<UserModel, Integer>, UserDetailsManager {
}
