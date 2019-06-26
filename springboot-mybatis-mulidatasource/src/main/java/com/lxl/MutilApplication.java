package com.lxl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lxl.dao")
public class MutilApplication {
	public static void main(String[] args) {
		SpringApplication.run(MutilApplication.class, args);
	}

}
