package com.punch.service.impl;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.punch.common.enums.PunchType;
import com.punch.common.util.OkHttpUtil;
import com.punch.service.IPunchService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiachao
 * @date 2020/08/04 8:18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class PunchServiceImplTest {

	@Resource
	private IPunchService punchService;

	@Test
	void punch() throws InterruptedException {
		punchService.punch("xiac", "UID_IEDCJopo1JBj7Ed8JVLnvzfIAOKN", PunchType.PUNCH_OFF);
		punchService.punch("chenbin", "UID_S66sLJJM073ZdScz8V9LvqaUJ0zj", PunchType.PUNCH_OFF);
		Thread.sleep(300000);
	}

	@Test
	void workDay() {
		boolean flag = false;
		int workDay = 1;
		int max = 10;
		String url = "http://api.tianapi.com/txapi/jiejiari/index?key=13e7f466941f1b7661d8c01fd3fbcdf8&date="
				+ LocalDate.now().toString();
		for (int i = 0; i < max; i++) {
			String result = OkHttpUtil.get(url);
			if (result != null) {
				JSONObject jsonObject = JSON.parseObject(result);
				if (200 == jsonObject.getInteger("code")) {
					flag = true;
					workDay = jsonObject.getJSONArray("newslist").getJSONObject(0).getInteger("isnotwork");
					break;
				}
			}
		}
		if (!flag) {
			punchService.sendAllMessage("打卡服务异常，请手动打卡！！！");
		}
		log.info("workDay={}", workDay);
	}
}
