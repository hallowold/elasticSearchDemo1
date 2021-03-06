package com.example.demo.security.config;

import com.example.demo.controller.ArticleController;
import com.example.demo.security.entity.SysUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : liuqitian
 * @date : 2018/7/2 10:31
 * @version : V1.0
 *  登录成功handle
 */
public class LoginSuccessHandler extends
        SavedRequestAwareAuthenticationSuccessHandler {

    private static final Log LOGGER = LogFactory.getLog(ArticleController.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String currentPrincipalName = authentication.getName();
        LOGGER.info("后台用户 [" + currentPrincipalName + "] 登录，登录IP : " + getIpAddress(request));
        super.onAuthenticationSuccess(request, response, authentication);
    }

    public static SysUser getCurrentUser() {
        return (SysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
