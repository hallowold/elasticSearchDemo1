package com.example.demo.service;

import com.example.demo.security.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 * 角色服务定义接口
 * @author liuqitian
 * @date 2018年6月12日 
 */
public interface RoleService {

	/**
	 * 抛出Demo1Exception表示发现用户试图操作系统管理员角色
	 */
	void addRole(Map<String, Object> data) throws Exception;

	/**
	 * Demo1Exception表示发现用户试图操作系统管理员角色
	 */
	void updateRole(Map<String, Object> data) throws Exception;

	/**
	 * Demo1Exception表示发现用户试图操作系统管理员角色，被其他用户或机构依赖，或指定的信息不存在
	 */
	Integer deleteRole(Integer[] ids) throws Exception;
	
	SysRole findById(Integer id) throws Exception;
	
	List<SysRole> fuzzyFindByName(String name) throws Exception;
	
	Iterable<SysRole> findAllRole();

}
