package com.shearf.cloud.apps.user.center.dal.mapper;

import com.shearf.cloud.apps.commons.foundation.mybatis.IGenericMapper;
import com.shearf.cloud.apps.user.center.domain.model.UserSessionModel;
import org.springframework.stereotype.Repository;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2018/2/2
 */
@Repository
public interface UserSessionMapper extends IGenericMapper<UserSessionModel, Integer> {
    UserSessionModel getByUserId(Integer id);
}
