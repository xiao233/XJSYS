package com.java.service;

import java.io.IOException;

public interface CodeImageService {
	/**
	 * 产生图片验证码
	 * 2018-09-14 11:35:15
	 * @param key
	 * @return
	 * @throws IOException 
	 */
	String getCodeImage(String key) throws IOException;
}
