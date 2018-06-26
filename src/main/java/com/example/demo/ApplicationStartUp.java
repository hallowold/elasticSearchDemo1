package com.example.demo;

import com.example.demo.dao.RoleDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.service.impl.RoleServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

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

    }
}
