package com.punch.service;

import java.util.List;
import java.util.Map;

import com.punch.common.entity.Result;
import com.punch.model.AddUserDto;
import com.punch.model.BindPushDto;
import com.punch.model.PunchDto;
import com.punch.model.UpdStatusDto;

/**
 * @author xiachao
 * @date 2020/07/21 8:59
 */
public interface IUserService {

	/**
	 * 新增用户
	 *
	 * @param addUserDto dto
	 * @return result
	 */
	Result<String> addUser(AddUserDto addUserDto);

	/**
	 * 更新用户
	 *
	 * @param addUserDto dto
	 * @return result
	 */
	Result<String> updUser(AddUserDto addUserDto);

	/**
	 * 停用、启用打卡
	 *
	 * @param updStatusDto dto
	 * @return result
	 */
	Result<String> updStatus(UpdStatusDto updStatusDto);

	/**
	 * 绑定推送
	 *
	 * @param bindPushDto dto
	 * @return result
	 */
	Result<String> bindPush(BindPushDto bindPushDto);

	/**
	 * 查询用户
	 *
	 * @return result
	 */
	Result<List<Map<String, Object>>> getUserList();

	/**
	 * 打卡
	 *
	 * @param punchDto dto
	 * @return result
	 */
	Result<String> punch(PunchDto punchDto);
}
