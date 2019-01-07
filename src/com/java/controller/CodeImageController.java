package com.java.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.constants.CodeMsgConstants;
import com.java.service.CodeImageService;
import com.java.utils.GetUUID;

import jxl.common.Logger;

@Controller
@RequestMapping("/codeImage")
public class CodeImageController{
	private Logger log = Logger.getLogger(CodeImageController.class);
	@Resource
	private CodeImageService codeImageService;
	/**
	 * 产生图片验证码
	 * 2018-09-14 11:27:07
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/get",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getCodeImage(HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		String codeImageBase64str="";
		String key = GetUUID.getUUID(0, 32);
		try {
			codeImageBase64str = codeImageService.getCodeImage(key);
			rs.put("code", CodeMsgConstants.CODE_BORN_SUCC);
			rs.put("msg", CodeMsgConstants.CODE_BORN_SUCC_MSG);
		} catch (IOException e) {
			log.error("生成图片验证码异常："+e.getMessage());
			rs.put("code", CodeMsgConstants.CODE_BORN_FAIL);
			rs.put("msg", CodeMsgConstants.CODE_BORN_FAIL_MSG);
		}
		
		rs.put("codeImage", "data:image/png;base64,"+codeImageBase64str);
		rs.put("key", key);
		return rs;
	}
}
