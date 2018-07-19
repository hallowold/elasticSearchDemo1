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

    void addGroup(Map<String, Object> dataMap);

    Integer deleteGroups(Integer[] ids);

    void updateGroup(Map<String, Object> dataMap);

    List<SysGroup> findAllGroups();

    List<SysGroup> fuzzyFindByName(String name) throws Demo1Exception;

    List<SysRole> findRolesByGroupId(Integer id);
}
