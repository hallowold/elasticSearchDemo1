package com.example.demo.service;

import java.util.List;

import com.example.demo.common.config.ModuleNameEnum;
import com.example.demo.entity.Right;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.security.entity.SysRight;

/**
 * 权限服务定义接口
 * @author liuqitian
 * @version V1.1 因使用spring security同一管理权限，代码重构
 * @date 2018年6月12日 
 *
 */
public interface RightService {
	
	/**
	 * 新增权限
	 * @param 	right	权限实体
	 * @throws Exception 任何执行时的异常
	 */
	void addRight(SysRight right) throws Exception;
	
	/**
	 * 修改权限
	 * @param 	right	权限实体
	 * @throws Exception 任何执行时的异常
	 */
	void updateRight(SysRight right) throws Exception;
	
	/**
	 * 删除权限
	 * @param 	ids		权限id数组
	 * @throws Exception 任何执行时的异常
	 */
	void deleteRight(Integer[] ids) throws Exception;
	
	/**
	 * 通过名称模糊查询
	 * 
	 * @param 	name 		权限名
	 * @throws Demo1Exception 查出空集合时丢出自定义异常
	 * @return 	List<SysRight> 	权限实体列表
	 */
	List<SysRight> fuzzyFindByName(String name) throws Demo1Exception;
	
	/**
	 * 通过id查找权限
	 * @param 	id 		id
	 * @throws Demo1Exception    查询时丢出的异常，预计为无数据或链接中断
	 * @return 	Right	权限实体
	 */
	SysRight findById(Integer id) throws Demo1Exception;
	
	/**
	 * 获取所有权限
	 * @return	rights		权限集合
	 */
	Iterable<SysRight> findAllRight();

}
