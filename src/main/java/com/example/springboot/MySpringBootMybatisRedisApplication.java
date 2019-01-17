package com.example.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;


@EnableConfigurationProperties
@SpringBootApplication
//@MapperScan("com.example.springboot.dao")
@EnableCaching
public class MySpringBootMybatisRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootMybatisRedisApplication.class, args);
	}
}
