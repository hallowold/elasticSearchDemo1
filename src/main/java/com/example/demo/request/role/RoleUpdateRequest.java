package com.example.demo.request.role;

import com.example.demo.common.config.ValidationStaticValues;
import com.example.demo.common.config.validator.IntegerArray;

import javax.validation.constraints.*;

/**
 * 修改角色时使用的request对象
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class RoleUpdateRequest {

	@NotNull
	@Max(value = 999999999, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_ID_MYSQL)
	@Min(value = 1, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_ID_MYSQL)
	private Integer id;

	@NotNull
	@Pattern(regexp = ValidationStaticValues.REGULAR_NAME,
			message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
	@Size(max = 256, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
	private String name;

	@IntegerArray()
	private Integer[] rightIds;

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

	public Integer[] getRightIds() {
		return rightIds;
	}

	public void setRightIds(Integer[] rightIds) {
		this.rightIds = rightIds;
	}
}
