package com.example.demo.security.dao;

import com.example.demo.security.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : liuqitian
 * @date : 2018/7/2 10:37
 * @version : V1.0
 * 用户dao
 */
@Repository
public interface SysUserDao extends JpaRepository<SysUser, Integer> {

    SysUser findByLoginName(String loginName);

    List<SysUser> findByLoginNameLike(String loginName);

    Integer deleteByIdIn(Integer[] ids);
}
