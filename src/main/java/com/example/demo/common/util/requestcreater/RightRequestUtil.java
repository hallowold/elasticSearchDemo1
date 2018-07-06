package com.example.demo.common.util.requestcreater;

import com.example.demo.request.right.RightCreateRequest;
import com.example.demo.request.right.RightUpdateRequest;
import com.example.demo.security.entity.SysRight;

import java.util.ArrayList;
import java.util.List;

/**
 * 本类用于将right相关的request对象转化实体
 * @author liuqitian
 * @version V1.1 因引入spring security统一管理权限，代码重构
 * @date 2018年6月14日 
 *
 */
public class RightRequestUtil {
	
	/**
	 * 将RightCreateRequest转化为Right
	 * @param 	request
	 * @return	SysRight
	 */
	public static SysRight createRightByCreateRequest(RightCreateRequest request) {
		SysRight info = new SysRight();
		/*
		 * 若request为空则直接返回null，否则将属性值一一对应转化
		 */
		if(request == null) {
			return null;
		} else {
			if(request.getName() != null) {
				info.setName(request.getName());
			}
			if(request.getRightUrl() != null) {
				info.setRightUrl(request.getRightUrl());
			}
			if(request.getMethodType() != null) {
				info.setMethodType(request.getMethodType());
			}
			if(request.getMethodName() != null) {
				info.setMethodName(request.getMethodName());
			}
			if(request.getMethodPath() != null) {
				info.setMethodPath(request.getMethodPath());
			}
			if(request.getRemark() != null) {
				info.setRemark(request.getRemark());
			}
		}
		return info;
	}
	
	/**
	 * 将RightCreateRequest集合转化为Right集合
	 * @param 	requests
	 * @return	List<SysRight>
	 */
	public static List<SysRight> createRightByCreateRequests(List<RightCreateRequest> requests) {
		List<SysRight> resultList = new ArrayList<>();
		for(RightCreateRequest request : requests) {
			if(createRightByCreateRequest(request) != null) {
				resultList.add(createRightByCreateRequest(request));
			}
		}
		return resultList;
	}
	
	/**
	 * 将RightUpdateRequest转化为Right
	 * @param 	request
	 * @return	SysRight
	 */
	public static SysRight createRightByUpdateRequest(RightUpdateRequest request) {
		SysRight info = new SysRight();
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
			if(request.getRightUrl() != null) {
				info.setRightUrl(request.getRightUrl());
			}
			if(request.getMethodType() != null) {
				info.setMethodType(request.getMethodType());
			}
			if(request.getMethodName() != null) {
				info.setMethodName(request.getMethodName());
			}
			if(request.getMethodPath() != null) {
				info.setMethodPath(request.getMethodPath());
			}
			if(request.getRemark() != null) {
				info.setRemark(request.getRemark());
			}
		}
		return info;
	}
	
	/**
	 * 将RightUpdateRequest集合转化为Right集合
	 * @param 	requests
	 * @return	List<SysRight>
	 */
	public static List<SysRight> createRightByUpdateRequests(List<RightUpdateRequest> requests) {
		List<SysRight> resultList = new ArrayList<>();
		for(RightUpdateRequest request : requests) {
			if(createRightByUpdateRequest(request) != null) {
				resultList.add(createRightByUpdateRequest(request));
			}
		}
		return resultList;
	}

}
