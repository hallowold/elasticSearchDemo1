package com.example.demo.request.group;

import com.example.demo.common.config.ValidationStaticValues;
import com.example.demo.common.config.validator.IntegerArray;

import javax.validation.constraints.*;

/**
 * @author : liuqitian
 * @date : 2018/7/16 12:31
 * @version : V1.2
 * 修改机构时使用的request对象
 */
public class SysGroupUpdateRequest {

    @NotNull
    @Max(value = 999999999, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_ID_MYSQL)
    @Min(value = 1, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_ID_MYSQL)
    private Integer id;

    @NotNull
    @Pattern(regexp = ValidationStaticValues.REGULAR_NAME,
            message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
    @Size(max = 256, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
    private String name;

    @Max(value = 999999999, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_ID_MYSQL)
    @Min(value = 1, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_ID_MYSQL)
    private Integer pid;

    @IntegerArray()
    private Integer[] roleIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
