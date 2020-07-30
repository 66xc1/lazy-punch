package com.punch.controller;

import javax.annotation.Resource;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.punch.common.entity.Result;
import com.punch.model.LoginDto;
import com.punch.model.PunchDto;
import com.punch.service.ILoginService;

/**
 * @author xiachao
 * @date 2020/07/20 16:34
 */
@RestController
@RequestMapping("/sys")
public class LoginController {

	@Resource
	private ILoginService loginService;

	@PostMapping(value = "/login")
	public Result<String> login(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.fail(bindingResult);
		}
		return loginService.login(loginDto);
	}

	@PostMapping(value = "/userLogin")
	public Result<String> userLogin(@RequestBody @Validated PunchDto punchDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.fail(bindingResult);
		}
		return loginService.userLogin(punchDto);
	}

}
