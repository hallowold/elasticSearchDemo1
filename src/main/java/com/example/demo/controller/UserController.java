package com.example.demo.controller;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.example.demo.common.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.UserSessionInfo;
import com.example.demo.common.config.ModuleNameEnum;
import com.example.demo.common.util.ResponseUtil;
import com.example.demo.common.util.createRequestEntityUtil.UserRequestUtil;
import com.example.demo.entity.User;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.request.user.UserCreateRequest;
import com.example.demo.request.user.UserUpdateRequest;
import com.example.demo.response.ResponseData;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 用户控制器
 * @author liuqitian
 * @date 2018年6月11日
 */
@Api(value="/testdemo1", tags="用户接口模块")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final Log LOGGER = LogFactory.getLog(UserController.class);
	
	/*
	 * 注入用户服务类
	 */
	@Autowired
	UserService userService;

    /**
     * 新增接口
     * @param user
     * @return
     */
    @ApiOperation(value="添加用户信息", notes = "添加用户信息")
    @RequestMapping(value = "/user",method = RequestMethod.POST)  
    public ResponseData addUser(@RequestBody UserCreateRequest user) throws Demo1Exception {
    	ResponseData res = new ResponseData();
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	if(!this.checkIfHasRight(ModuleNameEnum.USER)) {
    		return ResponseUtil.createResponseDataHasNoRight();
    	}
    	boolean ifSuccess = false;
    	ifSuccess = userService.addUser(UserRequestUtil.createUserByCreateRequest(user));
        LOGGER.info("执行新增用户信息操作，操作用户为[" + UserSessionInfo.getBackgroundUserInfo().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	res = ResponseUtil.createResponseDataCheckIfExsit(ifSuccess, "登录名");
        return res;
    }
    
    /**
     * 删除接口
     * @param user
     * @return
     * @throws Demo1Exception 
     */
    @ApiOperation(value="批量删除用户信息", notes = "批量删除用户信息")
    @RequestMapping(value = "/users/ids",method = RequestMethod.DELETE)  
    public ResponseData deleteUser(@RequestBody Long[] ids) throws Demo1Exception{
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	if(!this.checkIfHasRight(ModuleNameEnum.USER)) {
    		return ResponseUtil.createResponseDataHasNoRight();
    	}
    	userService.deleteUser(ids);
        LOGGER.info("执行删除用户信息操作，操作用户为[" + UserSessionInfo.getBackgroundUserInfo().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "删除成功", null, 200);
    }
    
    /**
     * 修改接口
     * @param user
     * @return
     * @throws Demo1Exception 
     */
    @ApiOperation(value="修改用户信息", notes = "修改用户信息")
    @RequestMapping(value = "/user",method = RequestMethod.PUT)  
    public ResponseData updateUser(@RequestBody UserUpdateRequest user) throws Demo1Exception, UnsupportedEncodingException, NoSuchAlgorithmException {
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	if(!this.checkIfHasRight(ModuleNameEnum.USER)) {
    		return ResponseUtil.createResponseDataHasNoRight();
    	}
    	userService.updateUser(UserRequestUtil.createUserByUpdateRequest(user));
        LOGGER.info("执行修改用户信息操作，操作用户为[" + UserSessionInfo.getBackgroundUserInfo().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "修改成功", null, 200);
    }
    
    /**
     * 获取所有用户接口
     * @return
     */
    @ApiOperation(value="获取所有用户信息", notes = "获取所有用户信息")
    @RequestMapping(value = "/users",method = RequestMethod.GET)  
    public ResponseData searchAllUser() {
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	Iterable<User> results = userService.findAllUser();
    	UserSessionInfo.getBackgroundUserInfo();
        LOGGER.info("执行获取所有用户信息操作，操作用户为[" + UserSessionInfo.getBackgroundUserInfo().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "查询", results, 200);
    }
    
    /**
     * 通过用户id获取单一用户接口
     * @return
     * @throws Demo1Exception 
     */
    @ApiOperation(value="通过用户id获取用户信息", notes = "通过用户id获取用户信息")
    @RequestMapping(value = "/user/id/{id}",method = RequestMethod.GET)  
    public ResponseData searchUserByLoginName(@ApiParam(value = "需要查询的用户登录名", required = true, defaultValue = "1") 
		@PathVariable("id") Long id) throws Demo1Exception {
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	User result = userService.findById(id);
        LOGGER.info("执行通过id获取用户信息操作，操作用户为[" + UserSessionInfo.getBackgroundUserInfo().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "查询", result, 200);
    }
    
    /**
     * 通过用户名获取近似用户列表接口
     * @return
     * @throws Demo1Exception 
     */
    @ApiOperation(value="通过登录名获取近似用户信息列表", notes = "通过登录名获取近似用户信息列表")
    @RequestMapping(value = "/users/loginName/{loginName}",method = RequestMethod.GET)  
    public ResponseData searchUsersByLoginNameFuzzy(@ApiParam(value = "需要查询的用户登录名", required = true, defaultValue = "1") 
		@PathVariable("loginName") String loginName) throws Demo1Exception {
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	List<User> result = userService.fuzzyFindByLoginName(loginName);
        LOGGER.info("执行查询用户信息操作，操作用户为[" + UserSessionInfo.getBackgroundUserInfo().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "查询", result, 200);
    }
    
}