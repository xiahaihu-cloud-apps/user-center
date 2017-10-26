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

    private String username;

    private String password;

    private boolean enabled;

    private String salt;

    private static final long serialVersionUID = -4526272797216934966L;

    public static UserDetailsBean generateByUserModel(UserModel userModel) {
        UserDetailsBean userDetailsBean = new UserDetailsBean();
        userDetailsBean.setUsername(userModel.getEmail());
        userDetailsBean.setSalt(userModel.getSalt());
        userDetailsBean.setPassword(userModel.getPassword());
        userDetailsBean.setEnabled(UserModel.Status.ENABLED.getValue() == userModel.getStatus());
        return userDetailsBean;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
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
        return enabled;
    }
}
