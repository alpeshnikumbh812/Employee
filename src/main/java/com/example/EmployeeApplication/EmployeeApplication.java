package com.example.EmployeeApplication;

import com.example.JWTConfig.WebSecurityConfig;
import com.example.controller.EmployeeController;
import com.example.dbConnector.DbConnector;
import com.example.javainuse.service.JwtUserDetailsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {EmployeeController.class, DbConnector.class,JwtUserDetailsService.class,WebSecurityConfig.class})
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class,args);
	}

}
