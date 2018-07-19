package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.Demo1Exception;
import com.example.demo.security.entity.SysRight;

/**
 * 权限服务定义接口
 * @author liuqitian
 * @version V1.1 因使用spring security同一管理权限，代码重构
 * @date 2018年6月12日 
 */
public interface RightService {
	
	void addRight(SysRight right) throws Exception;

	/**
	 * Demo1Exception异常表示权限仍然被其他角色使用
	 */
	void updateRight(SysRight right) throws Exception;

	/**
	 * Demo1Exception异常表示权限仍然被其他角色使用或指定的数据不存在
	 */
	Integer deleteRight(Integer[] ids) throws Exception;

	List<SysRight> fuzzyFindByName(String name) throws Demo1Exception;
	
	SysRight findById(Integer id) throws Demo1Exception;
	
	Iterable<SysRight> findAllRight();

}
