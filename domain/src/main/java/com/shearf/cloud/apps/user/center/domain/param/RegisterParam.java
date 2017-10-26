package com.shearf.cloud.apps.user.center.domain.param;

import com.shearf.cloud.apps.commons.foundation.base.IParam;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author xiahaihu2009@gmail.com
 */
@Data
public class RegisterParam implements IParam {

    private static final long serialVersionUID = -5833901465772148647L;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String retypePassword;
}
