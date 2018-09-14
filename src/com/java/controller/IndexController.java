package com.java.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.constants.CodeMsgConstants;
import com.java.entites.CodeMessageResult;
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
	
	/**
	 * 登录或注册
	 * 2018-09-13 13:42:40
	 * @param userInf
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/log.reg",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> loginRegister(@RequestBody UserInf userInf,
			HttpServletRequest request) {
		Map<String,Object> rs = new HashMap<String,Object>();
		CodeMessageResult<UserInf> cms = userInfService.loginRegister(userInf);
		if(cms.getCode().equals(CodeMsgConstants.L_LOGIN_SUCC)) {
			request.getSession().setAttribute("userInf", cms.getResult().get(0));
		}
		rs.put("code", cms.getCode());
		rs.put("msg", cms.getMsg());
		return rs;
	}
	
	/**
	 * 首页
	 * 2018-09-14 09:15:20
	 * @return
	 */
	@RequestMapping(value="/first")
	public String firstPage() {
		return "first";
	}
	
	@RequestMapping(value="/hh")
	public void error() {
		throw new IllegalAccessError();
	}
	
}
