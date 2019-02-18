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
 * 字段信息管理
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
	 * 获取选中表的字段信息
	 * 2018-12-21 15:20:51
	 * @param exportTableName
	 * @return
	 */
	@RequestMapping(value="/query",method=RequestMethod.POST)
	@ResponseBody
	public List<TblFieldInf> tblFieldsInf(@RequestBody SearchParamObject searchParamObject){
		String exportTableName = searchParamObject.getSearchObj().get("exportTableName");
		log.info("》》》》》》开始查询表字段信息");
		return fieldInfService.getFieldInfByTableName(exportTableName);
	}
	
	/**
	 * 更新表字段信息
	 * 2019-02-18 13:57:13
	 * @param searchParamObject
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public CodeMessageResult<TblFieldInf> updTblFieldsInf(@RequestBody SearchParamObject searchParamObject){
		log.info("》》》》》》开始更新表字段信息");
		
		TblFieldInf tblFieldInf = getParamsObj(searchParamObject.getSearchObj());
		return fieldInfService.updTblFieldsInf(tblFieldInf);
	}
	
	

	/**
	 * 删除表字段信息
	 * 2019-02-18 13:57:44
	 * @param searchParamObject
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public CodeMessageResult<TblFieldInf> deleteTblFieldsInf(@RequestBody SearchParamObject searchParamObject){
		log.info("》》》》》》开始删除表字段信息");
		
		TblFieldInf tblFieldInf = getParamsObj(searchParamObject.getSearchObj());
		return fieldInfService.deleteTblFieldsInf(tblFieldInf);
	}
	
	/**
	 * 增加表字段信息
	 * 2019-02-18 13:57:56
	 * @param searchParamObject
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public CodeMessageResult<TblFieldInf> createTblFieldsInf(@RequestBody SearchParamObject searchParamObject){
		log.info("》》》》》》开始增加表字段信息");
		
		TblFieldInf tblFieldInf = getParamsObj(searchParamObject.getSearchObj());
		return fieldInfService.createTblFieldsInf(tblFieldInf);
	}
	
	/**
	 * 获取入参
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
