package com.java.service;

import com.java.entites.CodeMessageResult;
import com.java.entites.UserInf;

public interface UserInfService {
	
	/**
	 * 注册登录
	 * 2018-09-13 10:28:09
	 * @param userInf
	 * @return
	 */
	public CodeMessageResult<UserInf> loginRegister(UserInf userInf);
}
