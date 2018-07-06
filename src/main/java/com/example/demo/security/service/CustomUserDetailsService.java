package com.example.demo.security.service;

import com.example.demo.security.dao.SysRoleUserDao;
import com.example.demo.security.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : liuqitian
 * @Date: 2018/7/2 10:27
 * @Version: V1.0
 * @Description: 实现用户信息服务类
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleUserDao sysRoleUserDao;

    /**
     * @Auther: liuqitian
     * @Date: 2018/7/4 10:43
     * @Version: V1.0
     * @Param: [userName]
     * @return: org.springframework.security.core.userdetails.UserDetails
     * @Description: 复写loadUserByUsername方法，使用我们自己的系统用户进行映射，替代默认的security用户
     *                     若后期有什么需要增加到security用户中的内容，也可以考虑在这里进行映射
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //SysUser对应数据库中的用户表，是最终存储用户和密码的表，可自定义
        //本例使用SysUser中的name作为用户名:
        SysUser user = userService.findByLoginName(userName);
        List<SysRoleUser> sysRoleUsers = sysRoleUserDao.findByUserId(user.getId());
        Set<SysRole> sysRoles = new HashSet<>();
        for(SysRoleUser sysRoleUser : sysRoleUsers) {
            sysRoles.add(sysRoleUser.getsRole());
        }
        user.setSysRoles(sysRoles);
        if (user != null) {
            // SecurityUser实现UserDetails并将SysUser的loginName映射为username
            return  new SecurityUser(user);
            /* 这段代码最初的设计意图为向security用户中加入自定义的属性，
                用于判断restful接口，后来通过其他方式实现，故此段代码被废弃。
                现在作为加入自定义属性的示例放置于此
            SysRole role = sysRoleDao.findByUserId(user.getId());
            List<SysRoleRight> rightList = sysRoleRightDao.findByRoleId(role.getId());
            List<MyGrantedAuthority> grantedAuthorities = new ArrayList<>();
            rightList.forEach(sysRoleRight -> grantedAuthorities.add(
                    new MyGrantedAuthority(sysRoleRight.getsRight().getRightUrl(), sysRoleRight.getsRight().getMthodType())));
            // SecurityUser实现UserDetails并将SysUser的name映射为username
            return  new SecurityUser(user, grantedAuthorities);*/
        } else {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }
    }

}
