package com.example.demo.security.dao;

import com.example.demo.security.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: liuqitian
 * @Date: 2018/7/2 12:16
 * @Version: V1.0
 * @Description:
 */
@Repository
public interface SysRoleDao extends JpaRepository<SysRole, Integer> {

    SysRole findByUserId(Integer userId);

}
