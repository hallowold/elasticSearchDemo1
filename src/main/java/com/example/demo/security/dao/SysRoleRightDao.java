package com.example.demo.security.dao;

import com.example.demo.security.entity.SysRoleRight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : liuqitian
 * @date : 2018/7/2 12:33
 * @version : V1.1
 * 角色权限DAO
 */
public interface SysRoleRightDao extends JpaRepository<SysRoleRight, Integer> {

    List<SysRoleRight> findByRoleId(Integer roleId);

    List<SysRoleRight> findByRightId(Integer rightId);

    Integer deleteByRoleIdIn(Integer[] ids);

}
