package com.punch.common.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

/**
 * okHttp配置
 *
 * @author xiachao
 * @date 2019/11/08 8:33
 */
@Configuration
public class OkHttpConfig {

	@Bean
	public ConnectionPool pool() {
		return new ConnectionPool(200, 5, TimeUnit.MINUTES);
	}

	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient.Builder().retryOnConnectionFailure(true).connectionPool(pool())
				.connectTimeout(10L, TimeUnit.SECONDS).readTimeout(10L, TimeUnit.SECONDS)
				.addInterceptor(new LogInterceptor()).build();
	}
}
