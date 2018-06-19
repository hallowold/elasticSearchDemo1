package com.example.demo.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.example.demo.entity.RoleRight;

/**
 * 角色与权限关联关系的dao
 * 
 * @author liuqitian
 * @date 2018年6月11日
 *
 */
@Component
public interface RoleRightDAO extends ElasticsearchRepository<RoleRight, Long> {
	
	/**
	 * 通过角色id查找
	 * @param 	roleId
	 * @return	List<RoleRight>
	 */
	List<RoleRight> findByRoleId(Long roleId);

	/**
	 * 通过权限id查找
	 * @param 	rightId
	 * @return	List<RoleRight>
	 */
	List<RoleRight> findByRightId(Long rightId);

	/**
	 * 通过角色id批量删除
	 * @param 	Long[]	角色id数组
	 * @return	Long	删除信息条数
	 */
	Long deleteByRoleIdIn(Long[] roleIds);

	/**
	 * 通过id数组批量删除数据
	 * @param 	ids		id数组
	 * @return	Long	删除信息条数
	 */
	Long deleteByIdIn(Long[] ids);
}
