package com.example.demo.common.config;

/**
 * 模块标识enum，用于权限判断
 * @author liuqitian	
 * @date 2018年6月13日 
 *
 */
public enum ModuleNameEnum {
	
	USER("用户", 11), ROLE("角色", 12), RIGHT("权限", 13), ARTICLE("文章", 21);

	private String moduleName;
	private int moduleFlag;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public int getModuleFlag() {
		return moduleFlag;
	}

	public void setModuleFlag(int moduleFlag) {
		this.moduleFlag = moduleFlag;
	}

	private ModuleNameEnum(String moduleName, int moduleFlag) {
		this.moduleName = moduleName;
		this.moduleFlag = moduleFlag;
	}

}
