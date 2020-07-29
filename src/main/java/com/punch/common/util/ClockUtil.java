package com.punch.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author xiachao
 * @date 2020/07/22 16:01
 */
public class ClockUtil {

	/**
	 * 获取随机上班打卡时间 7:50-8:00之间
	 *
	 * @return Date
	 */
	public static Date getPunchOnTime() {
		LocalDate now = LocalDate.now();
		LocalTime beginTime = LocalTime.of(7, 45);
		LocalTime endTime = LocalTime.of(7, 55);
		LocalDateTime beginDateTime = LocalDateTime.of(now, beginTime);
		LocalDateTime endDateTime = LocalDateTime.of(now, endTime);
		long random = random(beginDateTime, endDateTime);
		return new Date(random);
	}

	/**
	 * 获取随机下班打卡时间 17:10-18:00直接
	 *
	 * @return Date
	 */
	public static Date getPunchOffTime() {
		LocalDate now = LocalDate.now();
		LocalTime beginTime = LocalTime.of(17, 10);
		LocalTime endTime = LocalTime.of(17, 30);
		LocalDateTime beginDateTime = LocalDateTime.of(now, beginTime);
		LocalDateTime endDateTime = LocalDateTime.of(now, endTime);
		long random = random(beginDateTime, endDateTime);
		return new Date(random);
	}

	private static long random(LocalDateTime beginDateTime, LocalDateTime endDateTime) {
		long begin = beginDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
		long end = endDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
		long random = begin + (long) (Math.random() * (end - begin));
		// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
		if (random == begin || random == end) {
			return random(beginDateTime, endDateTime);
		}
		return random;
	}
}
