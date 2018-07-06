package com.example.demo.security.dao;

import com.example.demo.security.entity.SysRoleRight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : liuqitian
 * @Date: 2018/7/2 12:33
 * @Version: V1.1
 * @Description: 角色权限DAO
 */
public interface SysRoleRightDao extends JpaRepository<SysRoleRight, Integer> {

    /**
     * 通过roleId查询
     * @param roleId 角色id
     * @return  List<SysRoleRight>
     */
    List<SysRoleRight> findByRoleId(Integer roleId);

    /**
     * 通过rightId查询
     * @param rightId 权限id
     * @return List<SysRoleRight>
     */
    List<SysRoleRight> findByRightId(Integer rightId);

    /**
     * 通过角色id进行删除
     * @param ids 角色ids
     * @return Integer 成功删除信息条数
     */
    Integer deleteByRoleIdIn(Integer[] ids);

}
