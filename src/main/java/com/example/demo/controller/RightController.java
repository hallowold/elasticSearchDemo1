package com.example.demo.controller;

import com.example.demo.common.UserSessionInfo;
import com.example.demo.common.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.config.ModuleNameEnum;
import com.example.demo.common.util.ResponseUtil;
import com.example.demo.common.util.createRequestEntityUtil.RightRequestUtil;
import com.example.demo.entity.Right;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.request.right.RightCreateRequest;
import com.example.demo.request.right.RightUpdateRequest;
import com.example.demo.response.ResponseData;
import com.example.demo.service.RightService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 权限控制器
 * @author liuqitian
 * @date 2018年6月11日
 */
@Api(value="/testdemo1", tags="权限接口模块")
@RestController
@RequestMapping("/right")
public class RightController extends BaseController{

    private static final Log LOGGER = LogFactory.getLog(RightController.class);
	
	/*
	 * 注入权限服务类
	 */
	@Autowired
	RightService rightService;

    /**
     * 新增接口
     * @param right
     * @return
     */
    @ApiOperation(value="添加权限信息", notes = "添加权限信息")
    @RequestMapping(value = "/right",method = RequestMethod.POST)  
    public ResponseData addRight(@RequestBody RightCreateRequest right) throws Exception{
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	if(!this.checkIfHasRight(ModuleNameEnum.RIGHT)) {
    		return ResponseUtil.createResponseDataHasNoRight();
    	}
    	rightService.addRight(RightRequestUtil.createRightByCreateRequest(right));
        LOGGER.info("执行新增权限信息操作，操作用户为[" + UserSessionInfo.getBackgroundUserInfo().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
        return ResponseUtil.createResponseData(true, "新增成功", null, 200);
    }
    
    /**
     * 删除接口
     * @param user
     * @return
     */
    @ApiOperation(value="批量删除权限信息", notes = "批量删除权限信息")
    @RequestMapping(value = "/rights/ids",method = RequestMethod.DELETE)  
    public ResponseData deleteRight(@RequestBody Long[] ids) throws Exception{
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	if(!this.checkIfHasRight(ModuleNameEnum.RIGHT)) {
    		return ResponseUtil.createResponseDataHasNoRight();
    	}
    	rightService.deleteRight(ids);
        LOGGER.info("执行删除权限信息操作，操作用户为[" + UserSessionInfo.getBackgroundUserInfo().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "删除成功", null, 200);
    }
    
    /**
     * 修改接口
     * @param user
     * @return
     */
    @ApiOperation(value="修改权限信息", notes = "修改权限信息")
    @RequestMapping(value = "/right",method = RequestMethod.PUT)  
    public ResponseData updateRight(@RequestBody RightUpdateRequest right) throws Exception{
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	if(!this.checkIfHasRight(ModuleNameEnum.RIGHT)) {
    		return ResponseUtil.createResponseDataHasNoRight();
    	}
    	rightService.updateRight(RightRequestUtil.createRightByUpdateRequest(right));
        LOGGER.info("执行修改权限信息操作，操作用户为[" + UserSessionInfo.getBackgroundUserInfo().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "修改成功", null, 200);
    }
    
    /**
     * 获取所有权限接口
     * @return
     */
    @ApiOperation(value="获取所有权限信息", notes = "获取所有权限信息")
    @RequestMapping(value = "/rights",method = RequestMethod.GET)  
    public ResponseData searchAllUser() {
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	Iterable<Right> results = rightService.findAllRight();
        LOGGER.info("执行获取所有权限信息操作，操作用户为[" + UserSessionInfo.getBackgroundUserInfo().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "查询", results, 200);
    }
    
    /**
     * 通过名称获取近似权限信息列表
     * @param 	name	权限名称
     * @return
     * @throws 	Exception
     */
    @ApiOperation(value="通过名称获取近似权限信息列表", notes = "通过名称获取近似权限信息列表")
    @RequestMapping(value = "/rights/name/{name}",method = RequestMethod.GET)  
    public ResponseData searchRightsByName(@ApiParam(value = "需要查询的权限名", required = true, defaultValue = "1") 
		@PathVariable("name") String name) throws Exception { 
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	Iterable<Right> results = rightService.fuzzyFindByName(name);
        LOGGER.info("执行查询权限信息操作，操作用户为[" + UserSessionInfo.getBackgroundUserInfo().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "查询", results, 200);
    }
    
    /**
     * 通过id获取权限接口
     * @return
     * @throws Demo1Exception 
     */
    @ApiOperation(value="通过id获取权限信息", notes = "通过id获取权限信息")
    @RequestMapping(value = "/right/id/{id}",method = RequestMethod.GET)  
    public ResponseData searchRightById(@ApiParam(value = "需要查询的权限id", required = true, defaultValue = "1") 
		@PathVariable("id") Long id) throws Exception {
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	Right result = rightService.findById(id);
        LOGGER.info("执行通过id获取权限信息操作，操作用户为[" + UserSessionInfo.getBackgroundUserInfo().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "查询", result, 200);
    }
    
}