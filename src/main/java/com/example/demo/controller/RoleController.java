package com.example.demo.controller;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.DateUtil;
import com.example.demo.common.util.ResponseUtil;
import com.example.demo.common.util.requestcreater.RoleRequestUtil;
import com.example.demo.request.IntegerRequest;
import com.example.demo.request.role.RoleCreateRequest;
import com.example.demo.request.role.RoleUpdateRequest;
import com.example.demo.response.ResponseData;
import com.example.demo.security.config.LoginSuccessHandler;
import com.example.demo.security.entity.SysRole;
import com.example.demo.service.RoleService;
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

/**
 * 角色控制器
 * @author liuqitian
 * @date 2018年6月11日
 */
@Api(value="/testdemo1", tags="角色接口模块")
@RestController
@RequestMapping("/role")
@Validated
public class RoleController{

    private static final Log LOGGER = LogFactory.getLog(RoleController.class);

	@Autowired
	RoleService roleService;

    @ApiOperation(value="添加角色信息", notes = "添加角色信息")
    @RequestMapping(value = "/role",method = RequestMethod.POST)  
    public ResponseData addRole(@RequestBody @Valid RoleCreateRequest role, BindingResult bindResult) throws Exception{
    	roleService.addRole(RoleRequestUtil.createRoleByCreateRequest(role));
        LOGGER.info("执行新增角色信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
        return ResponseUtil.createResponseData(true, "新增成功", null, 200);
    }

    /**
     * json格式传参，ids: Integer[]
     */
    @ApiOperation(value="批量删除角色信息", notes = "批量删除角色信息")
    @RequestMapping(value = "/roles",method = RequestMethod.DELETE)
    public ResponseData deleteRole(@RequestBody IntegerRequest request) throws Exception{
        Integer num = roleService.deleteRole(request.getIds());
        LOGGER.info("执行删除角色信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "删除成功", num, 200);
    }
    
    @ApiOperation(value="修改角色信息", notes = "修改角色信息")
    @RequestMapping(value = "/role",method = RequestMethod.PUT)  
    public ResponseData updateRole(@RequestBody @Valid RoleUpdateRequest role, BindingResult bindResult) throws Exception{
    	roleService.updateRole(RoleRequestUtil.createRoleByUpdateRequest(role));
        LOGGER.info("执行修改角色信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, "修改成功", null, 200);
    }
    
    @ApiOperation(value="获取所有角色信息", notes = "获取所有角色信息")
    @RequestMapping(value = "/roles",method = RequestMethod.GET)  
    public ResponseData searchAllUser() {
    	Iterable<SysRole> results = roleService.findAllRole();
        LOGGER.info("执行获取所有角色信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, StaticValues.SEARCH, results, 200);
    }
    
    @ApiOperation(value="通过名称模糊查询", notes = "通过名称模糊查询")
    @RequestMapping(value = "/roles/name/{name}",method = RequestMethod.GET)  
    public ResponseData searchRolesByName(@ApiParam(value = "需要查询的角色名", required = true, defaultValue = "1") 
		@PathVariable("name") String name) throws Exception { 
    	Iterable<SysRole> results = roleService.fuzzyFindByName(name);
        LOGGER.info("执行查询角色信息操作，操作用户为[" + LoginSuccessHandler.getCurrentUser().getLoginName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
    	return ResponseUtil.createResponseData(true, StaticValues.SEARCH, results, 200);
    }
    
}