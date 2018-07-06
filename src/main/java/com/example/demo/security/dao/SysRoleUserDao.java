package com.example.demo.security.dao;

import com.example.demo.security.entity.SysRoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : liuqitian
 * @date : 2018/7/6 10:55
 * @version : V1.1
 * @deprecated : 用户角色关系dao
 */
public interface SysRoleUserDao extends JpaRepository<SysRoleUser, Integer> {

    /**
     * 通过用户id查询
     * @param userId 用户id
     * @return  List<SysRoleUser> 查询结果集
     */
    List<SysRoleUser> findByUserId(int userId);
}
