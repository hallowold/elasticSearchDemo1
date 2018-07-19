package com.example.demo.service;

import com.example.demo.exception.Demo1Exception;
import com.example.demo.security.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 用户服务定义接口
 * @author liuqitian
 * @date 2018年6月11日 
 *
 */
public interface UserService {

	boolean addUser(Map<String, Object> dataMap);

	void updateUser(Map<String, Object> dataMap);

	/**
	 * Demo1Exception 表示发现用户试图删除自己，或者无指定信息
	 */
	Integer deleteUser(Integer[] ids) throws Demo1Exception;

	SysUser findById(Integer id) throws Demo1Exception;

	SysUser findByLoginName(String loginName) throws Demo1Exception;

	List<SysUser> fuzzyFindByLoginName(String loginName) throws Demo1Exception;

	Iterable<SysUser> findAllUser();

}
