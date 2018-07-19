package com.example.demo.security.service;

import com.example.demo.common.config.StaticValues;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : liuqitian
 * @date : 2018/7/6 15:20
 * @version : V1.1
 * 将403权限异常以近似的格式抛出
 */
public class AccessDeniedServletHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendError(403, StaticValues.ACCESSDENIED);
    }


}
