package com.shearf.cloud.apps.user.center.domain.bean;

import com.shearf.cloud.apps.user.center.domain.model.UserModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author xiahaihu2009@gmail.com
 */
@Data
public class UserDetailsBean implements UserDetails {

    private final UserModel userModel;

    private static final long serialVersionUID = -4526272797216934966L;

    public UserDetailsBean(UserModel userModel) {
        this.userModel = userModel;
    }

    public static UserDetailsBean generateByUserModel(UserModel userModel) {
        return new UserDetailsBean(userModel);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userModel.getPassword();
    }

    public String getSalt() {
        return userModel.getSalt();
    }

    public UserModel getUserModel() {
        return userModel;
    }

    @Override
    public String getUsername() {
        return userModel.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userModel.getStatus() == UserModel.Status.ENABLED.getValue();
    }
}
