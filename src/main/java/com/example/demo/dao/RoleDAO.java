package com.example.demo.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Role;

/**
 * 角色实体的dao
 * @author liuqitian
 * @date 2018年6月11日
 *
 */
@Component
public interface RoleDAO extends ElasticsearchRepository<Role, Long> {
	
	/**
	 * 通过名称查找符合要求的角色列表
	 * @param 	name	名称
	 * @return	List<Role>
	 */
	List<Role> findByName(String name);
	 
	/**
	 * 通过id数组批量删除数据
	 * @param 	ids	id数组
	 * @return	Long	删除信息条数
	 */
	Long deleteByIdIn(Long[] ids);
	 
}
