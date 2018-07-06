package com.example.demo;

import com.example.demo.security.entity.SysUser;
import com.example.demo.security.service.SysUserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : liuqitian
 * @Date: 2018/6/26 14:44
 * @Version: V1.0
 * @Description: 初始化类
 */
public class ApplicationStartUp implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //do something here

        SysUserService suserService = contextRefreshedEvent.getApplicationContext().getBean(SysUserService.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Long count = suserService.count();
        for (int num = 0; num < count; num++) {
            SysUser sysUser = suserService.findById(num + 1);
            if(sysUser.getPassword().equals("cloversec")) {
                sysUser.setPassword(encoder.encode("cloversec"));
            }
            suserService.update(sysUser);
        }
    }
}
