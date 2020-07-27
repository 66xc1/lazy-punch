package com.punch.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author xiachao
 * @date 2020/07/24 10:07
 */
@Data
public class UpdStatusDto implements Serializable {

	private static final long serialVersionUID = -7705338669414569829L;

	@NotBlank(message = "loginId不能为空")
	private String loginId;

	@NotNull(message = "enable不能为空")
	@Min(value = 0, message = "enable值不符合要求")
	@Max(value = 1, message = "enable值不符合要求")
	private Integer enable;

}
