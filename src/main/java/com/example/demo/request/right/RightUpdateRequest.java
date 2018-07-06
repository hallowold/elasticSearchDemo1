package com.example.demo.request.right;

/**
 * 修改权限时使用的request对象
 * @author liuqitian
 * @version V1.1 因引入spring security统一管理权限，代码重构
 * @date 2018年6月14日 
 *
 */
public class RightUpdateRequest {

	private Integer id;

	/**
	 * 权限名称
	 */
	private String name;

	/**
	 * 权限url
	 */
	private String rightUrl;

	/**
	 * 对应的请求类型
	 */
	private String methodType;

	/**
	 * 备注信息
	 */
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
