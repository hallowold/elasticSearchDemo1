package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.Role;

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
	 * @return	boolean
	 */
	void addRole(Map<String, Object> data) throws Exception;
	
	/**
	 * 修改角色
	 * @param 	data	角色实体与权限id数组
	 * @return	boolean
	 */
	void updateRole(Map<String, Object> data) throws Exception;
	
	/**
	 * 删除角色
	 * @param 	ids		角色id数组
	 * @return	boolean
	 */
	void deleteRole(Long[] ids) throws Exception;
	
	/**
	 * 通过id查找角色
	 * 
	 * @param 	id 		id
	 * @return 	Role 	角色实体
	 */
	Role findById(Long id) throws Exception;
	
	/**
	 * 通过登录名查找近似角色列表
	 * 
	 * @param 	loginName 	角色名
	 * @return 	List<Role>	角色实体列表
	 */
	List<Role> fuzzyFindByName(String roleName) throws Exception;
	
	/**
	 * 获取所有角色
	 * @return	roles		角色集合
	 */
	Iterable<Role> findAllRole();

}
