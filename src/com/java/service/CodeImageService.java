package com.java.service;

import java.io.IOException;

import com.java.entites.UserInf;

public interface CodeImageService {
	/**
	 * 产生图片验证码
	 * 2018-09-14 11:35:15
	 * @param key
	 * @return
	 * @throws IOException 
	 */
	String getCodeImage(String key) throws IOException;
	
	/**
	 * 验证图片验证码
	 * 2019-01-03 11:07:03
	 * @param user
	 * @param code
	 * @return
	 */
	int checkCodeImage(UserInf user,String code,String codeKey);
}
