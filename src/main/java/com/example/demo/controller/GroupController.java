package com.example.demo.controller;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.DateUtil;
import com.example.demo.common.util.ResponseUtil;
import com.example.demo.common.util.requestcreater.GroupRequestUtil;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.request.IntegerRequest;
import com.example.demo.request.group.SysGroupCreateRequest;
import com.example.demo.request.group.SysGroupUpdateRequest;
import com.example.demo.response.ResponseData;
import com.example.demo.security.entity.SysGroup;
import com.example.demo.security.entity.SysRole;
import com.example.demo.service.SysGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构控制器
 * @author liuqitian
 * @date 2018年7月16日
 */
@Api(value="/testdemo1", tags="机构接口模块")
@RestController
@RequestMapping("/group")
public class GroupController {

    private static final Log LOGGER = LogFactory.getLog(UserController.class);

    @Autowired
    SysGroupService sysGroupService;

    /**
     * 新增接口
     * @param 	request
     */
    @ApiOperation(value="添加文章信息", notes = "添加文章信息")
    @RequestMapping(value = "/group",method = RequestMethod.POST)
    public ResponseData addArticle(@RequestBody SysGroupCreateRequest request) throws Exception {
        sysGroupService.addGroup(GroupRequestUtil.createSysGroupByCreateRequest(request));
        LOGGER.info("执行新增机构信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
        return ResponseUtil.createResponseData(true, "新增成功", null, 200);
    }

    /**
     * 删除接口
     * @param request ids的封装实体
     */
    @ApiOperation(value="批量删除机构信息", notes = "批量删除机构信息")
    @RequestMapping(value = "/groups",method = RequestMethod.DELETE)
    public ResponseData deleteRight(@RequestBody IntegerRequest request) throws Exception{
        Integer num = sysGroupService.deleteGroups(request.getIds());
        LOGGER.info("执行删除机构信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
        return ResponseUtil.createResponseData(true, "删除成功", num, 200);
    }

    /**
     * 修改接口
     * @param request
     * @return
     */
    @ApiOperation(value="修改机构信息", notes = "修改机构信息")
    @RequestMapping(value = "/group",method = RequestMethod.PUT)
    public ResponseData updateRight(@RequestBody SysGroupUpdateRequest request) throws Exception{
        sysGroupService.updateGroup(GroupRequestUtil.createSysGroupByUpdateRequest(request));
        LOGGER.info("执行修改机构信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
        return ResponseUtil.createResponseData(true, "修改成功", null, 200);
    }

    /**
     * 根据机构id查询其默认角色接口
     */
    @ApiOperation(value="根据机构id查询其默认角色", notes = "根据机构id查询其默认角色")
    @RequestMapping(value = "/group/id/roles",method = RequestMethod.GET)
    public ResponseData searchRolesByGroupId(@RequestBody IntegerRequest request) throws Demo1Exception{

        List<SysRole> results = sysGroupService.findRolesByGroupId(request.getId());
        LOGGER.info("执行查询机构默认角色信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
        return ResponseUtil.createResponseData(true, StaticValues.SEARCH, results, 200);
    }

    /**
     * 根据名称模糊查询接口
     */
    @ApiOperation(value="根据名称模糊查询", notes = "根据名称模糊查询")
    @RequestMapping(value = "/groups/name/{name}",method = RequestMethod.GET)
    public ResponseData searchGroupByNameFuzzy(@ApiParam(value = "需要查询的名称", required = true, defaultValue = "1")
                                               @PathVariable("name") String name) throws Demo1Exception{

        List<SysGroup> results = sysGroupService.fuzzyFindByName(name);
        LOGGER.info("执行查询机构信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
        return ResponseUtil.createResponseData(true, StaticValues.SEARCH, results, 200);
    }

    /**
     * 获取所有机构接口
     * @return
     */
    @ApiOperation(value="获取所有机构信息", notes = "获取所有机构信息")
    @RequestMapping(value = "/groups",method = RequestMethod.GET)
    public ResponseData searchAllGroups() {
        Iterable<SysGroup> results = sysGroupService.findAllGroups();
        LOGGER.info("执行获取所有机构信息操作，操作用户为[" + SecurityContextHolder.getContext().getAuthentication().getName()
                + "],系统时间为[" + DateUtil.getCurrentDateStr() + "]");
        return ResponseUtil.createResponseData(true, StaticValues.SEARCH, results, 200);
    }

}

