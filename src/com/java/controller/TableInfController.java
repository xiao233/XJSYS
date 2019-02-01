package com.java.controller;

import java.util.List;
import java.util.Map;

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

import com.java.entites.TblFieldInf;
import com.java.entites.TblTableInf;
import com.java.entites.UserInf;
import com.java.entites.common.CodeMessageResult;
import com.java.entites.common.SearchParamObject;
import com.java.service.FieldInfService;
import com.java.service.TableInfService;
import com.java.utils.StringUtils;
import com.java.utils.TimeUtils;

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
	public CodeMessageResult<TblTableInf> queryTableInf(HttpServletRequest request,
			@RequestBody SearchParamObject searchObj){
		//UserInf user = getSessinUser(request);
		log.info("��������������ʼ��ѯ����Ϣ");
		String tableName = searchObj.getSearchObj().get("tableName");
		int pageSize = searchObj.getPageSize();
		int pageCurr = searchObj.getPageCurr();
		
		TblTableInf tableInf = new TblTableInf();
		tableInf.setPageCurr(pageCurr);
		tableInf.setPageSize(pageSize);
		if(!StringUtils.isEmpty(tableName)) {
			tableInf.setTableName(tableName);
		}
		return tableInfService.queryPageAll(tableInf);
	}
	
	/**
	 * ��ѯ�������Ϣ
	 * 2019-01-30 14:29:23
	 * @param request
	 * @param searchObj
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ResponseBody
	public CodeMessageResult<TblTableInf> detailTableInf(HttpServletRequest request,
			@RequestBody SearchParamObject searchObj){
		//UserInf user = getSessinUser(request);
		log.info("��������������ʼ��ѯ����Ϣ");
		
		CodeMessageResult<TblTableInf> rs = new CodeMessageResult<TblTableInf>();
		
		
		TblTableInf tableInf = getQueryParams(searchObj);
		
		//��������Ϣ
		rs = tableInfService.queryAll(tableInf);
		
		return rs;
	}
	
	/**
	 * ����id���±������Ϣ
	 * 2019-02-01 09:31:12
	 * @param request
	 * @param searchObj
	 * @return
	 */
	@RequestMapping(value="/update/table",method=RequestMethod.POST)
	@ResponseBody
	public CodeMessageResult<TblTableInf> updateTableInf(HttpServletRequest request,
			@RequestBody SearchParamObject searchObj){
		UserInf user = getSessinUser(request);
		log.info("��������������ʼ���±���Ϣ");
		
		CodeMessageResult<TblTableInf> rs = new CodeMessageResult<TblTableInf>();
		
		
		TblTableInf tableInf = getQueryParams(searchObj);
		tableInf.setTableCrtUsr(user.getUserName());
		tableInf.setTableCrtTs(TimeUtils.getCurrentTimeByFormate(null));
		rs =  tableInfService.updateTableInfById(tableInf);
		rs.getResult().add(tableInf);
		return rs;
	}
	
	/**
	 * ɾ������Ϣ
	 * 2019-02-01 09:33:08
	 * @param request
	 * @param searchObj
	 * @return
	 */
	@RequestMapping(value="/delete/table",method=RequestMethod.POST)
	@ResponseBody
	public CodeMessageResult<TblTableInf> deleteTableInf(HttpServletRequest request,
			@RequestBody SearchParamObject searchObj){
		log.info("��������������ʼɾ������Ϣ");
		
		CodeMessageResult<TblTableInf> rs = new CodeMessageResult<TblTableInf>();
		
		
		TblTableInf tableInf = getQueryParams(searchObj);
		rs =  tableInfService.deleteTableInf(tableInf);
		
		return rs;
	}
	
	/**
	 * ��ӱ������Ϣ
	 * 2019-02-01 10:00:54
	 * @param request
	 * @param searchObj
	 * @return
	 */
	@RequestMapping(value="/create/table",method=RequestMethod.POST)
	@ResponseBody
	public CodeMessageResult<TblTableInf> createTableInf(HttpServletRequest request,
			@RequestBody SearchParamObject searchObj){
		UserInf user = getSessinUser(request);
		log.info("��������������ʼ��ӱ���Ϣ");
		
		CodeMessageResult<TblTableInf> rs = new CodeMessageResult<TblTableInf>();
		
		TblTableInf tableInf = getQueryParams(searchObj);
		tableInf.setTableCrtUsr(user.getUserName());
		tableInf.setTableCrtTs(TimeUtils.getCurrentTimeByFormate(null));
		rs =  tableInfService.createTableInfById(tableInf);
		rs.getResult().add(tableInf);
		
		return rs;
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
	
	/**
	 * ��ȡ��ѯ����
	 * 2019-01-29 09:55:25
	 * @param searchObj
	 * @return
	 */
	private TblTableInf getQueryParams(SearchParamObject searchObj) {
		TblTableInf tableInf = new TblTableInf();
		Map<String,String> map = searchObj.getSearchObj();
		String tableName = map.get("tableName");
		if(!StringUtils.isEmpty(tableName)) {
			tableInf.setTableName(tableName);
		}
		
		String tableId = map.get("tableId");
		if(!StringUtils.isEmpty(tableId)) {
			try {
				tableInf.setTableId(Integer.parseInt(tableId));
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
			}
		}
		
		String tableNameCn = map.get("tableNameCn");
		if(!StringUtils.isEmpty(tableNameCn)) {
			tableInf.setTableNameCn(tableNameCn);
		}
		
		String tableDescription = map.get("tableDescription");
		if(!StringUtils.isEmpty(tableDescription)) {
			tableInf.setTableDescription(tableDescription);
		}
		return tableInf;
	}
}
