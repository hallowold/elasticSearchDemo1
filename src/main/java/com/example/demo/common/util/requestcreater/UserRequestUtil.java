package com.example.demo.common.util.requestcreater;

import com.example.demo.request.user.UserCreateRequest;
import com.example.demo.request.user.UserUpdateRequest;
import com.example.demo.security.entity.SysUser;

import java.util.*;

/**
 * 本类用于将user相关的request对象转化实体
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class UserRequestUtil {
	
	/**
	 * "entity"对应用户实体，"roleIds"对应角色id数组
	 */
	public static Map<String, Object> createUserByCreateRequest(UserCreateRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		SysUser info = new SysUser();
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
	 * "entity"对应用户实体，"roleIds"对应角色id数组
	 */
	public static Map<String, Object> createUserByUpdateRequest(UserUpdateRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		SysUser info = new SysUser();
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
