package com.example.demo.service.impl;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.StringUtil;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.security.dao.SysRightDao;
import com.example.demo.security.dao.SysRoleRightDao;
import com.example.demo.security.entity.SysRight;
import com.example.demo.security.entity.SysRoleRight;
import com.example.demo.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限服务实现类
 * @author liuqitian
 * @version V1.1 因使用spring security同一管理权限，代码重构
 * @date 2018年6月11日
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class RightServiceImpl implements RightService {

	@Autowired
	private SysRightDao rightDao;
	
	@Autowired
	private SysRoleRightDao roleRightDao;

	/**
	 * 新增权限
	 * @param 	right	权限实体
	 * @throws Exception 任何执行时的异常
	 */
	@Override
	public void addRight(SysRight right) throws Exception{
		rightDao.save(right);
	}

	/**
	 * 修改权限
	 * @param 	right	权限实体
	 * @throws Exception 任何执行时的异常，特殊的，若权限仍然被其他角色使用，抛出Demo1Exception("依赖")
	 */
	@Override
	public void updateRight(SysRight right) throws Exception{
		//若该权限被任何角色使用，则不允许进行修改或删除操作
		if(this.ifHasRelationWithRole(right.getId())) {
			throw new Demo1Exception("依赖");
		}
		rightDao.save(right);
	}

	/**
	 * 删除权限
	 * @param 	ids		权限id数组
	 * @throws Exception 任何执行时的异常，特殊的，若权限仍然被其他角色使用，抛出Demo1Exception("依赖")
	 */
	@Override
	public Integer deleteRight(Integer[] ids) throws Exception{
		for(int num = 0; num < ids.length; num++) {
			//若该权限被任何角色使用，则不允许进行修改或删除操作
			if(this.ifHasRelationWithRole(ids[num])) {
				throw new Demo1Exception(StaticValues.DEPENDENCE);
			}
		}
		Integer result = rightDao.deleteByIdIn(ids);
		if(result == 0) {
			throw new Demo1Exception(StaticValues.NODATA);
		}
		return result;
	}

	/**
	 * 通过名称模糊查询
	 *
	 * @param 	name 		权限名
	 * @throws Demo1Exception 查出空集合时丢出自定义异常
	 * @return 	List<SysRight> 	权限实体列表
	 */
	@Override
	public List<SysRight> fuzzyFindByName(String name) throws Demo1Exception{
		List<SysRight> list = rightDao.findByNameLike("%" + StringUtil.changeSpecialCharacter(name) + "%");
		if(list == null || list.size() < 1) {
			throw new Demo1Exception(StaticValues.SEARCH);
		}
		return list;
	}

	/**
	 * 通过id查找权限
	 * @param 	id 		id
	 * @throws Demo1Exception    查询时丢出的异常，预计为无数据或链接中断
	 * @return 	SysRight	权限实体
	 */
	@Override
	public SysRight findById(Integer id) throws Demo1Exception{
		SysRight right;
		try {
			right = rightDao.findById(id).get();
		} catch (Exception ex) {
			throw new Demo1Exception(StaticValues.SEARCH);
		}
		return right;
	}

	/**
	 * 获取所有权限
	 * @return	rights		权限集合
	 */
	@Override
	public Iterable<SysRight> findAllRight() {
		return rightDao.findAll();
	}

	/**
	 * 判断给定的权限是否与任意角色之间有关联联系
	 * @param 	id	权限id
	 * @return	boolean
	 */
	public boolean ifHasRelationWithRole(Integer id) {
		boolean ifHas = false;
		List<SysRoleRight> list = roleRightDao.findByRightId(id);
		if(list != null && list.size() > 0) {
			ifHas = true;
		}
		return ifHas;
	}
	
}
