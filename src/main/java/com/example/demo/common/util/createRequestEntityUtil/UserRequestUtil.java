package com.example.demo.common.util.createRequestEntityUtil;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.request.user.UserCreateRequest;
import com.example.demo.request.user.UserUpdateRequest;

/**
 * 本类用于将user相关的request对象转化实体
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class UserRequestUtil {
	
	/**
	 * 将UserCreateRequest转化为User
	 * @param 	UserCreateRequest
	 * @return	User
	 */
	public static User createUserByCreateRequest(UserCreateRequest request) {
		User info = new User();
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
			if(request.getUserName() != null) {
				info.setUserName(request.getUserName());
			}
			if(request.getMobile() != null) {
				info.setMobile(request.getMobile());
			}
			if(request.getGender() != null) {
				info.setGender(request.getGender());
			}
			if(request.getRole() != null) {
				info.setRole(request.getRole());
			}
			if(request.getCreateDate() != null) {
				info.setCreateDate(request.getCreateDate());
			}
		}
		
		return info;
	}
	
	/**
	 * 将UserCreateRequest集合转化为User集合
	 * @param 	List<UserCreateRequest>
	 * @return	List<User>
	 */
	public static List<User> createUserByCreateRequests(List<UserCreateRequest> requests) {
		List<User> resultList = new ArrayList<User>();
		for(UserCreateRequest request : requests) {
			if(createUserByCreateRequest(request) != null) {
				resultList.add(createUserByCreateRequest(request));
			}
		}
		return resultList;
	}
	
	/**
	 * 将UserUpdateRequest转化为User
	 * @param 	UserUpdateRequest
	 * @return	User
	 */
	public static User createUserByUpdateRequest(UserUpdateRequest request) {
		User info = new User();
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
			if(request.getUserName() != null) {
				info.setUserName(request.getUserName());
			}
			if(request.getMobile() != null) {
				info.setMobile(request.getMobile());
			}
			if(request.getGender() != null) {
				info.setGender(request.getGender());
			}
			if(request.getRole() != null) {
				info.setRole(request.getRole());
			}
			if(request.getCreateDate() != null) {
				info.setCreateDate(request.getCreateDate());
			}
			if(request.getLastChangeDate() != null) {
				info.setLastChangeDate(request.getLastChangeDate());
			}
		}
		
		return info;
	}
	
	/**
	 * 将UserUpdateRequest集合转化为User集合
	 * @param 	List<UserUpdateRequest>
	 * @return	List<User>
	 */
	public static List<User> createUserByUpdateRequests(List<UserUpdateRequest> requests) {
		List<User> resultList = new ArrayList<User>();
		for(UserUpdateRequest request : requests) {
			if(createUserByUpdateRequest(request) != null) {
				resultList.add(createUserByUpdateRequest(request));
			}
		}
		return resultList;
	}

}
