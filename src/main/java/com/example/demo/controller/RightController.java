package com.example.demo.controller;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.DateUtil;
import com.example.demo.common.util.ResponseUtil;
import com.example.demo.common.util.requestcreater.RightRequestUtil;
import com.example.demo.request.IntegerRequest;
import com.example.demo.request.right.RightCreateRequest;
import com.example.demo.request.right.RightUpdateRequest;
import com.example.demo.response.ResponseData;
import com.example.demo.security.entity.SysRight;
import com.example.demo.service.RightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * 权限控制器
 * @author liuqitian
 * @date 2018年6月11日
 */
@Api(value="/testdemo1", tags="权限接口模块")
@RestController
@RequestMapping("/right")
public class RightController{

    private static final Log LOGGER = LogFactory.getLog(RightController.class);
	
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
        System.out.println(right);
    	rightService.addRight(RightRequestUtil.createRightByCreateRequest(right));
        LOGGER.info("执行新增权限信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
        return ResponseUtil.createResponseData(true, "新增成功", null, 200);
    }
    
    /**
     * 删除接口
     * @param user
     * @return
     */
    @ApiOperation(value="批量删除权限信息", notes = "批量删除权限信息")
    @RequestMapping(value = "/rights",method = RequestMethod.DELETE)
    public ResponseData deleteRight(@RequestBody IntegerRequest request) throws Exception{
        Integer num = rightService.deleteRight(request.getIds());
        LOGGER.info("执行删除权限信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "删除成功", num, 200);
    }
    
    /**
     * 修改接口
     * @param user
     * @return
     */
    @ApiOperation(value="修改权限信息", notes = "修改权限信息")
    @RequestMapping(value = "/right",method = RequestMethod.PUT)  
    public ResponseData updateRight(@RequestBody RightUpdateRequest right) throws Exception{
    	rightService.updateRight(RightRequestUtil.createRightByUpdateRequest(right));
        LOGGER.info("执行修改权限信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
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
    	Iterable<SysRight> results = rightService.findAllRight();
        LOGGER.info("执行获取所有权限信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, StaticValues.SEARCH, results, 200);
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
    	Iterable<SysRight> results = rightService.fuzzyFindByName(name);
        LOGGER.info("执行查询权限信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, StaticValues.SEARCH, results, 200);
    }
    
}