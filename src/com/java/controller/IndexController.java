package com.java.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entites.UserInf;
import com.java.service.UserInfService;

@Controller
public class IndexController {
	@Resource
	private UserInfService userInfService;
	
	@RequestMapping(value="/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/login")
	public String login(@ModelAttribute UserInf userInf) {
		String userName = userInf.getUserName();
		String userPwd = userInf.getUserPaw();
		return "first";
	}
}
