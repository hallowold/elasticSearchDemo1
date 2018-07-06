package com.example.demo.security.dao;

import com.example.demo.security.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : liuqitian
 * @Date: 2018/7/2 12:16
 * @Version: V1.0
 * @Description:
 */
@Repository
public interface SysRoleDao extends JpaRepository<SysRole, Integer> {

    /**
     * 通过用户id查询
     * @param userId 用户Id
     * @return SysRole 角色实体
     */
//    SysRole findByUserId(Integer userId);

    /**
     * 通过ids批量删除
     * @param ids id数组
     * @return Integer 成功删除信息条数
     */
    Integer deleteByIdIn(Integer[] ids);

    /**
     * 通过name模糊查询
     * @param name 查询字段
     * @return  List<SysRole>
     */
    List<SysRole> findByNameLike(String name);

}
