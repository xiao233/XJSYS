package com.java.utils;

import java.util.UUID;

public class GetUUID {
	/**
	 * 获取指定长度的UUID
	 * 2018-09-13 15:41:28
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getUUID(int start,int end) {
		UUID uuid = UUID.randomUUID();
		String ustring = uuid.toString().replace("-", "");
		if(start>=0&&end<=ustring.length()&&start<=end) {
			ustring=ustring.substring(start, end);
		}
		return ustring;
	}
}
