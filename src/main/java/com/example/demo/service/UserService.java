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

	/**
	 * 新增用户
	 * @param dataMap 用户实体和角色id数组的map
	 * @return boolean 是否成功
	 */
	boolean addUser(Map<String, Object> dataMap);

	/**
	 * 修改用户
	 * @param 	dataMap 用户实体和角色id数组的map
	 */
	void updateUser(Map<String, Object> dataMap);

	/**
	 * 删除用户
	 * @param 	ids		用户id数组
	 * @throws Demo1Exception 发现用户试图删除自己时抛出
	 * @return	Integer	成功删除信息条数
	 */
	Integer deleteUser(Integer[] ids) throws Demo1Exception;

	/**
	 * 通过id查找单一用户
	 *
	 * @param 	id 	    id
	 * @throws Demo1Exception 查询结果为空时抛出Demo1Exception(StaticValues.SEARCH)
	 * @return User 	用户实体
	 */
	SysUser findById(Integer id) throws Demo1Exception;

	/**
	 * 通过登录名查找单一用户
	 *
	 * @param 	loginName 	登录名
	 * @throws Demo1Exception 查到空值时抛出Demo1Exception(StaticValues.SEARCH)
	 * @return User 	用户实体
	 */
	SysUser findByLoginName(String loginName) throws Demo1Exception;

	/**
	 * 通过登录名模糊匹配
	 *
	 * @param 	loginName 	登录名
	 * @return List<User> 	用户实体列表
	 */
	List<SysUser> fuzzyFindByLoginName(String loginName) throws Demo1Exception;

	/**
	 * 获取所有用户
	 * @return	Iterable<SysUser> 用户集合
	 */
	Iterable<SysUser> findAllUser();

}
