package com.example.demo.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * @author : liuqitian
 * @date : 2018/7/2 10:28
 * @version : V1.0
 * 权限用户
 */
public class SecurityUser extends SysUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    public SecurityUser() {}

    public SecurityUser(SysUser suser) {
        if(suser != null)
        {
            this.setId(suser.getId());
            this.setLoginName(suser.getLoginName());
            this.setShowName(suser.getShowName());
            this.setEmail(suser.getEmail());
            this.setPassword(suser.getPassword());
            this.setCreateTime(suser.getCreateTime());
            this.setSysRoles(suser.getSysRoles());
            this.setUpdateTime(suser.getUpdateTime());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Set<SysRole> userRoles = this.getSysRoles();

        if(userRoles != null)
        {
            for (SysRole role : userRoles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getLoginName();
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
        return true;
    }
}
