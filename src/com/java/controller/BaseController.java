package com.java.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.java.entites.UserInf;

public class BaseController {
	
	private Logger log = Logger.getLogger(BaseController.class);
	/**
	 * 获取用户信息
	 * 2019-01-03 11:11:54
	 * @param request
	 * @return
	 */
	UserInf getUserInf(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("userInf");
		if(obj!=null) {
			return (UserInf) obj;
		}
		log.warn("没有获取到用户信息！");
		return null;
	}
}
