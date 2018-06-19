package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.common.UserSessionInfo;
import com.example.demo.common.config.ModuleNameEnum;
import com.example.demo.entity.User;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.service.RightService;

/**
 * controller基础类，可用于存放一些复用性较高代码
 * @author liuqitian	
 * @date 2018年6月13日 
 *
 */
public class BaseController {
	
	@Autowired
	private RightService rightService;
	
	/**
	 * 检查后台管理系统是否有登录用户
	 * @return	boolean
	 */
	public boolean checkIfHasBackgroundUser() {
		boolean ifHasLogin = false;
		User user = UserSessionInfo.getBackgroundUserInfo();
		if(user != null) {
			ifHasLogin = true;
		}
		return ifHasLogin;
	}
	
	/**
	 * 检查当前用户是否有此模块权限
	 * @param moduleNameEnum
	 * @return
	 * @throws Demo1Exception
	 */
	public boolean checkIfHasRight(ModuleNameEnum moduleNameEnum) throws Demo1Exception {
		return rightService.ifHasRight(UserSessionInfo.getBackgroundUserInfo().getRoleId(), moduleNameEnum);
	}

}
