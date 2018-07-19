package com.example.demo.security.dao;

import com.example.demo.security.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : liuqitian
 * @date  2018/7/2 12:16
 * @version : V1.0
 * 角色dao
 */
@Repository
public interface SysRoleDao extends JpaRepository<SysRole, Integer> {

    Integer deleteByIdIn(Integer[] ids);

    List<SysRole> findByNameLike(String name);

}
