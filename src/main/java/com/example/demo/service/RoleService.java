package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.security.entity.SysRole;

/**
 * 角色服务定义接口
 * @author liuqitian
 * @date 2018年6月12日 
 *
 */
public interface RoleService {
	
	/**
	 * 新增角色
	 * @param 	data	角色实体与权限id数组
	 * @throws Exception 任何异常，特殊的，当发现用户试图操作系统管理员角色时，抛出Demo1Exception(StaticValues.ADMIN)
	 */
	void addRole(Map<String, Object> data) throws Exception;
	
	/**
	 * 修改角色
	 * @param 	data	角色实体与权限id数组
	 * @throws Exception 任何异常，特殊的，当发现用户试图操作系统管理员角色时，抛出Demo1Exception(StaticValues.ADMIN)
	 */
	void updateRole(Map<String, Object> data) throws Exception;
	
	/**
	 * 删除角色
	 * @param 	ids		角色id数组
	 * @throws Exception 任何异常，特殊的，当发现用户试图操作系统管理员角色时，抛出Demo1Exception(StaticValues.ADMIN)
	 */
	Integer deleteRole(Integer[] ids) throws Exception;
	
	/**
	 * 通过id查找角色
	 * 
	 * @param 	id 		id
	 * @return 	SysRole 	角色实体
	 */
	SysRole findById(Integer id) throws Exception;
	
	/**
	 * 通过登录名模糊查询
	 * 
	 * @param 	name 	角色名
	 * @throws Exception 任何异常，特殊的，当查询结果集为空，抛出Demo1Exception(StaticValues.SEARCH)
	 * @return 	List<SysRole>	角色实体列表
	 */
	List<SysRole> fuzzyFindByName(String name) throws Exception;
	
	/**
	 * 获取所有角色
	 * @return	roles		角色集合
	 */
	Iterable<SysRole> findAllRole();

}
