package com.punch.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baidu.aip.ocr.AipOcr;

/**
 * @author xiachao
 * @date 2020/07/23 15:18
 */
@Configuration
public class OcrConfig {

	public static final String APP_ID = "21570779";
	public static final String API_KEY = "vCiz5iTOzA9MC0sjmTd6kery";
	public static final String SECRET_KEY = "1v5vl7A8ILjLRbi8YyXgUgcKFBAudu1l";

	@Bean
	public AipOcr aipOcr() {
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		return client;
	}
}
