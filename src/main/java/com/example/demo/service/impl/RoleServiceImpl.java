package com.example.demo.service.impl;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.StringUtil;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.security.dao.*;
import com.example.demo.security.entity.*;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 角色服务实现类
 * 
 * @author liuqitian
 * @date 2018年6月11日
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class RoleServiceImpl implements RoleService {

	@Autowired
	private SysRoleUserDao roleUserDao;

	@Autowired
	private SysRoleGroupDao roleGroupDao;

	@Autowired
	private SysRoleDao roleDao;

	@Autowired
	private SysRightDao rightDao;
	
	@Autowired
	private SysRoleRightDao roleRightDao;

	/**
     * 抛出Demo1Exception表示发现用户试图操作系统管理员角色
	 */
	@Override
	public void addRole(Map<String, Object> data) throws Exception{
		SysRole role = (SysRole) data.get("entity");
		Integer[] ids = (Integer[]) data.get("rightIds");
		//定义1号角色为系统管理员角色
		//若系统管理员角色已经存在，则不允许继续创建系统管理员角色
		if(StaticValues.ADMIN.equals(role.getName()) &&
				roleDao.findById(1).get() != null) {
			throw new Demo1Exception(StaticValues.ADMIN);
		}
		roleDao.save(role);
		if(ids != null && ids.length != 0) {
			for(int num = 0; num < ids.length; num++) {
				SysRight sysRight = rightDao.findById(ids[num]).get();
                roleRightDao.save(new SysRoleRight(role.getId(), role, sysRight.getId(), sysRight, new Date() ));
			}
		}
	}

	/**
	 * Demo1Exception表示发现用户试图操作系统管理员角色
	 */
	@Override
	public void updateRole(Map<String, Object> data) throws Exception{
		SysRole role = (SysRole) data.get("entity");
		Integer[] ids = (Integer[]) data.get("rightIds");
		//不允许对系统管理员角色进行任何修改
		if(role.getId() == 1L || StaticValues.ADMIN.equals(role.getName())) {
			throw new Demo1Exception(StaticValues.ADMIN);
		}
		/* 关联表的更新操作会比较麻烦且容易出错，故直接删除原有信息后重建 */
		roleDao.save(role);
		roleRightDao.deleteByRoleIdIn(new Integer[] {role.getId()});
		Arrays.stream(ids).forEach(id -> {
			SysRight sysRight = rightDao.findById(id).get();
			roleRightDao.save(new SysRoleRight(role.getId(), role, sysRight.getId(), sysRight, new Date()));
		});
	}

	/**
	 * Demo1Exception表示发现用户试图操作系统管理员角色，被其他用户或机构依赖，或指定的信息不存在
	 */
	@Override
	public Integer deleteRole(Integer[] ids) throws Exception{
		for(int num = 0; num < ids.length; num++) {
			if(ids[num] == 1) {
				throw new Demo1Exception(StaticValues.ADMIN);
			}
			if(ifHasRelationWithUser(ids[num]) || ifHasRelationWithGroup(ids[num])) {
				throw new Demo1Exception(StaticValues.DEPENDENCE);
			}
		}
		//删除角色信息，并同步删除该角色的所有roleright关系
		Integer result = roleDao.deleteByIdIn(ids);
		if(result == 0) {
			throw new Demo1Exception(StaticValues.NODATA);
		}
		roleRightDao.deleteByRoleIdIn(ids);
		return result;
	}

	public boolean ifHasRelationWithUser(Integer id) {
		boolean ifHas = false;
		List<SysRoleUser> list = roleUserDao.findByRoleId(id);
		if(list != null && list.size() > 0) {
			ifHas = true;
		}
		return ifHas;
	}

	public boolean ifHasRelationWithGroup(Integer id) {
		boolean ifHas = false;
		List<SysRoleGroup> list = roleGroupDao.findByRoleId(id);
		if(list != null && list.size() > 0) {
			ifHas = true;
		}
		return ifHas;
	}
	
	@Override
	public List<SysRole> fuzzyFindByName(String name) throws Exception{
		List<SysRole> list = roleDao.findByNameLike("%" + StringUtil.changeSpecialCharacter(name) + "%");
		if(list == null || list.size() < 1) {
			throw new Demo1Exception(StaticValues.SEARCH);
		}
		return list;
	}

	@Override
	public SysRole findById(Integer id) throws Exception{
		SysRole tempRole = roleDao.findById(id).get();
		if(tempRole == null) {
			throw new Demo1Exception(StaticValues.SEARCH);
		}
		return tempRole;
	}
	
	@Override
	public Iterable<SysRole> findAllRole() {
		return roleDao.findAll();
	}
}
