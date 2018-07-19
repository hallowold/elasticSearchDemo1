package com.example.demo;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author : liuqitian
 * @date : 2018/6/26 14:44
 * @version : V1.0
 * 初始化类
 */
public class ApplicationStartUp implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

//        SysUserService suserService = contextRefreshedEvent.getApplicationContext().getBean(SysUserService.class);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        Long count = suserService.count();
//        for (int num = 0; num < count; num++) {
//            SysUser sysUser = suserService.findById(num + 1);
//            if(sysUser.getPassword().equals("123456")) {
//                sysUser.setPassword(encoder.encode("123456"));
//            }
//            suserService.update(sysUser);
//        }
    }
}
