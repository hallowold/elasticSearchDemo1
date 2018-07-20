package com.example.demo.controller;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.DateUtil;
import com.example.demo.common.util.ResponseUtil;
import com.example.demo.common.util.requestcreater.RightRequestUtil;
import com.example.demo.request.IntegerRequest;
import com.example.demo.request.right.RightCreateRequest;
import com.example.demo.request.right.RightUpdateRequest;
import com.example.demo.response.ResponseData;
import com.example.demo.security.config.LoginSuccessHandler;
import com.example.demo.security.entity.SysRight;
import com.example.demo.service.RightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 权限控制器
 * @author liuqitian
 * @date 2018年6月11日
 */
@Api(value="/testdemo1", tags="权限接口模块")
@RestController
@RequestMapping("/right")
@Validated
public class RightController{

    private static final Log LOGGER = LogFactory.getLog(RightController.class);
	
	@Autowired
	RightService rightService;

    @ApiOperation(value="添加权限信息", notes = "添加权限信息")
    @RequestMapping(value = "/right",method = RequestMethod.POST)  
    public ResponseData addRight(@RequestBody @Valid RightCreateRequest right, BindingResult bindResult) throws Exception{
    	rightService.addRight(RightRequestUtil.createRightByCreateRequest(right));
        LOGGER.info("执行新增权限信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
        return ResponseUtil.createResponseData(true, "新增成功", null, 200);
    }

    /**
     * json格式传参，ids: Integer[]
     */
    @ApiOperation(value="批量删除权限信息", notes = "批量删除权限信息")
    @RequestMapping(value = "/rights",method = RequestMethod.DELETE)
    public ResponseData deleteRight(@RequestBody IntegerRequest request) throws Exception{
        Integer num = rightService.deleteRight(request.getIds());
        LOGGER.info("执行删除权限信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "删除成功", num, 200);
    }
    
    @ApiOperation(value="修改权限信息", notes = "修改权限信息")
    @RequestMapping(value = "/right",method = RequestMethod.PUT)  
    public ResponseData updateRight(@RequestBody RightUpdateRequest right) throws Exception{
    	rightService.updateRight(RightRequestUtil.createRightByUpdateRequest(right));
        LOGGER.info("执行修改权限信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "修改成功", null, 200);
    }
    
    @ApiOperation(value="获取所有权限信息", notes = "获取所有权限信息")
    @RequestMapping(value = "/rights",method = RequestMethod.GET)  
    public ResponseData searchAllUser() {
    	Iterable<SysRight> results = rightService.findAllRight();
        LOGGER.info("执行获取所有权限信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, StaticValues.SEARCH, results, 200);
    }
    
    @ApiOperation(value="通过名称模糊查询", notes = "通过名称模糊查询")
    @RequestMapping(value = "/rights/name/{name}",method = RequestMethod.GET)  
    public ResponseData searchRightsByName(@ApiParam(value = "需要查询的权限名", required = true, defaultValue = "1") 
		@PathVariable("name") String name) throws Exception { 
    	Iterable<SysRight> results = rightService.fuzzyFindByName(name);
        LOGGER.info("执行查询权限信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, StaticValues.SEARCH, results, 200);
    }
    
}