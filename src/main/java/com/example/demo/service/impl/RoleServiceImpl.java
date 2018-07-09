package com.example.demo.service.impl;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.StringUtil;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.security.dao.SysRightDao;
import com.example.demo.security.dao.SysRoleDao;
import com.example.demo.security.dao.SysRoleRightDao;
import com.example.demo.security.entity.SysRight;
import com.example.demo.security.entity.SysRole;
import com.example.demo.security.entity.SysRoleRight;
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
	private SysRoleDao roleDao;

	@Autowired
	private SysRightDao rightDao;
	
	@Autowired
	private SysRoleRightDao roleRightDao;

	/**
	 * 新增角色
     *  此处定义1号角色为系统管理员角色
	 * 
	 * @param 	data 	角色实体与权限id数组
	 * @throws Exception 任何异常，特殊的，当发现用户试图操作系统管理员角色时，抛出Demo1Exception(StaticValues.ADMIN)
	 */
	@Override
	public void addRole(Map<String, Object> data) throws Exception{
		SysRole role = (SysRole) data.get("entity");
		Integer[] ids = (Integer[]) data.get("rightIds");
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
	 * 修改角色
	 * 
	 * @param 	data 	角色实体与权限id数组
	 * @throws Exception 任何异常，特殊的，当发现用户试图操作系统管理员角色时，抛出Demo1Exception(StaticValues.ADMIN)
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
			System.out.println(sysRight.getName());
			roleRightDao.save(new SysRoleRight(role.getId(), role, sysRight.getId(), sysRight, new Date()));
		});
	}

	/**
	 * 删除角色
	 * @param 	ids		角色id数组
	 * @throws Exception 任何异常，特殊的，当发现用户试图操作系统管理员角色时，抛出Demo1Exception(StaticValues.ADMIN)
	 */
	@Override
	public Integer deleteRole(Integer[] ids) throws Exception{
		for(int num = 0; num < ids.length; num++) {
			if(ids[num] == 1) {
				throw new Demo1Exception(StaticValues.ADMIN);
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
	
	/**
	 * 通过角色名模糊查询
	 * 
	 * @param 	name 	角色名
	 * @throws Exception 任何异常，特殊的，当查询结果集为空，抛出Demo1Exception(StaticValues.SEARCH)
	 * @return 	List<SysRole>	角色实体列表
	 */
	@Override
	public List<SysRole> fuzzyFindByName(String name) throws Exception{
		List<SysRole> list = roleDao.findByNameLike("%" + StringUtil.changeSpecialCharacter(name) + "%");
		if(list == null || list.size() < 1) {
			throw new Demo1Exception(StaticValues.SEARCH);
		}
		return list;
	}

	/**
	 * 通过id查找角色
	 * 
	 * @param 	id 		id
	 * @throw Exception 任何异常，特殊的，当查询结果为空，抛出Demo1Exception("StaticValues.SEARCH)
	 * @return 	Role 	角色实体
	 */
	@Override
	public SysRole findById(Integer id) throws Exception{
		SysRole tempRole = roleDao.findById(id).get();
		if(tempRole == null) {
			throw new Demo1Exception(StaticValues.SEARCH);
		}
		return tempRole;
	}
	
	/**
	 * 获取所有角色
	 * @return	roles		角色集合
	 */
	@Override
	public Iterable<SysRole> findAllRole() {
		return roleDao.findAll();
	}
}
