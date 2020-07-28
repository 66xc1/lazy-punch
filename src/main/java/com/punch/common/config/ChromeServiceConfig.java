package com.punch.common.config;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiachao
 * @date 2020/07/28 14:15
 */
@Configuration
@ConfigurationProperties(prefix = "chrome")
@Slf4j
public class ChromeServiceConfig {

	private String locate;

	public String getLocate() {
		return locate;
	}

	public void setLocate(String locate) {
		this.locate = locate;
	}

	@Bean
	public ChromeDriverService chromeDriverService() {
		ChromeDriverService service = new ChromeDriverService.Builder().usingAnyFreePort()
				.usingDriverExecutable(new File(this.getLocate())).build();
		try {
			service.start();
		} catch (IOException e) {
			log.error("初始化chromeDriverService失败", e);
		}
		Runtime.getRuntime().addShutdownHook(new Thread(service::stop));
		return service;
	}

}
