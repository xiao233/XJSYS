package com.java.controller;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.entites.CodeMessageResult;
import com.java.entites.TableInf;
import com.java.service.TableInfService;

@Controller
@RequestMapping(value="table")
public class TableInfController extends BaseController{
	private Logger log = Logger.getLogger(TableInfController.class);
	
	@Autowired
	private TableInfService tableInfService;
	
	
	@RequestMapping(value="/query",method=RequestMethod.POST)
	@ResponseBody
	public CodeMessageResult<TableInf> queryTableInf(HttpServletRequest request){
		//UserInf user = getSessinUser(request);
		return tableInfService.queryAll();
	}
	
	@RequestMapping(value="/import/{importTableName}",method=RequestMethod.POST,
			produces="multipart/form-data;charset=UTF-8")
	@ResponseBody
	public CodeMessageResult<String> importTableInf(HttpServletRequest request,
			@RequestParam(value="fileName",required=true) MultipartFile fileName ,@PathVariable String importTableName){
		
		CodeMessageResult<String>  cmr = new CodeMessageResult<String>();
		String code = "";
		code+=fileName.getOriginalFilename();
		cmr.setCode(code);
		return cmr;
	}
}
