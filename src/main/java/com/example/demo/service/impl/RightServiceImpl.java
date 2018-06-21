package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.config.ModuleNameEnum;
import com.example.demo.dao.RightDAO;
import com.example.demo.dao.RoleRightDAO;
import com.example.demo.entity.Right;
import com.example.demo.entity.RoleRight;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.service.RightService;

/**
 * 权限服务实现类
 * 
 * @author liuqitian
 * @date 2018年6月11日
 *
 */
@Service
public class RightServiceImpl implements RightService {

	@Autowired
	private RightDAO rightDao;
	
	@Autowired
	private RoleRightDAO roleRightDao;

	/**
	 * 新增权限
	 * 
	 * @param 	right 	权限实体
	 * @return 	boolean
	 */
	@Transactional
	public void addRight(Right right) throws Exception{
		rightDao.save(right);
	}

	/**
	 * 修改权限
	 * 
	 * @param 	right 	权限实体
	 * @return 	boolean
	 */
	@Transactional
	public void updateRight(Right right) throws Exception{
		//若该权限被任何角色使用，则不允许进行修改或删除操作
		if(this.ifHasRelationWithRole(right.getId())) {
			throw new Demo1Exception("依赖");
		}
		rightDao.save(right);
	}

	/**
	 * 删除权限
	 * @param 	ids		权限id数组
	 * @return	boolean
	 */
	@Transactional
	public void deleteRight(Long[] ids) throws Exception{
		for(int num = 0; num < ids.length; num++) {
			//若该权限被任何角色使用，则不允许进行修改或删除操作
			if(this.ifHasRelationWithRole(ids[num])) {
				throw new Demo1Exception("依赖");
			}
		}
		rightDao.deleteByIdIn(ids);
	}

	/**
	 * 通过权限名查找单一权限
	 * 
	 * @param 	loginName 	权限名
	 * @return 	Right 		权限实体
	 */
	@Transactional
	public Right findByName(String rightName){
		Right tempRight = new Right();
		List<Right> list = rightDao.findByModuleName(rightName);
		if(list == null || list.size() < 1) {
			return null;
		}
		tempRight = list.get(0);
		return tempRight;
	}
	
	/**
	 * 通过权限名查找近似权限列表
	 * 
	 * @param 	rightName 		权限名
	 * @return 	List<Right> 	权限实体列表
	 */
	@Transactional
	public List<Right> fuzzyFindByName(String rightName) throws Demo1Exception{
		List<Right> list = rightDao.findByModuleName("*" + rightName + "*");
		if(list == null || list.size() < 1) {
			throw new Demo1Exception("查询");
		}
		return list;
	}
	
	/**
	 * 通过权限名查找近似权限列表
	 * 
	 * @param 	rightName 		权限名
	 * @return 	List<Right> 	权限实体列表
	 */
	@Transactional
	public Right findById(Long id) throws Demo1Exception{
		Right right = new Right();
		try {
			right = rightDao.findById(id).get();
		} catch (Exception ex) {
			throw new Demo1Exception("查询");
		}
		return right;
	}
	
	/**
	 * 获取所有权限
	 * @return	rights		权限集合
	 */
	@Transactional
	public Iterable<Right> findAllRight() {
		return rightDao.findAll();
	}
	
	/**
	 * 判断给定的权限是否与任意角色之间有关联联系
	 * @param 	id	权限id
	 * @return	boolean
	 */
	@Transactional
	public boolean ifHasRelationWithRole(Long id) {
		boolean ifHas = false;
		List<RoleRight> list = roleRightDao.findByRightId(id);
		if(list != null && list.size() > 0) {
			ifHas = true;
		}
		return ifHas;
	}
	
	/**
	 * 判断当前后台用户是否有某功能模块的权限
	 * @param 	roleId			角色id，从当前用户获取
	 * @param 	ModuleNameEnum	模块识别Enum，从config中的ModuleNameEnum选取
	 * @return
	 * @throws 	Demo1Exception
	 */
	@Transactional
	public boolean ifHasRight(Long roleId, ModuleNameEnum moduleNameEnum) throws Demo1Exception{
		boolean ifHasRight = false;
		//系统管理员用户拥有所有权限，无需判断
		if (roleId == 1) {
			return true;
		}
		List<RoleRight> relationList = roleRightDao.findByRoleId(roleId);
		Right temp = this.findByName(moduleNameEnum.getModuleName());
		if(temp != null && temp.getId() != null) {
			for(RoleRight rr : relationList) {
				if(rr.getRight().getId() == temp.getId()) {
					ifHasRight = true;
					break;
				}
			}
		} else {
			//此处实际应为right数据丢失问题导致，但是系统模块不能让客户操作，告知服务人员处理
			throw new Demo1Exception("逻辑错误");
		}
		return ifHasRight;
	}
	
}
