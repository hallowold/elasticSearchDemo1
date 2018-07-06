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

    /**
     * 通过角色id批量删除
     * @param ids 角色id数组
     * @return Integer 删除信息条数
     */
    Integer deleteByRoleIdIn(Integer[] ids);

    /**
     * 通过用户id批量删除
     * @param ids 用户id数组
     * @return Integer 删除信息条数
     */
    Integer deleteByUserIdIn(Integer[] ids);
}
