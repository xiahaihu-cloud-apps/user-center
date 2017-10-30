package com.shearf.cloud.apps.user.center.common.error;

import java.io.Serializable;

/**
 * @author xiahaihu2009@gmail.com
 */
public interface Error extends Serializable {

    /**
     * 获得错误代码
     * @return
     */
    int getCode();

    /**
     * 获得错误信息
     * @return
     */
    String getMessage();
}
