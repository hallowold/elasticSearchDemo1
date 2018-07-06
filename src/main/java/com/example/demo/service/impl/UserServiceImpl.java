package com.example.demo.service.impl;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.StringUtil;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.security.dao.SysUserDao;
import com.example.demo.security.entity.SysUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户服务实现类
 * 
 * @author liuqitian
 * @date 2018年6月11日
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserDao userDao;

	/**
	 * 新增用户
	 * @param user 用户实体
	 * @return boolean 是否成功
	 */
	@Override
	public boolean addUser(SysUser user) {
		boolean ifSuccess = false;
		try {
			this.findByLoginName(user.getLoginName());
		} catch (Demo1Exception ex) {
			//捕获到查不到值的异常才是期望结果，此时继续新增逻辑，并在成功后将返回值改为true
			if (StaticValues.SEARCH.equals(ex.getMessage())) {
				//密码需要加密
				try {
					user.setPassword(StringUtil.encode(user.getPassword().trim()));
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
	 * @param 	user 	用户实体
	 */
	@Override
	public void updateUser(SysUser user) {
		//密码需要加密
		user.setPassword(StringUtil.encode(user.getPassword()));
		userDao.save(user);
	}

	/**
	 * 删除用户
	 * @param 	ids		用户id数组
	 * @return	Integer	成功删除信息条数
	 */
	@Override
	public Integer deleteUser(Integer[] ids) {
		return userDao.deleteByIdIn(ids);
	}
	
	/**
	 * 通过id查找单一用户
	 * 
	 * @param 	id 	    id
	 * @throws Demo1Exception 查询结果为空时抛出Demo1Exception(StaticValues.SEARCH)
	 * @return User 	用户实体
	 */
	@Override
	public SysUser findById(Integer id) throws Demo1Exception{
		SysUser tempUser = userDao.findById(id).get();
		if(tempUser.getId() == null) {
			throw new Demo1Exception(StaticValues.SEARCH);
		}
		return tempUser;
	}

	/**
	 * 通过登录名查找单一用户
	 *
	 * @param 	loginName 	登录名
	 * @throws Demo1Exception 查到空值时抛出Demo1Exception(StaticValues.SEARCH)
	 * @return User 	用户实体
	 */
	@Override
	public SysUser findByLoginName(String loginName) throws Demo1Exception{
		SysUser tempUser = userDao.findByLoginName(loginName);
		if(tempUser == null) {
			throw new Demo1Exception(StaticValues.SEARCH);
		}
		return tempUser;
	}
	
	/**
	 * 通过登录名模糊匹配
	 * 
	 * @param 	loginName 	登录名
	 * @return List<User> 	用户实体列表
	 */
	@Override
	public List<SysUser> fuzzyFindByLoginName(String loginName) throws Demo1Exception{
		List<SysUser> list = userDao.findByLoginNameLike("*" + StringUtil.changeSpecialCharacter(loginName) + "*");
		if(list == null || list.size() < 1) {
			throw new Demo1Exception(StaticValues.SEARCH);
		}
		return list;
	}
	
	/**
	 * 获取所有用户
	 * @return	Iterable<SysUser> 用户集合
	 */
	@Override
	public Iterable<SysUser> findAllUser() {
		return userDao.findAll();
	}
	
}
