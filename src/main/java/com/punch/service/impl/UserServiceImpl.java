package com.punch.service.impl;

import static com.punch.common.constans.GlobalConstants.*;
import static com.punch.entity.tables.OaUser.OA_USER;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.punch.common.entity.OaProperties;
import com.punch.common.entity.OkHttpRequest;
import com.punch.common.entity.Result;
import com.punch.common.enums.EnableType;
import com.punch.common.enums.PunchType;
import com.punch.common.util.UuidUtil;
import com.punch.entity.tables.records.OaUserRecord;
import com.punch.model.AddUserDto;
import com.punch.model.BindPushDto;
import com.punch.model.PunchDto;
import com.punch.model.UpdStatusDto;
import com.punch.service.IPunchService;
import com.punch.service.IUserService;

/**
 * @author xiachao
 * @date 2020/07/21 9:00
 */
@Service
@EnableConfigurationProperties(OaProperties.class)
public class UserServiceImpl implements IUserService {

	@Resource
	private DSLContext create;

	@Resource
	private IPunchService punchService;

	@Value("${chrome}")
	private String chrome;

	/**
	 * 新增用户
	 *
	 * @param addUserDto dto
	 * @return result
	 */
	@Override
	public Result<String> addUser(AddUserDto addUserDto) {
		if (create.fetchExists(OA_USER, OA_USER.LOGIN_ID.eq(addUserDto.getLoginId()))) {
			return Result.fail("用户已存在");
		}
		// 登录oa系统，校验信息是否正确
		Result<JSONObject> result = login(addUserDto);
		if (!result.isState()) {
			return Result.fail(result.getMsg());
		}

		JSONObject jsonObject = result.getData();
		String userId = jsonObject.getString(USER_ID);
		String userName = jsonObject.getString(USER_NAME);
		String mobile = jsonObject.getString(MOBILE);

		// 新增oa用户
		OaUserRecord record = create.newRecord(OA_USER);
		record.from(addUserDto);
		record.setId(UuidUtil.getUUID());
		record.setMobile(mobile);
		record.setOaUserId(userId);
		record.setOaUserName(userName);
		record.setCreateTime(LocalDateTime.now());
		record.store();

		return Result.success("新增成功");
	}

	/**
	 * 更新用户
	 *
	 * @param addUserDto dto
	 * @return result
	 */
	@Override
	public Result<String> updUser(AddUserDto addUserDto) {
		// 登录oa系统，校验信息是否正确
		Result<JSONObject> result = login(addUserDto);
		if (!result.isState()) {
			return Result.fail(result.getMsg());
		}

		JSONObject jsonObject = result.getData();
		String userName = jsonObject.getString(USER_NAME);
		String mobile = jsonObject.getString(MOBILE);

		create.update(OA_USER).set(OA_USER.PASSWORD, addUserDto.getPassword()).set(OA_USER.MOBILE, mobile)
				.set(OA_USER.CLIENT_MODEL, addUserDto.getClientModel())
				.set(OA_USER.DEVICE_NAME, addUserDto.getDeviceName()).set(OA_USER.DEVICE_ID, addUserDto.getDeviceId())
				.set(OA_USER.OS_VERSION, addUserDto.getOsVersion()).set(OA_USER.OA_USER_NAME, userName)
				.where(OA_USER.LOGIN_ID.eq(addUserDto.getLoginId())).execute();
		return Result.success("修改成功");

	}

	/**
	 * 停用、启用打卡
	 *
	 * @param updStatusDto dto
	 * @return result
	 */
	@Override
	public Result<String> updStatus(UpdStatusDto updStatusDto) {
		create.update(OA_USER).set(OA_USER.ENABLE, updStatusDto.getEnable())
				.where(OA_USER.LOGIN_ID.eq(updStatusDto.getLoginId())).execute();
		return Result.success();
	}

	/**
	 * 绑定推送
	 *
	 * @param bindPushDto dto
	 * @return result
	 */
	@Override
	public Result<String> bindPush(BindPushDto bindPushDto) {
		create.update(OA_USER).set(OA_USER.PUSH_ID, bindPushDto.getPushId())
				.where(OA_USER.LOGIN_ID.eq(bindPushDto.getLoginId())).execute();
		return Result.success();
	}

	/**
	 * 查询用户
	 *
	 * @return result
	 */
	@Override
	public Result<List<Map<String, Object>>> getUserList() {
		return Result.success(create
				.select(OA_USER.LOGIN_ID.as("loginId"), OA_USER.PASSWORD, OA_USER.MOBILE,
						OA_USER.CLIENT_MODEL.as("clientModel"), OA_USER.DEVICE_NAME.as("deviceName"),
						OA_USER.DEVICE_ID.as("deviceId"), OA_USER.OS_VERSION.as("osVersion"), OA_USER.ENABLE,
						OA_USER.PUSH_ID.as("pushId"))
				.from(OA_USER).orderBy(OA_USER.ENABLE, OA_USER.CREATE_TIME).fetchMaps());
	}

	/**
	 * 打卡
	 *
	 * @param punchDto dto
	 * @return result
	 */
	@Override
	public Result<String> punch(PunchDto punchDto) {
		OaUserRecord record = create.fetchOne(OA_USER, OA_USER.LOGIN_ID.eq(punchDto.getLoginId()));
		if (EnableType.NO.getStatus().equals(record.getEnable())) {
			return Result.fail("用户已停用打卡");
		}
		if (null == record.getPushId()) {
			return Result.fail("用户未绑定推送");
		}
		punchService.punch(punchDto.getLoginId(), record.getPushId(), PunchType.PUNCH_OFF);
		return Result.success(null, "发送打卡请求成功");
	}

	private Result<JSONObject> login(AddUserDto addUserDto) {
		OkHttpRequest request = new OkHttpRequest(addUserDto, this.chrome);
		Result<JSONObject> result = request.login();
		if (!result.isState()) {
			return Result.fail(result.getMsg());
		}
		return result;
	}
}
