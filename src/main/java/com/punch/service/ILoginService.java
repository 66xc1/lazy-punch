package com.punch.service;

import com.punch.common.entity.Result;
import com.punch.model.LoginDto;

/**
 * @author xiachao
 * @date 2020/07/20 16:44
 */
public interface ILoginService {

	/**
	 * 登录
	 *
	 * @param dto dto
	 * @return result
	 */
	Result<String> login(LoginDto dto);
}
