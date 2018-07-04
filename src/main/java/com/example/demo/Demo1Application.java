package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * 项目启动类
 * @author liuqitian	
 * @date 2018年6月11日 
 *
 */
@EnableAutoConfiguration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class Demo1Application {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(Demo1Application.class);
		springApplication.addListeners(new ApplicationStartUp());
        springApplication.run(args);
	}

}
