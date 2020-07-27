package com.punch.common.util;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

/**
 * okHttp工具类
 *
 * @author xiachao
 * @date 2019/11/08 8:41
 */
@Slf4j
@Component
public class OkHttpUtil {

	private static OkHttpClient okHttpClient;

	@Resource
	public void setOkHttpClient(OkHttpClient okHttpClient) {
		OkHttpUtil.okHttpClient = okHttpClient;
	}

	public static String postJson(String url, String json) {
		RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
		Request request = new Request.Builder().url(url).post(requestBody).build();
		return execNewCall(request);
	}

	public static String postForm(String url, Map<String, String> map) {
		FormBody.Builder formBodyBuilder = new FormBody.Builder();
		if (map != null && map.keySet().size() > 0) {
			for (String key : map.keySet()) {
				formBodyBuilder.add(key, map.get(key));
			}
		}
		Request request = new Request.Builder().url(url).post(formBodyBuilder.build()).build();
		return execNewCall(request);
	}

	public static String get(String url) {
		Request request = new Request.Builder().url(url).get().build();
		return execNewCall(request);
	}

	private static String execNewCall(Request request) {
		try (Response response = okHttpClient.newCall(request).execute()) {
			if (response.isSuccessful()) {
				ResponseBody responseBody = response.body();
				if (responseBody != null) {
					String body = responseBody.string();
					log.info("接口返回={}", body);
					return body;
				}
				return null;
			}
		} catch (Exception e) {
			log.error("接口请求异常", e);
			return null;
		}
		return null;
	}
}
