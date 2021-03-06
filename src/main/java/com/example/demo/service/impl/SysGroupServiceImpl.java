package com.example.demo.service.impl;

import com.example.demo.common.config.StaticValues;
import com.example.demo.common.util.StringUtil;
import com.example.demo.exception.Demo1Exception;
import com.example.demo.security.dao.SysGroupDao;
import com.example.demo.security.dao.SysRoleDao;
import com.example.demo.security.dao.SysRoleGroupDao;
import com.example.demo.security.entity.SysGroup;
import com.example.demo.security.entity.SysRole;
import com.example.demo.security.entity.SysRoleGroup;
import com.example.demo.service.SysGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author : liuqitian
 * @date : 2018/7/16 12:16
 * @version : V1.2
 * 机构操纵类
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class SysGroupServiceImpl implements SysGroupService {

    @Autowired
    private SysGroupDao sysGroupDao;

    @Autowired
    private SysRoleGroupDao sysRoleGroupDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public void addGroup(Map<String, Object> dataMap) {
        SysGroup entity = (SysGroup) dataMap.get("entity");
        Integer[] roleIds = (Integer[]) dataMap.get("roleIds");
        System.out.println(entity);
        if(entity.getpId() != null && entity.getpId() != 0) {
            entity.setpSysGroup(sysGroupDao.findById(entity.getpId()).get());
        }
        sysGroupDao.save(entity);
        Arrays.stream(roleIds).forEach(roleId -> sysRoleGroupDao.save(
                new SysRoleGroup(sysRoleDao.findById(roleId).get(), roleId, entity, entity.getId())));
    }

    @Override
    public Integer deleteGroups(Integer[] ids) {
        Integer num = 0;
        num = sysGroupDao.deleteByIdIn(ids);
        //要记得同时删除中间表的数据
        sysRoleGroupDao.deleteByGroupIdIn(ids);
        return num;
    }

    @Override
    public void updateGroup(Map<String, Object> dataMap) {
        SysGroup entity = (SysGroup) dataMap.get("entity");
        Integer[] roleIds = (Integer[]) dataMap.get("roleIds");
        if(entity.getpId() != null && entity.getpId() != 0) {
            entity.setpSysGroup(sysGroupDao.findById(entity.getpId()).get());
        }
        sysGroupDao.save(entity);
        /* 中间表直接做修改操作很麻烦而且效率低容易出错，使用先全部删除再新增的方式来实现 */
        sysRoleGroupDao.deleteByGroupIdIn(new Integer[] {entity.getId()});
        Arrays.stream(roleIds).forEach(roleId -> sysRoleGroupDao.save(
                new SysRoleGroup(sysRoleDao.findById(roleId).get(), roleId, entity, entity.getId())));
    }

    @Override
    public List<SysGroup> fuzzyFindByName(String name) throws Demo1Exception{
        List<SysGroup> list = sysGroupDao.findByNameLike("%" + StringUtil.changeSpecialCharacter(name) + "%");
        if(list == null || list.size() < 1) {
            throw new Demo1Exception(StaticValues.SEARCH);
        }
        return list;
    }

    @Override
    public List<SysGroup> findAllGroups() {
        return sysGroupDao.findAll();
    }

    @Override
    public List<SysRole> findRolesByGroupId(Integer id) {
        List<SysRole> results = new ArrayList<>();
        List<SysRoleGroup> roleGroups = sysRoleGroupDao.findByGroupId(id);
        roleGroups.forEach(sysRoleGroup -> results.add(sysRoleGroup.getsRole()));
        return results;
    }
}
