package com.example.demo.security.dao;

import com.example.demo.security.entity.SysRoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : liuqitian
 * @date : 2018/7/6 10:55
 * @version : V1.1
 * 用户角色关系dao
 */
public interface SysRoleUserDao extends JpaRepository<SysRoleUser, Integer> {

    List<SysRoleUser> findByUserId(Integer userId);

    List<SysRoleUser> findByRoleId(Integer roleId);

    Integer deleteByRoleIdIn(Integer[] ids);

    Integer deleteByUserIdIn(Integer[] ids);
}
