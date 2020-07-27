package com.punch.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author xiachao
 * @date 2020/07/24 11:02
 */
@Data
public class PunchDto implements Serializable {

	private static final long serialVersionUID = 1404814504972985844L;

	@NotBlank(message = "loginId不能为空")
	public String loginId;
}
