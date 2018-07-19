package com.example.demo.request.role;

import com.example.demo.common.config.ValidationStaticValues;
import com.example.demo.common.config.validator.IntegerArray;
import com.example.demo.security.entity.SysUser;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 创建角色时使用的request对象
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class RoleCreateRequest {

	@NotNull
	@Pattern(regexp = ValidationStaticValues.REGULAR_NAME,
			message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
	@Size(max = 256, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
	private String name;

	@IntegerArray()
	private Integer[] rightIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer[] getRightIds() {
		return rightIds;
	}

	public void setRightIds(Integer[] rightIds) {
		this.rightIds = rightIds;
	}
}
