package com.punch.model;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xiachao
 * @date 2020/07/20 16:47
 */
@Data
public class LoginDto implements Serializable {

	private static final long serialVersionUID = -857418440783228635L;

	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空")
	public String phone;

	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空")
	public String pwd;
}
