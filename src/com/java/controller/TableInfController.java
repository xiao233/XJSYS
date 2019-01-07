package com.java.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.entites.CodeMessageResult;
import com.java.entites.SearchParamObject;
import com.java.entites.TblFieldInf;
import com.java.entites.TblTableInf;
import com.java.service.FieldInfService;
import com.java.service.TableInfService;

@Controller
@RequestMapping(value="table")
public class TableInfController extends BaseController{
	private Logger log = Logger.getLogger(TableInfController.class);
	
	@Autowired
	private TableInfService tableInfService;
	
	@Autowired
	private FieldInfService fieldInfService;
	
	/**
	 * ��ȡ��������
	 * 2018-12-21 15:17:05
	 * @param request
	 * @return	
	 */
	@RequestMapping(value="/query",method=RequestMethod.POST)
	@ResponseBody
	public CodeMessageResult<TblTableInf> queryTableInf(HttpServletRequest request){
		//UserInf user = getSessinUser(request);
		log.info("��������������ʼ��ѯ����Ϣ");
		return tableInfService.queryAll();
	}
	
	/**
	 * �ļ����롢�ϴ�
	 * 2018-12-21 15:17:20
	 * @param request
	 * @param fileName
	 * @param importTableName
	 * @return
	 */
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
	
	/**
	 * ��ȡѡ�б���ֶ���Ϣ
	 * 2018-12-21 15:20:51
	 * @param exportTableName
	 * @return
	 */
	@RequestMapping(value="/field",method=RequestMethod.POST)
	@ResponseBody
	public List<TblFieldInf> tblFieldsInf(@RequestBody SearchParamObject searchParamObject){
		String exportTableName = searchParamObject.getSearchObj().get("exportTableName");
		log.info("��������������ʼ��ѯ���ֶ���Ϣ");
		return fieldInfService.getFieldInfByTableName(exportTableName);
	}
	
	/**
	 * ����ָ�����������Ϣ
	 * 2018-12-21 15:23:21
	 * @param request
	 */
	@RequestMapping(value="/export",method=RequestMethod.POST)
	public void exportTableInf(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("��������������ʼ����������");
		fieldInfService.exportTableInf(response,request);
	}
}
