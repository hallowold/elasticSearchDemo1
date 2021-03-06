package com.example.demo.request.right;

import com.example.demo.common.config.ValidationStaticValues;
import com.example.demo.common.config.validator.MethodType;

import javax.validation.constraints.*;

/**
 * 修改权限时使用的request对象
 * @author liuqitian
 * @version V1.1 因引入spring security统一管理权限，代码重构
 * @date 2018年6月14日 
 *
 */
public class RightUpdateRequest {

	@NotNull
	@Max(value = 999999999, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_ID_MYSQL)
	@Min(value = 1, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_ID_MYSQL)
	private Integer id;

	@NotNull
	@Pattern(regexp = ValidationStaticValues.REGULAR_NAME,
			message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
	@Size(max = 256, message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_NAME)
	private String name;

	@NotNull
	@Pattern(regexp = ValidationStaticValues.REGULAR_REST_URL,
			message = ValidationStaticValues.START_FLAG + ValidationStaticValues.VALID_REST_URL)
	private String rightUrl;

	/**
	 * 对应的http请求类型
	 */
	@NotNull
	@MethodType()
	private String methodType;

	/**
	 * 备注信息
	 */
	@Pattern(regexp = ValidationStaticValues.REGULAR_CONTENT + "{0,500}",
			message = ValidationStaticValues.START_FLAG + "备注不能超过500" + ValidationStaticValues.REGULAR_CONTENT)
	@Size(max = 500, message = ValidationStaticValues.START_FLAG + "备注不能超过500" + ValidationStaticValues.REGULAR_CONTENT)
	private String remark;

	/**
	 * 所对应的方法名
	 */
	private String methodName;

	/**
	 * 所对应的包路径
	 */
	private String methodPath;

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

	public String getRightUrl() {
		return rightUrl;
	}

	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodPath() {
		return methodPath;
	}

	public void setMethodPath(String methodPath) {
		this.methodPath = methodPath;
	}
}
