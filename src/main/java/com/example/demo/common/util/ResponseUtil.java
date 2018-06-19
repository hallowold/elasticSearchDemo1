package com.example.demo.common.util;

import com.example.demo.response.ResponseData;

/**
 * response工具类
 * @author liuqitian	
 * @date 2018年6月12日 
 *
 */
public class ResponseUtil {

	/**
	 * 根据给定的参数生成responseData对象，通用方法
	 * @param state		状态
	 * @param message	反馈信息
	 * @param data		数据对象
	 * @param code		状态码，如200，404，500等
	 * @return
	 */
	public static ResponseData createResponseData(boolean state, String message, Object data, int code) {
		ResponseData res = new ResponseData();
	    res.setState(state);
	    if("查询".equals(message) && data == null) {
	    	res.setMessage("查询完成，未找到指定数据");
	    	code = 200;
	    } else if ("查询".equals(message) && data != null) {
	    	res.setMessage("查询完成");
	    	code = 200;
	    } else if ("依赖".equals(message)) {
	    	res.setMessage("指定的数据被其他数据所依赖，无法删除，请检查");
	    	code = 500;
	    } else if ("逻辑错误".equals(message)) {
	    	res.setMessage("使用的功能逻辑错误，请与系统管理员联系");
	    	code = 500;
	    } else if ("作者".equals(message)) {
	    	res.setMessage("您不是文章的作者，无法进行修改或删除操作");
	    	code = 500;
	    } else if ("作者，批量删除".equals(message)) {
	    	res.setMessage("您不是所有选定文章的作者，无法进行批量删除操作");
	    	code = 500;
	    } else {
			res.setMessage(message);
	    }
	    res.setData(data);
		res.setCode(code);
		return res;
	}
	
	/**
	 * 根据给定的参数生成responseData对象，校验唯一性时使用
	 * @param state		状态
	 * @param message	填充信息，此处应填入校验字段，如登录名，用户姓名，文章标题等
	 * @return
	 */
	public static ResponseData createResponseDataCheckIfExsit(boolean state, String message) {
		ResponseData res = new ResponseData();
		if(state) {
    		res = ResponseUtil.createResponseData(state, "新增成功", null, 200);
    	} else {
    		res = ResponseUtil.createResponseData(state, "该" + message + "已被占用，请修改", null, 500);
    	}
		return res;
	}
	
	/**
	 * 生成responseData对象，操作者未登录时使用
	 * @return
	 */
	public static ResponseData createResponseDataNeedLogIn() {
		return new ResponseData(false, null, "需要登录", 500);
	}
	
	/**
	 * 生成responseData对象，操作者无模块权限时使用
	 * @return
	 */
	public static ResponseData createResponseDataHasNoRight() {
		return new ResponseData(false, null, "您没有此模块的操作权限", 500);
	}
	
}
