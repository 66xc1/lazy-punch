package com.punch.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.punch.common.entity.Result;
import com.punch.model.GetPusherListVo;
import com.punch.service.IMessageService;
import com.zjiecode.wxpusher.client.WxPusher;
import com.zjiecode.wxpusher.client.bean.Message;
import com.zjiecode.wxpusher.client.bean.MessageResult;
import com.zjiecode.wxpusher.client.bean.Page;
import com.zjiecode.wxpusher.client.bean.WxUser;

/**
 * @author xiachao
 * @date 2020/07/23 9:14
 */
@Service
public class MessageServiceImpl implements IMessageService {

	/**
	 * 发送打卡消息
	 *
	 * @param uid     uid
	 * @param content 内容
	 */
	@Override
	@Async
	public void sendMessage(String uid, String content) {
		Message message = new Message();
		message.setAppToken("AT_JMmvTucPFh3yJ6PmFUNyaUIeBzLnkU3t");
		message.setContentType(Message.CONTENT_TYPE_TEXT);
		message.setContent(content);
		message.setSummary(content);
		message.setUid(uid);
		com.zjiecode.wxpusher.client.bean.Result<List<MessageResult>> result = WxPusher.send(message);
		if (!result.isSuccess()) {
			WxPusher.send(message);
		}
	}

	/**
	 * 查询关注用户
	 *
	 * @return result
	 */
	@Override
	public Result<List<GetPusherListVo>> getPusherList() {
		com.zjiecode.wxpusher.client.bean.Result<Page<WxUser>> res = WxPusher
				.queryWxUser("AT_JMmvTucPFh3yJ6PmFUNyaUIeBzLnkU3t", 1, 10000);
		if (res.isSuccess()) {
			Page<WxUser> data = res.getData();
			List<WxUser> records = data.getRecords();
			List<GetPusherListVo> list = new ArrayList<>();
			GetPusherListVo vo;
			for (WxUser wxUser : records) {
				vo = new GetPusherListVo();
				vo.setPushId(wxUser.getUid());
				vo.setHeadImg(wxUser.getHeadImg());
				vo.setNickName(wxUser.getNickName());
				list.add(vo);
			}
			return Result.success(list);
		}
		return Result.fail(res.getMsg());
	}
}
