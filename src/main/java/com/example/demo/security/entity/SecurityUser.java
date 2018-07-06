package com.example.demo.security.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * @Auther: liuqitian
 * @Date: 2018/7/2 10:28
 * @Version: V1.0
 * @Description: 权限用户
 */
public class SecurityUser extends SysUser implements UserDetails {
    private static final long serialVersionUID = 1L;
//    private Collection<? extends GrantedAuthority> grantedAuthorities;

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
        }
    }

    /* 这段代码最初的设计意图为向security用户中加入自定义的属性，
                用于判断restful接口，后来通过其他方式实现，故此段代码被废弃。
                现在作为加入自定义属性的示例放置于此
    public SecurityUser(SysUser suser, Collection<? extends GrantedAuthority> grantedAuthorities) {
        if(suser != null && grantedAuthorities != null)
        {
            this.setId(suser.getId());
            this.setLoginName(suser.getLoginName());
            this.setShowName(suser.getShowName());
            this.setEmail(suser.getEmail());
            this.setPassword(suser.getPassword());
            this.setcreateTime(suser.getcreateTime());
            this.setSysRoles(suser.getSysRoles());
            this.grantedAuthorities = grantedAuthorities;
        }
    }*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /* 配合SecurityUser(SysUser suser, Collection<? extends GrantedAuthority> grantedAuthorities)使用
        return this.grantedAuthorities;*/
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
