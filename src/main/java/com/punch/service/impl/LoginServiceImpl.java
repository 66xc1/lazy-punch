package com.punch.service.impl;

import static com.punch.entity.Tables.SYS_USER;

import javax.annotation.Resource;

import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import com.punch.common.entity.Result;
import com.punch.entity.tables.records.SysUserRecord;
import com.punch.model.LoginDto;
import com.punch.service.ILoginService;

/**
 * @author xiachao
 * @date 2020/07/20 16:49
 */
@Service
public class LoginServiceImpl implements ILoginService {

	@Resource
	private DSLContext create;

	/**
	 * 登录
	 *
	 * @param dto dto
	 * @return result
	 */
	@Override
	public Result<String> login(LoginDto dto) {
		SysUserRecord record = create.fetchOne(SYS_USER, SYS_USER.PHONE.eq(dto.getPhone()));
		if (null == record) {
			return Result.fail("用户名不存在");
		}
		if (!record.getPwd().equals(dto.getPwd())) {
			return Result.fail("密码错误");
		}
		return Result.success();
	}
}
