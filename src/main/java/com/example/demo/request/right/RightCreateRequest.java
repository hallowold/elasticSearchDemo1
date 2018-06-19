package com.example.demo.request.right;

/**
 * 创建权限时使用的request对象
 * @author liuqitian	
 * @date 2018年6月14日 
 *
 */
public class RightCreateRequest {
	
	//用户id，暂用，解决主键问题后应去除
	private Long id;
	
	//模块名
	private String moduleName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
}
