package com.example.demo.common.util.requestcreater;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	 * @return	SysUser
	 */
	public static SysUser createUserByCreateRequest(UserCreateRequest request) {
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
			info.setCreateTime(new Date());
		}
		
		return info;
	}
	
	/**
	 * 将UserCreateRequest集合转化为User集合
	 * @param 	requests
	 * @return	List<SysUser>
	 */
	public static List<SysUser> createUserByCreateRequests(List<UserCreateRequest> requests) {
		List<SysUser> resultList = new ArrayList<SysUser>();
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
	 * @return	SysUser
	 */
	public static SysUser createUserByUpdateRequest(UserUpdateRequest request) {
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
			if(request.getCreateTime() != null) {
				info.setCreateTime(request.getCreateTime());
			}
		}
		
		return info;
	}
	
	/**
	 * 将UserUpdateRequest集合转化为User集合
	 * @param 	requests
	 * @return	List<SysUser>
	 */
	public static List<SysUser> createUserByUpdateRequests(List<UserUpdateRequest> requests) {
		List<SysUser> resultList = new ArrayList<SysUser>();
		for(UserUpdateRequest request : requests) {
			if(createUserByUpdateRequest(request) != null) {
				resultList.add(createUserByUpdateRequest(request));
			}
		}
		return resultList;
	}

}
