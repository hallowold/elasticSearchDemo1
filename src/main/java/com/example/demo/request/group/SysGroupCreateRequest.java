package com.example.demo.request.group;

/**
 * @author : liuqitian
 * @date : 2018/7/16 12:31
 * @version : V1.2
 * 创建机构时使用的request对象
 */
public class SysGroupCreateRequest {

    private String name;

    private Integer pid;

    private Integer[] roleIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }
}
