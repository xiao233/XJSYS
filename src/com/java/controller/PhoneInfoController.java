package com.java.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/phone")
public class PhoneInfoController {
	private Logger log = Logger.getLogger(PhoneInfoController.class);
	
	@RequestMapping(value="/query",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getPhoneInf(){
		return null;
	}
}
