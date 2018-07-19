package com.example.demo.request.role;

import com.example.demo.common.config.ValidationStaticValues;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 修改角色时使用的request对象
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class RoleUpdateRequest {

	@NotNull
	@Pattern(regexp = ValidationStaticValues.REGULAR_ID_MYSQL)
	@Size(max = 9, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_ID_MYSQL)
	private Integer id;

	/**
	 * 角色名称
	 */
	@NotNull
	@Pattern(regexp = ValidationStaticValues.REGULAR_NAME,
			message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
	@Size(max = 256, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
	private String name;

	/**
	 * 权限ids
	 */
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
