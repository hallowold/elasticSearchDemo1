package com.example.demo.security.dao;

import com.example.demo.security.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: liuqitian
 * @Date: 2018/7/2 10:37
 * @Version: V1.0
 * @Description:
 */
@Repository
public interface SysUserDao extends JpaRepository<SysUser, Integer> {

    SysUser findByLoginName(String name);
}
