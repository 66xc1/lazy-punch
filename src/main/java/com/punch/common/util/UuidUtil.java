package com.punch.common.util;

import java.util.UUID;

/**
 * @author xiachao
 * @date 2020/07/21 11:46
 */
public class UuidUtil {

	public static synchronized String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
