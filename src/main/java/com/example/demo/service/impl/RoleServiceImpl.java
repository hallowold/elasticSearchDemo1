package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.demo.common.util.KeyNumberUtil;
import com.example.demo.dao.RightDAO;
import com.example.demo.entity.Right;
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
	private RightDAO rightDao;
	
	@Autowired
	private RoleRightDAO roleRightDao;

	/**
	 * 新增角色
     *  此处定义1号角色为系统管理员角色
	 * 
	 * @param 	data 	角色实体与权限id数组
	 * @return 	boolean
	 */
	@Transactional
	public void addRole(Map<String, Object> data) throws Exception{
		Role role = (Role) data.get("role");
		Long[] ids = (Long[]) data.get("rightIds");
		//若系统管理员角色已经存在，则不允许继续创建系统管理员角色
		if("系统管理员".equals(role.getName()) &&
				roleDao.findById(1l).get() != null) {
			throw new Demo1Exception("admin");
		}
		//存储角色
		role.setId(KeyNumberUtil.nextId());
		roleDao.save(role);
		//存储roleright关系
		if(ids != null && ids.length != 0) {
			for(int num = 0; num < ids.length; num++) {
                roleRightDao.save(new RoleRight(KeyNumberUtil.nextId(), role, rightDao.findById(ids[num]).get()));
			}
		}
	}

	/**
	 * @Auther: liuqitian
	 * @Date: 2018/6/26 15:14
	 * @Version: V1.0
	 * @Param: []
	 * @return: void
	 * @Description: 初始化时添加系统管理员角色
	 */
	public void addAdmin() {
		Role role = new Role();
		role.setId(1l);
		role.setName("系统管理员");
		role.setCreateDate(new Date());
		roleDao.save(role);
		Iterable<Right> rightIterable = rightDao.findAll();
		for (Right right : rightIterable) {
			roleRightDao.save(new RoleRight(KeyNumberUtil.nextId(), role, right));
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
		//不允许对系统管理员角色进行任何修改
		if(role.getId() == 1l || "系统管理员".equals(role.getName())) {
			throw new Demo1Exception("admin");
		}
		//存储角色
		roleDao.save(role);
		//删除该角色的所有roleright关系并重新记录
		roleRightDao.deleteByRoleIdIn(new Long[] {role.getId()});
		if(ids != null && ids.length != 0) {
			for(int num = 0; num < ids.length; num++) {
				roleRightDao.save(new RoleRight(KeyNumberUtil.nextId(), role, rightDao.findById(ids[num]).get()));
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
