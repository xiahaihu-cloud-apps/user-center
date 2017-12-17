package com.shearf.cloud.apps.user.center.domain.model;

import com.shearf.cloud.apps.commons.foundation.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author xiahaihu2009@gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserModel extends BaseModel {

    /**
     * ID
     */
    private Integer id;

    /**
     * 英文名
     */
    private String name;

    /**
     * 密码盐(32)
     */
    private String salt;

    /**
     * 密码(64)
     */
    private String password;

    /**
     * email地址
     */
    private String email;

    /**
     * 状态(1启用，0禁用)
     */
    private Integer status;

    @AllArgsConstructor
    public enum Status {
        /**
         * 启用的
         */
        ENABLED(1),
        /**
         * 禁用的
         */
        DISABLED(0),
        ;
        @Getter
        private int value;
    }

}
