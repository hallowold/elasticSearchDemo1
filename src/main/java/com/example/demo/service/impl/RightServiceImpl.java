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

	@Override
	public void addRight(SysRight right) throws Exception{
		rightDao.save(right);
	}

	/**
	 * Demo1Exception异常表示权限仍然被其他角色使用
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
	 * Demo1Exception异常表示权限仍然被其他角色使用或指定的数据不存在
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

	public boolean ifHasRelationWithRole(Integer id) {
		boolean ifHas = false;
		List<SysRoleRight> list = roleRightDao.findByRightId(id);
		if(list != null && list.size() > 0) {
			ifHas = true;
		}
		return ifHas;
	}

	@Override
	public List<SysRight> fuzzyFindByName(String name) throws Demo1Exception{
		List<SysRight> list = rightDao.findByNameLike("%" + StringUtil.changeSpecialCharacter(name) + "%");
		if(list == null || list.size() < 1) {
			throw new Demo1Exception(StaticValues.SEARCH);
		}
		return list;
	}

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

	@Override
	public Iterable<SysRight> findAllRight() {
		return rightDao.findAll();
	}

}
