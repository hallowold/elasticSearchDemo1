package com.example.demo.service;

import com.example.demo.exception.Demo1Exception;
import com.example.demo.security.entity.SysGroup;
import com.example.demo.security.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 * @author : liuqitian
 * @date : 2018/7/16 12:14
 * @version : V1.0
 * 机构服务定义接口
 */
public interface SysGroupService {

    /**
     * 新增机构
     * @param dataMap 信息
     */
    void addGroup(Map<String, Object> dataMap);

    /**
     * 批量删除
     * @param ids 机构id数组
     * @return Integer 删除信息条数
     */
    Integer deleteGroups(Integer[] ids);

    /**
     * 修改机构
     * @param dataMap 信息
     */
    void updateGroup(Map<String, Object> dataMap);

    /**
     * 获取所有机构实体
     * @return List 机构集合
     */
    List<SysGroup> findAllGroups();

    /**
     * 根据名称模糊查询
     * @param name 名称字段
     * @throws Demo1Exception 指定在无数据时候抛出此异常
     * @return List 机构集合
     */
    List<SysGroup> fuzzyFindByName(String name) throws Demo1Exception;

    /**
     * 根据机构id查找其默认的角色信息
     * @param id 机构id
     * @return List 角色数组
     */
    List<SysRole> findRolesByGroupId(Integer id);
}
