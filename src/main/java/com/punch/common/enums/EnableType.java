package com.punch.common.enums;

/**
 * @author xiachao
 * @date 2020/07/24 11:19
 */
public enum EnableType {

	/**
	 * 启用
	 */
	YES(1),

	/**
	 * 停用
	 */
	NO(0);

	private final Integer status;

	public Integer getStatus() {
		return status;
	}

	EnableType(Integer status) {
		this.status = status;
	}
}
