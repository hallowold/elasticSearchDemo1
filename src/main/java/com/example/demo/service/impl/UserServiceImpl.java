package com.example.demo.service.impl;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.StringUtil;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.security.config.LoginSuccessHandler;
import com.example.demo.security.dao.SysRoleDao;
import com.example.demo.security.dao.SysRoleUserDao;
import com.example.demo.security.dao.SysUserDao;
import com.example.demo.security.entity.SysRoleUser;
import com.example.demo.security.entity.SysUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

	@Autowired
	private SysRoleUserDao sysRoleUserDao;

	@Autowired
	private SysRoleDao sysRoleDao;

	@Override
	public boolean addUser(Map<String, Object> dataMap){
		SysUser user = (SysUser) dataMap.get("entity");
		Integer[] roleIds = (Integer[]) dataMap.get("roleIds");
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
                Arrays.stream(roleIds).forEach(
                		id ->sysRoleUserDao.save(new SysRoleUser(sysRoleDao.findById(id).get(),id, user, user.getId())));
				ifSuccess = true;
			}
		}
		return ifSuccess;
	}

	@Override
	public void updateUser(Map<String, Object> dataMap) {
		SysUser user = (SysUser) dataMap.get("entity");
		Integer[] roleIds = (Integer[]) dataMap.get("roleIds");
		//密码需要加密
		user.setPassword(StringUtil.encode(user.getPassword()));
		/* 中间表的修改操作繁琐且容易出错，故采用全部删除再根据新数据新增的方式 */
		sysRoleUserDao.deleteByRoleIdIn(roleIds);
        Arrays.stream(roleIds).forEach(id ->sysRoleUserDao.save(new SysRoleUser(sysRoleDao.findById(id).get(),id, user, user.getId())));
		userDao.save(user);

	}

	/**
	 * Demo1Exception 表示发现用户试图删除自己，或者无指定信息
	 */
	@Override
	public Integer deleteUser(Integer[] ids) throws Demo1Exception{
        SysUser user = LoginSuccessHandler.getCurrentUser();
        //不能删除自己
        if(Arrays.stream(ids).anyMatch(id -> id.intValue() == user.getId().intValue())) {
            throw new Demo1Exception(StaticValues.MYSELF);
        }
		Integer result = userDao.deleteByIdIn(ids);
        if(result == 0) {
            throw new Demo1Exception(StaticValues.NODATA);
        }
		sysRoleUserDao.deleteByUserIdIn(ids);
		return result;
	}
	
	@Override
	public SysUser findById(Integer id) throws Demo1Exception{
		SysUser tempUser = userDao.findById(id).get();
		if(tempUser.getId() == null) {
			throw new Demo1Exception(StaticValues.SEARCH);
		}
		return tempUser;
	}

	@Override
	public SysUser findByLoginName(String loginName) throws Demo1Exception{
		SysUser tempUser = userDao.findByLoginName(loginName);
		if(tempUser == null) {
			throw new Demo1Exception(StaticValues.SEARCH);
		}
		return tempUser;
	}
	
	@Override
	public List<SysUser> fuzzyFindByLoginName(String loginName) throws Demo1Exception{
		List<SysUser> list = userDao.findByLoginNameLike("%" + StringUtil.changeSpecialCharacter(loginName) + "%");
		if(list == null || list.size() < 1) {
			throw new Demo1Exception(StaticValues.SEARCH);
		}
		return list;
	}
	
	@Override
	public Iterable<SysUser> findAllUser() {
		return userDao.findAll();
	}
	
}
