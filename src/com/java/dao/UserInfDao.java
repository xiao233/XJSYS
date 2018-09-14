package com.java.dao;

import java.util.List;

import com.java.entites.UserInf;

public interface UserInfDao {
	/**
	 * 登录
	 * 2018-09-13 10:32:42
	 * @param userInf
	 * @return
	 */
	public List<UserInf> checkUserInf(UserInf userInf);

	/**
	 * 注册
	 * 2018-09-12 17:21:09
	 * @param userInf
	 */
	public void register(UserInf userInf);
}
