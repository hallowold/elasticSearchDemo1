package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 * @author liuqitian	
 * @date 2018年6月11日 
 *
 */
@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(Demo1Application.class);
		springApplication.addListeners(new ApplicationStartUp());
		springApplication.run(args);

//		SpringApplication.run(Demo1Application.class, args);
//		System.out.println("\n" + "lets do this" + "\n");
	}
}
