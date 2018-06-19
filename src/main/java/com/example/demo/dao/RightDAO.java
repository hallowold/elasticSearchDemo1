package com.example.demo.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Right;

/**
 * 权限实体的dao
 * @author liuqitian
 * @date 2018年6月11日
 *
 */
@Component
public interface RightDAO extends ElasticsearchRepository<Right, Long> {
	
	/**
	 * 通过模块名查找符合要求的权限列表
	 * @param 	moduleName	模块名
	 * @return	List<Right>	
	 */
	List<Right> findByModuleName(String moduleName);
	 
	/**
	 * 通过id数组批量删除数据
	 * @param 	ids	id数组
	 * @return	Long	删除信息条数
	 */
	Long deleteByIdIn(Long[] ids);
	 
}
