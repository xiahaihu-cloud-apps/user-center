package com.shearf.cloud.apps.user.center.dal.mapper;

import com.shearf.cloud.apps.commons.foundation.mybatis.IGenericMapper;
import com.shearf.cloud.apps.user.center.domain.model.UserModel;
import org.springframework.stereotype.Repository;

/**
 * @author xiahaihu2009@gmail.com
 */
@Repository
public interface UserMapper extends IGenericMapper<UserModel, Integer> {
}
