//package com.example.demo.controller;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.common.UserSessionInfo;
//import com.example.demo.common.util.ResponseUtil;
//import com.example.demo.entity.User;
//import com.example.demo.request.LogInRequest;
//import com.example.demo.response.ResponseData;
//import com.example.demo.service.UserService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
///**
// * 登录注销控制器
// * @author liuqitian
// * @date 2018年6月13日
// *
// */
//@Api(value="/testdemo1", tags="登录注销接口模块")
//@RestController
//@RequestMapping("/loginout")
//public class LogInOutController {
//
//	/*
//	 * 注入用户服务类
//	 */
//	@Autowired
//	UserService userService;
//
//	/**
//	 * 登录接口
//	 * @param 	req		用户信息
//	 * @param	session
//	 * @return
//	 */
//	@ApiOperation(value="填写用户信息", notes = "填写用户信息")
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public ResponseData login(@RequestBody LogInRequest req, HttpSession session) {
//    	ResponseData res = new ResponseData();
//    	User user = userService.varifyUser(req.getLoginName(), req.getPassword());
//    	if (user != null) {
//    		res = ResponseUtil.createResponseData(true, "登录成功", null, 200);
//    		UserSessionInfo.setBackgroundUserInfo(user);
//    	} else {
//    		res = ResponseUtil.createResponseData(false, "登录失败", null, 500);
//    	}
//        return res;
//    }
//
//	/**
//	 * 注销接口
//	 * @param 	session
//	 * @return
//	 */
//    @ApiOperation(value="注销当前用户", notes = "注销当前用户")
//    @RequestMapping(value = "/logout",method = RequestMethod.GET)
//    public ResponseData logout(HttpSession session) {
//    	UserSessionInfo.removeBackgroundUserInfo();
//    	return ResponseUtil.createResponseData(true, "注销成功", null, 200);
//    }
//
//}
