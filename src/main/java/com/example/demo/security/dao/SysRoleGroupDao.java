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

    /**
     * 通过机构id查找
     * @param id 机构Id
     * @return List 数据集合
     */
    List<SysRoleGroup> findByGroupId(Integer id);

    /**
     * 通过机构id进行删除
     * @param ids 角色ids
     * @return Integer 成功删除信息条数
     */
    Integer deleteByGroupIdIn(Integer[] ids);
}
