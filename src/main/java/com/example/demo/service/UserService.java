package com.example.demo.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.exception.Demo1Exception;

/**
 * 用户服务定义接口
 * @author liuqitian
 * @date 2018年6月11日 
 *
 */
public interface UserService {
	
	/**
	 * 新增用户
	 * @param 	user	用户实体
	 * @return	boolean
	 */
	boolean addUser(User user);
	
	/**
	 * 修改用户
	 * @param 	user	用户实体
	 * @return	boolean
	 */
	void updateUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;
	
	/**
	 * 删除用户
	 * @param 	ids		用户id数组
	 * @return	boolean
	 */
	void deleteUser(Long[] ids);
	
	/**
	 * 通过id查找单一用户
	 * 
	 * @param 	id 		用户id
	 * @return 	User 	用户实体
	 */
	User findById(Long id) throws Demo1Exception;
	
	/**
	 * 通过登录名查找单一用户
	 * 
	 * @param 	loginName 	登录名
	 * @return 	User 	用户实体
	 */
	User findByLoginName(String loginName) throws Exception;
	
	/**
	 * 通过登录名查找近似用户列表
	 * 
	 * @param 	loginName 	登录名
	 * @return 	List<User> 	用户实体列表
	 */
	List<User> fuzzyFindByLoginName(String loginName) throws Demo1Exception;
	
	/**
	 * 获取所有用户
	 * @return	users		用户集合
	 */
	Iterable<User> findAllUser();
	
	/**
	 * 通过登录名删除用户，精确匹配
	 * @param	loginName	登录名
	 * @return	long		删除条数
	 */
	Long deleteByLoginName(String loginName);
	
	/**
	 * 校验用户名和密码
	 * @param 	loginName
	 * @param 	password
	 * @return	boolean
	 */
	User varifyUser(String loginName, String password);

}
