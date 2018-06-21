package com.example.demo.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.util.MD5Util;
import com.example.demo.dao.UserDAO;
import com.example.demo.entity.User;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.service.UserService;

/**
 * 用户服务实现类
 * 
 * @author liuqitian
 * @date 2018年6月11日
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	/**
	 * 新增用户
	 * 
	 * @param user
	 *            用户实体
	 * @return boolean
	 */
	@Transactional
	public boolean addUser(User user) {
		boolean ifSuccess = false;
		try {
			Long l = userDao.findMaxId();
			this.findByLoginName(user.getLoginName());
		} catch (Demo1Exception ex) {
			//捕获到查不到值的异常才是期望结果，此时继续新增逻辑，并在成功后将返回值改为true
			if ("查询".equals(ex.getMessage())) {
				//密码需要加密
				try {
				user.setPassword(MD5Util.getEncryptedPwd(user.getPassword()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				userDao.save(user);
				ifSuccess = true;
			}
		}
		return ifSuccess;
	}

	/**
	 * 修改用户
	 * 
	 * @param 	user 	用户实体
	 * @return 	boolean
	 */
	@Transactional
	public void updateUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		//密码需要加密
		user.setPassword(MD5Util.getEncryptedPwd(user.getPassword()));
		userDao.save(user);
	}

	/**
	 * 删除用户
	 * @param 	ids		用户id数组
	 * @return	boolean
	 */
	@Transactional
	public void deleteUser(Long[] ids) {
		userDao.deleteByRoleIdIn(ids);
	}
	
	/**
	 * 通过id查找单一用户
	 * 
	 * @param 	loginName 	登录名
	 * @return 	User 	用户实体
	 */
	@Transactional
	public User findById(Long id) throws Demo1Exception{
		User tempUser = userDao.findById(id).get();
		if(tempUser == null || tempUser.getId() == null) {
			throw new Demo1Exception("查询");
		}
		return tempUser;
	}

	/**
	 * 通过登录名查找单一用户(供登录使用)
	 * 
	 * @param 	loginName 	登录名
	 * @return 	User 	用户实体
	 */
	@Transactional
	public User findByLoginName(String loginName) throws Demo1Exception{
		User tempUser = new User();
		List<User> list = userDao.findByLoginName(loginName);
		if(list == null || list.size() < 1) {
			//没查到
			throw new Demo1Exception("查询");
		} else if(list.size() > 1) {
			//数据被污染，有重复数据出现
			throw new Demo1Exception("重复的登录名");
		} else {
			tempUser = list.get(0);
		}
		return tempUser;
	}
	
	/**
	 * 通过登录名查找近似用户列表
	 * 
	 * @param 	loginName 	登录名
	 * @return 	List<User> 	用户实体列表
	 */
	@Transactional
	public List<User> fuzzyFindByLoginName(String loginName) throws Demo1Exception{
		List<User> list = userDao.findByLoginName("*" + loginName + "*");
		if(list == null || list.size() < 1) {
			throw new Demo1Exception("查询");
		}
		return list;
	}
	
	/**
	 * 获取所有用户
	 * @return	users		用户集合
	 */
	@Transactional
	public Iterable<User> findAllUser() {
		return userDao.findAll();
	}
	
	/**
	 * 通过登录名删除用户，精确匹配
	 * @param	loginName	登录名
	 * @return	long		删除条数
	 */
	@Transactional
	public Long deleteByLoginName(String loginName) {
		return userDao.deleteByLoginName(loginName);
	}
	
	/**
	 * 校验用户名和密码
	 * @param 	loginName
	 * @param 	password
	 * @return	boolean
	 */
	@Transactional
	public User varifyUser(String loginName, String password) {
		boolean ifSuccess = false;
		User user = new User();
		try {
			//获取用户，仅当密码匹配成功后，将校验结果的值改为true，其他任何情况都是返回false
			user = this.findByLoginName(loginName);
			if(user.getPassword() != null && MD5Util.validPasswd(password, user.getPassword())) {
				ifSuccess = true;
			}
			if (!ifSuccess) {
				user = null;
			}
		} catch (Exception ex) {
			user = null;
		}
		return user;
	}
}
