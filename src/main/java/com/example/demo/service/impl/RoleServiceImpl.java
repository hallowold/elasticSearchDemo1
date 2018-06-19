package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.RoleDAO;
import com.example.demo.dao.RoleRightDAO;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleRight;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.service.RoleService;

/**
 * 角色服务实现类
 * 
 * @author liuqitian
 * @date 2018年6月11日
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDao;
	
	@Autowired
	private RoleRightDAO roleRightDao;

	/**
	 * 新增角色
	 * 
	 * @param 	data 	角色实体与权限id数组
	 * @return 	boolean
	 */
	@Transactional
	public void addRole(Map<String, Object> data) throws Exception{
		Role role = (Role) data.get("role");
		Long[] ids = (Long[]) data.get("rightIds");
		if(role.getId() == 1l || "系统管理员".equals(role.getName()) &&
				roleDao.findById(1l).get() != null) {
			throw new Demo1Exception("admin");
		}
		//分别计算role和roleright的主键
		Long newRoleRightID = roleRightDao.count() + 1;
		//存储角色
		roleDao.save(role);
		//存储roleright关系
		if(ids != null && ids.length != 0) {
			for(int num = 0; num < ids.length; num++) {
				roleRightDao.save(new RoleRight(newRoleRightID + 1, role.getId(), ids[num]));
			}
		}
	}

	/**
	 * 修改角色
	 * 
	 * @param 	data 	角色实体与权限id数组
	 * @return 	boolean
	 */
	@Transactional
	public void updateRole(Map<String, Object> data) throws Exception{
		Role role = (Role) data.get("role");
		Long[] ids = (Long[]) data.get("rightIds");
		if(role.getId() == 1l || "系统管理员".equals(role.getName())) {
			throw new Demo1Exception("admin");
		}
		//计算roleright的主键
		Long newRoleRightID = roleRightDao.count() + 1;
		//存储角色
		roleDao.save(role);
		//删除该角色的所有roleright关系并重新记录
		roleRightDao.deleteByRoleIdIn(new Long[] {role.getId()});
		if(ids != null && ids.length != 0) {
			for(int num = 0; num < ids.length; num++) {
				roleRightDao.save(new RoleRight(newRoleRightID + 1, role.getId(), ids[num]));
			}
		}
	}

	/**
	 * 删除角色
	 * @param 	ids		角色id数组
	 * @return	boolean
	 */
	@Transactional
	public void deleteRole(Long[] ids) throws Exception{
		for(int num = 0; num < ids.length; num++) {
			if(ids[num] == 1l) {
				throw new Demo1Exception("admin");
			}
		}
		//删除角色信息，并同步删除该角色的所有roleright关系
		roleRightDao.deleteByRoleIdIn(ids);
		roleDao.deleteByIdIn(ids);
	}
	
	/**
	 * 通过角色名查找近似角色列表
	 * 
	 * @param 	loginName 	角色名
	 * @return 	List<Role>	角色实体列表
	 */
	@Transactional
	public List<Role> fuzzyFindByName(String roleName) throws Exception{
		List<Role> list = roleDao.findByName("*" + roleName + "*");
		if(list == null || list.size() < 1) {
			throw new Demo1Exception("查询");
		}
		return list;
	}

	/**
	 * 通过id查找角色
	 * 
	 * @param 	id 		id
	 * @return 	Role 	角色实体
	 */
	@Transactional
	public Role findById(Long id) throws Exception{
		Role tempRole = roleDao.findById(id).get();
		if(tempRole == null || tempRole.getId() == null) {
			throw new Demo1Exception("查询");
		}
		return tempRole;
	}
	
	/**
	 * 获取所有角色
	 * @return	roles		角色集合
	 */
	@Transactional
	public Iterable<Role> findAllRole() {
		return roleDao.findAll();
	}
}
