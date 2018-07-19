package com.example.demo.common.util.requestcreater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.request.role.RoleCreateRequest;
import com.example.demo.request.role.RoleUpdateRequest;
import com.example.demo.security.entity.SysRole;

/**
 * 本类用于将role相关的request对象转化实体
 * @author liuqitian
 * @version V1.1 因引入spring security统一处理权限，代码重构
 * @date 2018年7月5日
 *
 */
public class RoleRequestUtil {
	
	/**
 	 *  "entity"对应角色实体，"rightIds"对应权限id数组
	 */
	public static Map<String, Object> createRoleByCreateRequest(RoleCreateRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SysRole info = new SysRole();
		if(request == null) {
			return null;
		} else {
			if(request.getName() != null) {
				info.setName(request.getName());
			}
			resultMap.put("entity", info);
			resultMap.put("rightIds", request.getRightIds());
		}
		return resultMap;
	}
	
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
	 * "entity"对应角色实体，"rightIds"对应权限id数组
	 */
	public static Map<String, Object> createRoleByUpdateRequest(RoleUpdateRequest request) {
		SysRole info = new SysRole();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(request == null) {
			return null;
		} else {
			if(request.getId() != null) {
				info.setId(request.getId());
			}
			if(request.getName() != null) {
				info.setName(request.getName());
			}
			resultMap.put("entity", info);
			resultMap.put("rightIds", request.getRightIds());
		}

		return resultMap;
	}
	
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
