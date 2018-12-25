package com.java.utils;

import java.util.Random;

public class StringUtils {
	/**
	 * 判断字符串是否为空,<br/>
	 * 当字符串为null或者""时认为为空
	 * 2018-09-13 10:12:39
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		return string==null||string==""?true:false;
	}

	/**
	 * 随机获取指定长度字符串<br/>
	 * 2018-09-14 13:49:37
	 * @param i
	 * @return
	 */
	public static String getRandom(int i) {
		
		Random random = new Random();
		String str = "";
		while(true) {
			int temp = random.nextInt('z'-'0')+'0';
			if(temp>='0'&&temp<='9'
					||temp>='A'&&temp<='Z'
					||temp>='a'&&temp<='z') {
				str+=(char)temp;
			}
			if(str.length()==i) {
				break;
			}
		}
		return str;
	}

	/**
	 * 将驼峰命名法转为首字母大写命名规则
	 * 2018-12-25 13:53:22
	 * @param key
	 * @return
	 */
	public static String changeName(String key) {
		
		if(isEmpty(key)) {
			return key;
		}
		
		String []strs = key.split("_");
		String newKey = "";
		for (int i = 0; i < strs.length; i++) {
			if(i==0) {
				newKey+=strs[i];
			}else {
				String temp = strs[i].substring(1, strs[i].length());
				newKey+=(char)(strs[i].charAt(0)-32)+temp;
			}
		}
		return newKey;
	}
}
