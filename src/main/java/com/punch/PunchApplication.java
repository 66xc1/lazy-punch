package com.punch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author xiachao
 * @date 2020/07/20 16:33
 */
@SpringBootApplication
@EnableAsync
public class PunchApplication {
	public static void main(String[] args) {
		SpringApplication.run(PunchApplication.class, args);
	}
}
