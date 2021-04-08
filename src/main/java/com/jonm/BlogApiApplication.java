package com.jonm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan		// 扫描 @WebServlet、@WebFilter、@WebListener 注解自动注册
public class BlogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}

}
