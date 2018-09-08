package com.pwz.util;


import org.springframework.stereotype.Service;

import java.util.UUID;

public class UuidUtil {


	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	


}
