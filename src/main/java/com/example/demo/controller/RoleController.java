package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.config.ModuleNameEnum;
import com.example.demo.common.util.ResponseUtil;
import com.example.demo.common.util.createRequestEntityUtil.RoleRequestUtil;
import com.example.demo.entity.Role;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.request.role.RoleCreateRequest;
import com.example.demo.request.role.RoleUpdateRequest;
import com.example.demo.response.ResponseData;
import com.example.demo.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 角色控制器
 * @author liuqitian
 * @date 2018年6月11日
 */
@Api(value="/testdemo1", tags="角色接口模块")
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController{
	
	/*
	 * 注入角色服务类
	 */
	@Autowired
	RoleService roleService;

    /**
     * 新增接口
     * @param role
     * @return
     */
    @ApiOperation(value="添加角色信息", notes = "添加角色信息")
    @RequestMapping(value = "/role",method = RequestMethod.POST)  
    public ResponseData addRole(@RequestBody RoleCreateRequest role) throws Exception{
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	if(!this.checkIfHasRight(ModuleNameEnum.ROLE)) {
    		return ResponseUtil.createResponseDataHasNoRight();
    	}
    	roleService.addRole(RoleRequestUtil.createRoleByCreateRequest(role));
        return ResponseUtil.createResponseData(true, "新增成功", null, 200);
    }
    
    /**
     * 删除接口
     * @param user
     * @return
     */
    @ApiOperation(value="批量删除角色信息", notes = "批量删除角色信息")
    @RequestMapping(value = "/roles/ids",method = RequestMethod.DELETE)  
    public ResponseData deleteRole(@RequestBody Long[] ids) throws Exception{
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	if(!this.checkIfHasRight(ModuleNameEnum.ROLE)) {
    		return ResponseUtil.createResponseDataHasNoRight();
    	}
    	roleService.deleteRole(ids);
    	return ResponseUtil.createResponseData(true, "删除成功", null, 200);
    }
    
    /**
     * 修改接口
     * @param user
     * @return
     */
    @ApiOperation(value="修改角色信息", notes = "修改角色信息")
    @RequestMapping(value = "/role",method = RequestMethod.PUT)  
    public ResponseData updateRole(@RequestBody RoleUpdateRequest role) throws Exception{
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	if(!this.checkIfHasRight(ModuleNameEnum.ROLE)) {
    		return ResponseUtil.createResponseDataHasNoRight();
    	}
    	roleService.updateRole(RoleRequestUtil.createRoleByUpdateRequest(role));
    	return ResponseUtil.createResponseData(true, "修改成功", null, 200);
    }
    
    /**
     * 获取所有角色接口
     * @return
     */
    @ApiOperation(value="获取所有角色信息", notes = "获取所有角色信息")
    @RequestMapping(value = "/roles",method = RequestMethod.GET)  
    public ResponseData searchAllUser() {
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	Iterable<Role> results = roleService.findAllRole();
    	return ResponseUtil.createResponseData(true, "查询", results, 200);
    }
    
    /**
     * 通过名称获取近似角色信息列表
     * @param 	name	角色名称
     * @return
     * @throws 	Exception
     */
    @ApiOperation(value="通过名称获取近似角色信息列表", notes = "通过名称获取近似角色信息列表")
    @RequestMapping(value = "/roles/name/{name}",method = RequestMethod.GET)  
    public ResponseData searchRolesByName(@ApiParam(value = "需要查询的角色名", required = true, defaultValue = "1") 
		@PathVariable("name") String name) throws Exception { 
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	Iterable<Role> results = roleService.fuzzyFindByName(name);
    	return ResponseUtil.createResponseData(true, "查询", results, 200);
    }
    
    /**
     * 通过id获取角色接口
     * @return
     * @throws Demo1Exception 
     */
    @ApiOperation(value="通过id获取角色信息", notes = "通过id获取角色信息")
    @RequestMapping(value = "/role/id/{id}",method = RequestMethod.GET)  
    public ResponseData searchRoleById(@ApiParam(value = "需要查询的角色id", required = true, defaultValue = "1") 
		@PathVariable("id") Long id) throws Exception {
    	if(!this.checkIfHasBackgroundUser()) {
    		return ResponseUtil.createResponseDataNeedLogIn();
    	}
    	Role result = roleService.findById(id);
    	return ResponseUtil.createResponseData(true, "查询", result, 200);
    }
    
}