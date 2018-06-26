package com.example.demo.common.util.createRequestEntityUtil;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Right;
import com.example.demo.request.right.RightCreateRequest;
import com.example.demo.request.right.RightUpdateRequest;

/**
 * 本类用于将right相关的request对象转化实体
 * @author liuqitian	
 * @date 2018年6月14日 
 *
 */
public class RightRequestUtil {
	
	/**
	 * 将RightCreateRequest转化为Right
	 * @param 	RightCreateRequest
	 * @return	Right
	 */
	public static Right createRightByCreateRequest(RightCreateRequest request) {
		Right info = new Right();
		/*
		 * 若request为空则直接返回null，否则将属性值一一对应转化
		 */
		if(request == null) {
			return null;
		} else {
			if(request.getModuleName() != null) {
				info.setModuleName(request.getModuleName());
			}
		}
		return info;
	}
	
	/**
	 * 将RightCreateRequest集合转化为Right集合
	 * @param 	List<RightCreateRequest>
	 * @return	List<Right>
	 */
	public static List<Right> createRightByCreateRequests(List<RightCreateRequest> requests) {
		List<Right> resultList = new ArrayList<Right>();
		for(RightCreateRequest request : requests) {
			if(createRightByCreateRequest(request) != null) {
				resultList.add(createRightByCreateRequest(request));
			}
		}
		return resultList;
	}
	
	/**
	 * 将RightUpdateRequest转化为Right
	 * @param 	RightUpdateRequest
	 * @return	Right
	 */
	public static Right createRightByUpdateRequest(RightUpdateRequest request) {
		Right info = new Right();
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
			if(request.getModuleName() != null) {
				info.setModuleName(request.getModuleName());
			}
		}
		return info;
	}
	
	/**
	 * 将RightUpdateRequest集合转化为Right集合
	 * @param 	List<RightUpdateRequest>
	 * @return	List<Right>
	 */
	public static List<Right> createRightByUpdateRequests(List<RightUpdateRequest> requests) {
		List<Right> resultList = new ArrayList<Right>();
		for(RightUpdateRequest request : requests) {
			if(createRightByUpdateRequest(request) != null) {
				resultList.add(createRightByUpdateRequest(request));
			}
		}
		return resultList;
	}

}
