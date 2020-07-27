package com.punch.common.enums;

/**
 * @author xiachao
 * @date 2020/07/23 10:22
 */
public enum PunchType {

	/**
	 * 上班
	 */
	PUNCH_ON(1, "上班"),

	/**
	 * 下班
	 */
	PUNCH_OFF(2, "下班");

	private final int type;

	private final String memo;

	public int getType() {
		return type;
	}

	public String getMemo() {
		return memo;
	}

	PunchType(int type, String memo) {
		this.type = type;
		this.memo = memo;
	}
}
