package com.punch.common.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author xiachao
 * @date 2020/07/22 17:11
 */
@Data
@ConfigurationProperties(prefix = "locate")
public class LocationProperties implements Serializable {

	private static final long serialVersionUID = -1322610583713423039L;

	private List<Point> points;

	@Data
	public static class Point implements Serializable {

		private static final long serialVersionUID = 3581038430084132493L;

		private String longitude;

		private String latitude;
	}

}
