package com.example.demo.security.dao;

import com.example.demo.security.entity.SysRoleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : liuqitian
 * @date : 2018/7/6 10:55
 * @version : V1.2
 * 用户机构关系dao
 */
public interface SysRoleGroupDao extends JpaRepository<SysRoleGroup, Integer> {

    List<SysRoleGroup> findByGroupId(Integer groupId);

    List<SysRoleGroup> findByRoleId(Integer roleId);

    Integer deleteByGroupIdIn(Integer[] ids);
}
