package com.hyxh;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.hyxh.*"})
@MapperScan("com.hyxh.dao")
public class HyxhApplication {

	public static void main(String[] args) {
		SpringApplication.run(HyxhApplication.class, args);
	}

}
