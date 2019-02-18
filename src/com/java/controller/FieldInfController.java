package com.java.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.entites.TblFieldInf;
import com.java.entites.common.CodeMessageResult;
import com.java.entites.common.SearchParamObject;
import com.java.service.FieldInfService;
import com.java.utils.StringUtils;

/**
 * �ֶ���Ϣ����
 * @author xjl
 * 2019-02-18 13:59:24
 */
@Controller
@RequestMapping("field")
public class FieldInfController {
	
private Logger log = Logger.getLogger(FieldInfController.class);
	
	@Autowired
	private FieldInfService fieldInfService;
	
	/**
	 * ��ȡѡ�б���ֶ���Ϣ
	 * 2018-12-21 15:20:51
	 * @param exportTableName
	 * @return
	 */
	@RequestMapping(value="/query",method=RequestMethod.POST)
	@ResponseBody
	public List<TblFieldInf> tblFieldsInf(@RequestBody SearchParamObject searchParamObject){
		String exportTableName = searchParamObject.getSearchObj().get("exportTableName");
		log.info("��������������ʼ��ѯ���ֶ���Ϣ");
		return fieldInfService.getFieldInfByTableName(exportTableName);
	}
	
	/**
	 * ���±��ֶ���Ϣ
	 * 2019-02-18 13:57:13
	 * @param searchParamObject
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public CodeMessageResult<TblFieldInf> updTblFieldsInf(@RequestBody SearchParamObject searchParamObject){
		log.info("��������������ʼ���±��ֶ���Ϣ");
		
		TblFieldInf tblFieldInf = getParamsObj(searchParamObject.getSearchObj());
		return fieldInfService.updTblFieldsInf(tblFieldInf);
	}
	
	

	/**
	 * ɾ�����ֶ���Ϣ
	 * 2019-02-18 13:57:44
	 * @param searchParamObject
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public CodeMessageResult<TblFieldInf> deleteTblFieldsInf(@RequestBody SearchParamObject searchParamObject){
		log.info("��������������ʼɾ�����ֶ���Ϣ");
		
		TblFieldInf tblFieldInf = getParamsObj(searchParamObject.getSearchObj());
		return fieldInfService.deleteTblFieldsInf(tblFieldInf);
	}
	
	/**
	 * ���ӱ��ֶ���Ϣ
	 * 2019-02-18 13:57:56
	 * @param searchParamObject
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public CodeMessageResult<TblFieldInf> createTblFieldsInf(@RequestBody SearchParamObject searchParamObject){
		log.info("��������������ʼ���ӱ��ֶ���Ϣ");
		
		TblFieldInf tblFieldInf = getParamsObj(searchParamObject.getSearchObj());
		return fieldInfService.createTblFieldsInf(tblFieldInf);
	}
	
	/**
	 * ��ȡ���
	 * 2019-02-18 14:24:35
	 * @param searchObj
	 * @return
	 */
	private TblFieldInf getParamsObj(Map<String, String> searchObj) {
		TblFieldInf tblFieldInf = new TblFieldInf();
		String tableId = searchObj.get("tableId");
		if(!StringUtils.isEmpty(tableId)) {
			try {
				tblFieldInf.setTableId(Integer.parseInt(tableId));
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
			}
		}
		tblFieldInf.setFieldId(searchObj.get("fieldId"));
		tblFieldInf.setFieldName(searchObj.get("fieldName"));
		tblFieldInf.setFieldNameCn(searchObj.get("fieldNameCn"));
		tblFieldInf.setIsShow(searchObj.get("isShow"));
		return tblFieldInf;
	}


}
