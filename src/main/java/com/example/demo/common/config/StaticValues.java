package com.example.demo.common.config;

/**
 * 定义一些常量
 * @author liuqitian	
 * @date 2018年6月13日 
 *
 */
public class StaticValues {

	/**
	 * 需要使用字符串进行和系统管理员相关操作时使用
	 */
	public static final String ADMIN = "ADMIN";

	public static final String MYSELF = "不能删除自己";

	public static final String NODATA = "未找到指定数据";

	/**
	 * 需要使用字符串进行与查询相关操作时使用
	 */
	public static final String SEARCH = "搜索";

	/**
	 * 因被其他数据依赖而拒绝操作时使用
	 */
	public static final String DEPENDENCE = "依赖";

	/**
	 * 因不是文章作者而拒绝操作时使用
	 */
	public static final String AUTHOR = "作者";

	/**
	 * 因权限不足抛出异常时候使用
	 */
	public static final String ACCESSDENIED = "当前用户无访问权限";

}
