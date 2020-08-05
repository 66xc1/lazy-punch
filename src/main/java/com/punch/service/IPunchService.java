package com.punch.service;

import com.punch.common.enums.PunchType;

/**
 * @author xiachao
 * @date 2020/07/23 9:51
 */
public interface IPunchService {

	/**
	 * 打卡
	 *
	 * @param loginId loginId
	 * @param pushId  pushId
	 * @param type    类型 上班 or 下班
	 */
	void punch(String loginId, String pushId, PunchType type);

	/**
	 * 全员发送通知
	 *
	 * @param content 内容
	 */
	void sendAllMessage(String content);

}
