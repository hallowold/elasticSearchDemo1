package com.example.demo.common.util.requestcreater;

import java.util.*;

import com.example.demo.entity.User;
import com.example.demo.request.user.UserCreateRequest;
import com.example.demo.request.user.UserUpdateRequest;
import com.example.demo.security.entity.SysUser;

/**
 * 本类用于将user相关的request对象转化实体
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class UserRequestUtil {
	
	/**
	 * 将UserCreateRequest转化为User
	 * @param 	request
	 * @return	Map<String, Object>
	 */
	public static Map<String, Object> createUserByCreateRequest(UserCreateRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		SysUser info = new SysUser();
		/*
		 * 若request为空则直接返回null，否则将属性值一一对应转化
		 */
		if(request == null) {
			return null;
		} else {
			if(request.getLoginName() != null) {
				info.setLoginName(request.getLoginName());
			}
			if(request.getPassword() != null) {
				info.setPassword(request.getPassword());
			}
			if(request.getShowName() != null) {
				info.setShowName(request.getShowName());
			}
			if(request.getEmail() != null) {
				info.setEmail(request.getEmail());
			}
			//创建时赋值createTime
			info.setCreateTime(new Date());
			resultMap.put("entity", info);
			resultMap.put("roleIds", request.getRoleIds());
		}
		
		return resultMap;
	}
	
	/**
	 * 将UserCreateRequest集合转化为User集合
	 * @param 	requests
	 * @return	List<Map<String, Object>>
	 */
	public static List<Map<String, Object>> createUserByCreateRequests(List<UserCreateRequest> requests) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		for(UserCreateRequest request : requests) {
			if(createUserByCreateRequest(request) != null) {
				resultList.add(createUserByCreateRequest(request));
			}
		}
		return resultList;
	}
	
	/**
	 * 将UserUpdateRequest转化为User
	 * @param 	request
	 * @return	Map<String, Object>
	 */
	public static Map<String, Object> createUserByUpdateRequest(UserUpdateRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		SysUser info = new SysUser();
		/*
		 * 若request为空则直接返回null，否则将属性值一一对应转化
		 */
		if(request == null) {
			return null;
		} else {
			if(request.getId() != null) {
				info.setId(request.getId());
			}
			if(request.getLoginName() != null) {
				info.setLoginName(request.getLoginName());
			}
			if(request.getPassword() != null) {
				info.setPassword(request.getPassword());
			}
			if(request.getShowName() != null) {
				info.setShowName(request.getShowName());
			}
			if(request.getEmail() != null) {
				info.setEmail(request.getEmail());
			}
			//修改时赋值updateTime
			info.setUpdateTime(new Date());
			resultMap.put("entity", info);
			resultMap.put("roleIds", request.getRoleIds());
		}
		
		return resultMap;
	}
	
	/**
	 * 将UserUpdateRequest集合转化为User集合
	 * @param 	requests
	 * @return	List<Map<String, Object>>
	 */
	public static List<Map<String, Object>> createUserByUpdateRequests(List<UserUpdateRequest> requests) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		for(UserUpdateRequest request : requests) {
			if(createUserByUpdateRequest(request) != null) {
				resultList.add(createUserByUpdateRequest(request));
			}
		}
		return resultList;
	}

}
