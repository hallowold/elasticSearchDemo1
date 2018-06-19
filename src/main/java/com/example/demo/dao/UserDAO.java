package com.example.demo.dao;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;

/**
 * 用户实体的dao
 * @author liuqitian
 * @date 2018年6月11日
 *
 */
@Component
public interface UserDAO extends ElasticsearchRepository<User, Long> {
	
	/**
	 * 通过登录名寻找用户
	 * @param 	loginName	登录名
	 * @return
	 */
	List<User> findByLoginName(String loginName);
	 
	/**
	 * 通过登录名删除用户
	 * @param 	loginName
	 * @return
	 */
	Long deleteByLoginName(String loginName);
	
	/**
	 * 通过id数组批量删除数据
	 * @param 	ids		id数组
	 * @return	Long	删除信息条数
	 */
	Long deleteByRoleIdIn(Long[] ids);
	
	@Query("{\"query\" : {\"match_all\" : {}},\"aggs\" : {\"MAX(id)\" : {\"max\" : {\"field\" : \"id\"}}}}")
	Long findMaxId();
	
}
