package com.example.demo;

import com.example.demo.security.entity.SysUser;
import com.example.demo.security.service.SysUserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @author : liuqitian
 * @date : 2018/6/26 14:44
 * @version : V1.0
 * 初始化类
 */
public class ApplicationStartUp implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        SysUserService suserService = contextRefreshedEvent.getApplicationContext().getBean(SysUserService.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        Long count = suserService.count();
        List<SysUser> userList = suserService.findByPassWord("123456");
        for (SysUser user : userList) {
            user.setPassword(encoder.encode("123456"));
            suserService.update(user);
        }
    }
}
