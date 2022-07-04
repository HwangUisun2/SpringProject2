package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringProject2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringProject2Application.class, args);
	}

}
