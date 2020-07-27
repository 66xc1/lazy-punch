package com.punch.common.util;

import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * @author xiachao
 * @date 2020/07/22 16:34
 */
class ClockUtilTest {

	@Test
	void getPunchOnTime() {
		for (int i = 0; i < 50; i++) {
			Date date = ClockUtil.getPunchOnTime();
			System.out.println(date.toString());
		}

	}

	@Test
	void getPunchOffTime() {
		for (int i = 0; i < 50; i++) {
			Date date = ClockUtil.getPunchOffTime();
			System.out.println(date.toString());
		}
	}
}
