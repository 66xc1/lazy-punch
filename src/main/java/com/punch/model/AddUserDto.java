package com.punch.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;
import com.punch.common.constans.GlobalConstants;

import lombok.Data;

/**
 * @author xiachao
 * @date 2020/07/20 17:12
 */
@Data
public class AddUserDto implements Serializable {

	private static final long serialVersionUID = -7111546397680125988L;

	public String loginUUID;

	@NotBlank(message = "验证码不能为空")
	@JSONField(name = "captcha_text")
	public String captchaText;

	@JSONField(name = "client_type")
	@NotNull(message = "系统类型不能为空")
	public Integer clientType;

	@JSONField(name = "login_type")
	public Integer loginType = GlobalConstants.LOGIN_TYPE;

	@NotBlank(message = "用户名不能为空")
	@JSONField(name = "loginid")
	public String loginId;

	@NotBlank(message = "随机码不能为空")
	public String key;

	@JSONField(name = "client_version")
	public String clientVersion = GlobalConstants.CLIENT_VERSION;

	@NotBlank(message = "密码不能为空")
	public String password;

	@NotBlank(message = "手机型号不能为空")
	@JSONField(name = "client_model")
	public String clientModel;

	@NotBlank(message = "设备名称不能为空")
	@JSONField(name = "device_name")
	public String deviceName;

	@NotBlank(message = "设备ID不能为空")
	@JSONField(name = "device_id")
	public String deviceId;

	@NotBlank(message = "操作系统版本号不能为空")
	@JSONField(name = "os_version")
	public String osVersion;

	@JSONField(name = "lang_type")
	public String langType = GlobalConstants.LANG_TYPE;

}
