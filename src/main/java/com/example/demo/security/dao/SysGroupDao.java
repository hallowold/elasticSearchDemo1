package com.example.demo.security.dao;

import com.example.demo.security.entity.SysGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : liuqitian
 * @date : 2018/7/16 12:13
 * @version : V1.2
 * 机构dao
 */
public interface SysGroupDao extends JpaRepository<SysGroup, Integer> {

    /**
     * 用名称模糊查询
     * @param name 名称字段
     * @return List 机构列表
     */
    List<SysGroup> findByNameLike(String name);

    /**
     * 用id数组来批量删除
     * @param ids id数组
     * @return Integer 成功删除的信息条数
     */
    Integer deleteByIdIn(Integer[] ids);
}
