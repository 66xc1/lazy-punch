package com.punch.model;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

/**
 * @author xiachao
 * @date 2020/07/22 14:58
 */
@Data
public class JobKeyDto implements Serializable {

	private static final long serialVersionUID = -392212448749313158L;

	@NotBlank(message = "jobKey不能为空")
	private String jobKey;
}
