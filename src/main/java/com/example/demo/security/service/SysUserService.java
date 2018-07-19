package com.example.demo.security.service;

import com.example.demo.security.dao.SysUserDao;
import com.example.demo.security.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuqitian
 * @date 2018/7/2 10:36
 * @version : V1.0
 * 系统用户操作层
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    public SysUser findById(Integer id) {
        return sysUserDao.findById(id).get();
    }

    public SysUser findByLoginName(String name) {
        return sysUserDao.findByLoginName(name);
    }

    public void update(SysUser user){
        sysUserDao.save(user);
    }

    public Long count() {
        return sysUserDao.count();
    }
}
