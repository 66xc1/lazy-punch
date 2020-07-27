package com.punch.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiachao
 * @date 2020/07/22 14:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UpdQuartzDto extends QuartzDto {

	private static final long serialVersionUID = 1558757416677546415L;

	@NotBlank(message = "jobKey不能为空")
	private String jobKey;

}
