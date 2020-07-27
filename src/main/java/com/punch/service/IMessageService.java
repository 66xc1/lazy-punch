package com.punch.service;

import java.util.List;

import com.punch.common.entity.Result;
import com.punch.model.GetPusherListVo;

/**
 * @author xiachao
 * @date 2020/07/23 9:13
 */
public interface IMessageService {

	/**
	 * 发送打卡消息
	 *
	 * @param uid     uid
	 * @param content 内容
	 */
	void sendMessage(String uid, String content);

	/**
	 * 查询关注用户
	 *
	 * @return result
	 */
	Result<List<GetPusherListVo>> getPusherList();
}
