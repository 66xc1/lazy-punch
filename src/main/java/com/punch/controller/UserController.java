package com.punch.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.punch.common.entity.Result;
import com.punch.common.util.OkHttpUtil;
import com.punch.model.*;
import com.punch.service.IMessageService;
import com.punch.service.IUserService;

/**
 * @author xiachao
 * @date 2020/07/20 17:09
 */
@RestController
@RequestMapping("/oa")
public class UserController {

	@Resource
	private IUserService userService;

	@Resource
	private IMessageService messageService;

	/**
	 * 新增oa用户
	 *
	 * @param addUserDto    addUserDto
	 * @param bindingResult bindingResult
	 * @return result
	 */
	@PostMapping(value = "/addUser")
	public Result<String> addUser(@RequestBody @Valid AddUserDto addUserDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.fail(bindingResult);
		}
		return userService.addUser(addUserDto);
	}

	@PostMapping(value = "/getUser")
	public Result<Map<String, Object>> getUser(@RequestBody @Valid PunchDto dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.fail(bindingResult);
		}
		return userService.getUser(dto);
	}

	@PostMapping(value = "/getUserList")
	public Result<List<Map<String, Object>>> getUserList() {
		return userService.getUserList();
	}

	@PostMapping(value = "/updUser")
	public Result<String> updUser(@RequestBody @Valid AddUserDto addUserDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.fail(bindingResult);
		}
		return userService.updUser(addUserDto);
	}

	@PostMapping(value = "/updStatus")
	public Result<String> updStatus(@RequestBody @Valid UpdStatusDto updStatusDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.fail(bindingResult);
		}
		return userService.updStatus(updStatusDto);
	}

	@PostMapping(value = "/bindPush")
	public Result<String> bindPush(@RequestBody @Valid BindPushDto bindPushDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.fail(bindingResult);
		}
		return userService.bindPush(bindPushDto);
	}

	@PostMapping(value = "/getPusherList")
	public Result<List<GetPusherListVo>> getPusherList() {
		return messageService.getPusherList();
	}

	@PostMapping(value = "/punch")
	public Result<String> punch(@RequestBody @Valid PunchDto punchDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.fail(bindingResult);
		}
		return userService.punch(punchDto);
	}

	@PostMapping(value = "/test")
	public Result<String> test() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("longitude", "120.1862708875868");
		jsonObject.put("latitude", "30.325785047743057");
		String res = OkHttpUtil.postJson("http://115.238.110.210:3690/api/hrm/kq/attendanceButton/getOutButtons",
				jsonObject.toJSONString());
		return Result.success(res);
	}
}
