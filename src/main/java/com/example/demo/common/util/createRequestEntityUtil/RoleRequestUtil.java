package com.example.demo.common.util.createRequestEntityUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.Role;
import com.example.demo.request.role.RoleCreateRequest;
import com.example.demo.request.role.RoleUpdateRequest;

/**
 * 本类用于将role相关的request对象转化实体
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class RoleRequestUtil {
	
	/**
	 * 将RoleCreateRequest转化为Map<String, Object>
	 * 		"role"对应角色实体，"rightIds"对应权限id数组
	 * @param 	RoleCreateRequest
	 * @return	Map<String, Object>
	 */
	public static Map<String, Object> createRoleByCreateRequest(RoleCreateRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Role info = new Role();
		/*
		 * 若request为空则直接返回null，否则将属性值一一对应转化
		 */
		if(request == null) {
			return null;
		} else {
			//用户id，暂用，解决主键问题后应去除
			if(request.getId() != null) {
				info.setId(request.getId());
			}
			if(request.getName() != null) {
				info.setName(request.getName());
			}
			if(request.getCreateDate() != null) {
				info.setCreateDate(request.getCreateDate());
			}
		}
		resultMap.put("role", info);
		resultMap.put("rightIds", request.getRightIds());
		
		return resultMap;
	}
	
	/**
	 * 将RoleCreateRequest集合转化为Map<String, Object>集合
	 * @param 	List<RoleCreateRequest>
	 * @return	List<Map<String, Object>>
	 */
	public static List<Map<String, Object>> createRoleByCreateRequests(List<RoleCreateRequest> requests) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for(RoleCreateRequest request : requests) {
			if(createRoleByCreateRequest(request) != null) {
				resultList.add(createRoleByCreateRequest(request));
			}
		}
		return resultList;
	}
	
	/**
	 * 将RoleUpdateRequest转化为Map<String, Object>
	 * @param 	RoleUpdateRequest
	 * @return	Map<String, Object>
	 */
	public static Map<String, Object> createRoleByUpdateRequest(RoleUpdateRequest request) {
		Role info = new Role();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		/*
		 * 若request为空则直接返回null，否则将属性值一一对应转化
		 */
		if(request == null) {
			return null;
		} else {
			if(request.getId() != null) {
				info.setId(request.getId());
			}
			if(request.getName() != null) {
				info.setName(request.getName());
			}
			if(request.getCreateDate() != null) {
				info.setCreateDate(request.getCreateDate());
			}
			if(request.getLastChangeDate() != null) {
				info.setLastChangeDate(request.getLastChangeDate());
			}
		}
		resultMap.put("role", info);
		resultMap.put("rightIds", request.getRightIds());
		
		return resultMap;
	}
	
	/**
	 * 将RoleUpdateRequest集合转化为Map<String, Object>集合
	 * @param 	List<RoleUpdateRequest>
	 * @return	List<Map<String, Object>>
	 */
	public static List<Map<String, Object>> createRoleByUpdateRequests(List<RoleUpdateRequest> requests) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for(RoleUpdateRequest request : requests) {
			if(createRoleByUpdateRequest(request) != null) {
				resultList.add(createRoleByUpdateRequest(request));
			}
		}
		return resultList;
	}

}
