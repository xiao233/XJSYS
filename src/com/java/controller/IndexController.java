package com.java.controller;

import java.io.IOException;
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
import com.java.entites.UserInf;
import com.java.entites.common.CodeMessageResult;
import com.java.service.CodeImageService;
import com.java.service.UserInfService;
import com.java.utils.GetUUID;

import jxl.common.Logger;

@Controller
public class IndexController {
	private Logger log = Logger.getLogger(IndexController.class);
	@Resource
	private UserInfService userInfService;
	@Resource
	private CodeImageService codeImageService;
	
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
		log.info(cms.getCode()+": "+cms.getMsg());
		
		if(cms.getCode().equals(CodeMsgConstants.L_LOGIN_SUCC)) {
			request.getSession().setAttribute("userInf", cms.getResult().get(0));
		}
		
		//登录失败或注册失败，则刷新验证码
		if(!cms.getCode().equals(CodeMsgConstants.L_LOGIN_SUCC)
				&&!cms.getCode().equals(CodeMsgConstants.R_REGISTER_SUCC)) {
			String codeImageBase64str="";
			String key = GetUUID.getUUID(0, 32);
			try {
				codeImageBase64str = codeImageService.getCodeImage(key);
				rs.put("codeImage", "data:image/png;base64,"+codeImageBase64str);
				rs.put("key", key);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
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
