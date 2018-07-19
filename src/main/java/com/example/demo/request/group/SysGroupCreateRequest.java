package com.example.demo.request.group;

import com.example.demo.common.config.ValidationStaticValues;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author : liuqitian
 * @date : 2018/7/16 12:31
 * @version : V1.2
 * 创建机构时使用的request对象
 */
public class SysGroupCreateRequest {

    @NotNull
    @Pattern(regexp = ValidationStaticValues.REGULAR_NAME,
            message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
    @Size(max = 256, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
    private String name;

    /**
     * 父机构id
     */
    private Integer pid;

    /**
     * 角色id数组
     */
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
