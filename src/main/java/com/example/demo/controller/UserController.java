package com.example.demo.controller;


import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.DateUtil;
import com.example.demo.common.util.ResponseUtil;
import com.example.demo.common.util.requestcreater.UserRequestUtil;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.request.IntegerRequest;
import com.example.demo.request.user.UserCreateRequest;
import com.example.demo.request.user.UserUpdateRequest;
import com.example.demo.response.ResponseData;
import com.example.demo.security.config.LoginSuccessHandler;
import com.example.demo.security.entity.SysUser;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 用户控制器
 * @author liuqitian
 * @date 2018年6月11日
 */
@Api(value="/testdemo1", tags="用户接口模块")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    private static final Log LOGGER = LogFactory.getLog(UserController.class);
	
	@Autowired
	UserService userService;

    @ApiOperation(value="添加用户信息", notes = "添加用户信息")
    @RequestMapping(value = "/user",method = RequestMethod.POST)  
    public ResponseData addUser(@RequestBody @Valid UserCreateRequest user, BindingResult bindResult) throws Demo1Exception {
        boolean ifSuccess = userService.addUser(UserRequestUtil.createUserByCreateRequest(user));
        LOGGER.info("执行新增用户信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
        ResponseData res = ResponseUtil.createResponseDataCheckIfExsit(ifSuccess, "登录名");
        return res;
    }

    /**
     * json格式传参，ids: Integer[]
     */
    @ApiOperation(value="批量删除用户信息", notes = "批量删除用户信息")
    @RequestMapping(value = "/users",method = RequestMethod.DELETE)
    public ResponseData deleteUser(@RequestBody IntegerRequest request) throws Demo1Exception{
    	Integer num = userService.deleteUser(request.getIds());
        LOGGER.info("执行删除用户信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "删除成功", num, 200);
    }
    
    @ApiOperation(value="修改用户信息", notes = "修改用户信息")
    @RequestMapping(value = "/user",method = RequestMethod.PUT)  
    public ResponseData updateUser(@RequestBody @Valid UserUpdateRequest user, BindingResult bindResult){
    	userService.updateUser(UserRequestUtil.createUserByUpdateRequest(user));
        LOGGER.info("执行修改用户信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "修改成功", null, 200);
    }
    
    @ApiOperation(value="获取所有用户信息", notes = "获取所有用户信息")
    @RequestMapping(value = "/users",method = RequestMethod.GET)  
    public ResponseData searchAllUser() {
    	Iterable<SysUser> results = userService.findAllUser();
        LOGGER.info("执行获取所有用户信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, StaticValues.SEARCH, results, 200);
    }
    
    @ApiOperation(value="通过登录名模糊查询", notes = "通过登录名模糊查询")
    @RequestMapping(value = "/users/loginName/{loginName}",method = RequestMethod.GET)  
    public ResponseData searchUsersByLoginNameFuzzy(@ApiParam(value = "需要查询的用户登录名", required = true, defaultValue = "1") 
		@PathVariable("loginName") String loginName) throws Demo1Exception {

    	List<SysUser> result = userService.fuzzyFindByLoginName(loginName);
        LOGGER.info("执行查询用户信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, StaticValues.SEARCH, result, 200);
    }
    
}