package com.example.demo.security.config;


import com.example.demo.security.dao.SysRightDao;
import com.example.demo.security.entity.SysRight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author : liuqitian
 * @date : 2018/7/2 10:51
 * @version : V1.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private SysRightDao sysRightDao;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        List<SysRight> sysRightList = sysRightDao.findAll();
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");

        /* 将right表中的所有权限全部导入，参数为url路径 */
        sysRightList.forEach(sysRight -> registry.addViewController(sysRight.getRightUrl()));
    }

}
