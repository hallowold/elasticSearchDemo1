package com.example.demo;

import com.example.demo.dao.RoleDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.security.entity.SysUser;
import com.example.demo.security.service.SysUserService;
import com.example.demo.service.impl.RoleServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Auther: liuqitian
 * @Date: 2018/6/26 14:44
 * @Version: V1.0
 * @Description: 初始化类
 */
public class ApplicationStartUp implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * @Auther: liuqitian
     * @Date: 2018/6/26 15:30
     * @Version: V1.0
     * @Param: [contextRefreshedEvent]
     * @return: void
     * @Description: 初始化系统管理员用户，配置权限，初始化系统管理员用户admin
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        UserServiceImpl userService = contextRefreshedEvent.getApplicationContext().getBean(UserServiceImpl.class);
        RoleServiceImpl roleService = contextRefreshedEvent.getApplicationContext().getBean(RoleServiceImpl.class);
        UserDAO userDAO = contextRefreshedEvent.getApplicationContext().getBean(UserDAO.class);
        RoleDAO roleDAO = contextRefreshedEvent.getApplicationContext().getBean(RoleDAO.class);
        if(roleDAO.findByName("系统管理员").isEmpty()) {
            roleService.addAdmin();
        }
        if(userDAO.findByLoginName("admin").isEmpty()) {
            userService.addAdmin(roleDAO.findById(1l).get());
        }

        SysUserService suserService = contextRefreshedEvent.getApplicationContext().getBean(SysUserService.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Long count = suserService.count();
        for (int num = 0; num < count; num++) {
            SysUser sysUser = suserService.findById(num + 1);
            if(sysUser.getPassword().equals("111111")) {
                sysUser.setPassword(encoder.encode(sysUser.getPassword().trim()));
            }
            suserService.update(sysUser);
        }
//
//        SysUser su= suserService.findByName("TEST");
//        System.out.println("密码1:"+su.getPassword());
//        BCryptPasswordEncoder bc=new BCryptPasswordEncoder();//将密码加密 可以先设置初始密码：000000
//        su.setPassword(bc.encode(su.getPassword().trim()));//然后使用密码为key值进行加密，运行主类后，会自动加密密码，可连接数据库查看。
//        System.out.println("密码2:"+su.getPassword());
//        suserService.update(su);//运行一次后记得注释这段重复加密会无法匹配

    }
}
