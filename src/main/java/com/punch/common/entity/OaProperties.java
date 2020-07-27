package com.punch.common.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author xiachao
 * @date 2020/07/21 9:13
 */
@Data
@ConfigurationProperties(prefix = "oa.url")
public class OaProperties {

	private String captcha;

	private String login;

	private String auth;

	private String punch;

}
