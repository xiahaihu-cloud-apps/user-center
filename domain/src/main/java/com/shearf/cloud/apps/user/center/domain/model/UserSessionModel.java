package com.shearf.cloud.apps.user.center.domain.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiahaihu2009@gmail.com
 * @date 2018/2/2
 */
@Data
public class UserSessionModel implements Serializable {

    private static final long serialVersionUID = 6343888167204457683L;

    private Integer id;

    private Integer userId;

    private String sessionId;

    private Date loginTime;

    private Date expiredTime;
}
