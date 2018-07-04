package com.example.demo.security.dao;

import com.example.demo.security.entity.SysRoleRight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Auther: liuqitian
 * @Date: 2018/7/2 12:33
 * @Version: V1.0
 * @Description:
 */
public interface SysRoleRightDao extends JpaRepository<SysRoleRight, Integer> {

    List<SysRoleRight> findByRoleId(Integer id);

//    List<SysRoleRight> findByUserId(Integer userId);
}
