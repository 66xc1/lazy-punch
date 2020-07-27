package com.punch.common.entity;

import static com.punch.common.constans.GlobalConstants.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.punch.model.AddUserDto;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

/**
 * @author xiachao
 * @date 2020/07/24 14:18
 */
@Slf4j
public class OkHttpRequest {

	private final OkHttpClient client;

	private final AddUserDto user;

	private final String chrome;

	private String accessToken;

	private String toUrl;

	private final Lock lock = new ReentrantLock(true);

	String punchUrl = "http://115.238.110.210:3690/api/hrm/kq/attendanceButton/punchButton";

	public OkHttpRequest(AddUserDto user, String chrome) {
		this.user = user;
		this.chrome = chrome;
		this.client = new OkHttpClient.Builder().retryOnConnectionFailure(true).cookieJar(new CookieJar() {

			private final Map<String, List<Cookie>> cookiesMap = new ConcurrentHashMap<>();

			@Override
			public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
				log.info("url = {} response cookie = {}", httpUrl.url(), list);
				cookiesMap.put(httpUrl.host(), list);
			}

			@NotNull
			@Override
			public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
				List<Cookie> cookies = cookiesMap.get(httpUrl.host());
				log.info("url = {} request cookie = {}", httpUrl.url(), cookies);
				return null == cookies ? new ArrayList<>() : cookies;
			}
		}).connectionPool(new ConnectionPool(200, 5, TimeUnit.MINUTES)).connectTimeout(10L, TimeUnit.SECONDS)
				.readTimeout(10L, TimeUnit.SECONDS).build();
	}

	public Result<JSONObject> login() {
		String loginUrl = "http://115.238.110.210:8998/emp/passport/login";
		RequestBody requestBody = RequestBody.create(JSON.toJSONString(user),
				MediaType.parse("application/json; charset=utf-8"));
		Request request = new Request.Builder().url(loginUrl).post(requestBody).build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				ResponseBody responseBody = response.body();
				if (responseBody != null) {
					String body = responseBody.string();
					log.info("登录返回={}", body);
					JSONObject jsonObject = JSONObject.parseObject(body);
					if (!jsonObject.getInteger(ERR_CODE).equals(SUCCESS_CODE)) {
						return Result.fail(jsonObject.getString(ERR_MSG));
					}
					accessToken = jsonObject.getString("access_token");
					return Result.success(jsonObject);
				}
			}
		} catch (IOException e) {
			log.error("登录oa系统异常", e);
			return Result.fail("登录oa系统异常" + e.getMessage());
		}
		return Result.fail("登录oa系统失败");
	}

	public boolean auth() {
		String authUrl = "http://115.238.110.210:8998/emp/api/agent/client/link/home?agentid=-1&corpid=em4ce4068d933411eaaaec000c2985ba71&em_client_type=2";
		Request request = new Request.Builder().url(authUrl).addHeader("access_token", accessToken).get().build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				ResponseBody responseBody = response.body();
				if (responseBody != null) {
					String body = responseBody.string();
					log.info("授权返回={}", body);
					JSONObject jsonObject = JSONObject.parseObject(body);
					if (jsonObject.getInteger(ERR_CODE).equals(SUCCESS_CODE)) {
						toUrl = jsonObject.getString(TO_URL);
						return true;
					}
				}
			}
		} catch (IOException e) {
			log.error("授权异常", e);
		}
		return false;
	}

	public boolean getCookies() {
		if (null == toUrl) {
			return false;
		}
		String loginIdWeaver;
		String languageIdWeaver;
		String jSessionId;
		String host = "http://115.238.110.210";
		lock.lock();
		try {
			System.setProperty("webdriver.chrome.driver", this.chrome);
			// 创建chrome参数对象
			ChromeOptions options = new ChromeOptions();

			// 浏览器后台运行
			options.addArguments("--headless");
			options.addArguments("--disable-gpu");
			options.addArguments("window-size=1024,768");
			options.addArguments("--no-sandbox");
			options.addArguments("--verbose");
			options.addArguments("--whitelisted-ips=");
			options.addArguments("blink-settings=imagesEnabled=false");
			options.addArguments("--disable-javascript");

			WebDriver driver = new ChromeDriver(options);

			driver.get(toUrl);

			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("more")
//					.cssSelector("#entrance-page")
			));
			Set<org.openqa.selenium.Cookie> coo = driver.manage().getCookies();
			log.info("coo={}", coo);

			loginIdWeaver = coo.stream().filter(cookie -> "loginidweaver".equals(cookie.getName()))
					.map(org.openqa.selenium.Cookie::getValue).collect(Collectors.toList()).get(0);
			languageIdWeaver = coo.stream().filter(cookie -> "languageidweaver".equals(cookie.getName()))
					.map(org.openqa.selenium.Cookie::getValue).collect(Collectors.toList()).get(0);
			jSessionId = coo.stream().filter(cookie -> "ecology_JSessionid".equals(cookie.getName()))
					.map(org.openqa.selenium.Cookie::getValue).collect(Collectors.toList()).get(0);

			driver.close();
			driver.quit();
		} finally {
			lock.unlock();
		}
		if (null != loginIdWeaver && null != languageIdWeaver && null != jSessionId) {
			List<okhttp3.Cookie> cookieList = new ArrayList<>();
			okhttp3.Cookie cookie1 = new okhttp3.Cookie.Builder().name("loginidweaver").value(loginIdWeaver)
					.domain(HttpUrl.get(host).host()).build();
			cookieList.add(cookie1);
			okhttp3.Cookie cookie2 = new okhttp3.Cookie.Builder().name("languageidweaver").value(languageIdWeaver)
					.domain(HttpUrl.get(host).host()).build();
			cookieList.add(cookie2);
			okhttp3.Cookie cookie3 = new okhttp3.Cookie.Builder().name("ecology_JSessionid").value(jSessionId)
					.domain(HttpUrl.get(host).host()).build();
			cookieList.add(cookie3);
			client.cookieJar().saveFromResponse(HttpUrl.get(punchUrl), cookieList);
			return true;
		} else {
			return false;
		}
	}

	public boolean punch(String longitude, String latitude) {
		FormBody formBody = new FormBody.Builder().add("longitude", longitude).add("latitude", latitude).build();
		Request request = new Request.Builder().url(punchUrl).post(formBody).build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				ResponseBody responseBody = response.body();
				if (responseBody != null) {
					String body = responseBody.string();
					log.info("打卡返回={}", body);
					JSONObject jsonObject = JSONObject.parseObject(body);
					if (SUCCESS_STRING.equals(jsonObject.getString(SUCCESS))) {
						return true;
					}
				}
			}
		} catch (IOException e) {
			log.error("打卡异常", e);
		}
		return false;
	}
}
