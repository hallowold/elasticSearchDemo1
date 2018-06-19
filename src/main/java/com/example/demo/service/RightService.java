package com.example.demo.service;

import java.util.List;

import com.example.demo.common.config.ModuleNameEnum;
import com.example.demo.entity.Right;
import com.example.demo.exception.Demo1Exception;

/**
 * 权限服务定义接口
 * @author liuqitian
 * @date 2018年6月12日 
 *
 */
public interface RightService {
	
	/**
	 * 新增权限
	 * @param 	right	权限实体
	 * @return	boolean
	 */
	void addRight(Right right) throws Exception;
	
	/**
	 * 修改权限
	 * @param 	right	权限实体
	 * @return	boolean
	 */
	void updateRight(Right right) throws Exception;
	
	/**
	 * 删除权限
	 * @param 	ids		权限id数组
	 * @return	boolean
	 */
	void deleteRight(Long[] ids) throws Exception;
	
	/**
	 * 通过权限名查找权限
	 * @param 	rightName	权限名
	 * @return	right		权限实体
	 */
	Right findByName(String rightName) throws Exception;
	
	/**
	 * 通过权限名查找近似权限列表
	 * 
	 * @param 	rightName 		权限名
	 * @return 	List<Right> 	权限实体列表
	 */
	List<Right> fuzzyFindByName(String rightName) throws Demo1Exception;
	
	/**
	 * 通过权限名查找近似权限列表
	 * 
	 * @param 	rightName 		权限名
	 * @return 	List<Right> 	权限实体列表
	 */
	Right findById(Long id) throws Demo1Exception;
	
	/**
	 * 获取所有权限
	 * @return	rights		权限集合
	 */
	Iterable<Right> findAllRight();

	/**
	 * 判断当前后台用户是否有某功能模块的权限
	 * @param 	roleId			角色id，从当前用户获取
	 * @param 	ModuleNameEnum	模块识别Enum，从config中的ModuleNameEnum选取
	 * @return
	 * @throws 	Demo1Exception
	 */
	boolean ifHasRight(Long roleId, ModuleNameEnum moduleNameEnum) throws Demo1Exception;
	
}
