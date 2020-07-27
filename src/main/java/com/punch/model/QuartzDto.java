package com.punch.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author xiachao
 * @date 2020/07/22 13:14
 */
@Data
public class QuartzDto implements Serializable {

	private static final long serialVersionUID = 7289564952746310292L;

	@NotBlank(message = "bean不能为空")
	private String beanName;

	@NotBlank(message = "cron不能为空")
	private String cron;

	private String remark;
}
