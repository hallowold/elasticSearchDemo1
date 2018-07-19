package com.example.demo.security.service;

import com.example.demo.security.dao.SysRoleUserDao;
import com.example.demo.security.entity.SecurityUser;
import com.example.demo.security.entity.SysRole;
import com.example.demo.security.entity.SysRoleUser;
import com.example.demo.security.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : liuqitian
 * @date : 2018/7/2 10:27
 * @version : V1.0
 * 实现用户信息服务类
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleUserDao sysRoleUserDao;

    /**
     * 复写loadUserByUsername方法，使用我们自己的系统用户进行映射，替代默认的security用户
     *   若后期有什么需要增加到security用户中的内容，也可以考虑在这里进行映射
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //SysUser对应数据库中的用户表，是最终存储用户和密码的表，可自定义
        //本例使用SysUser中的loginName作为用户名:
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
        } else {
            throw new UsernameNotFoundException("UserName [" + userName + "] not found");
        }
    }

}
