package com.java.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.constants.CodeMsgConstants;
import com.java.dao.FieldInfDao;
import com.java.dao.jdbc.CommonSQLOperationUtils;
import com.java.entites.TblFieldInf;
import com.java.entites.common.CodeMessageResult;
import com.java.service.FieldInfService;
import com.java.utils.StringUtils;
import com.java.utils.WriteInFileUtils;

@Service
public class FieldInfServiceImpl implements FieldInfService {
	
	private Logger log = Logger.getLogger(FieldInfServiceImpl.class);
	
	@Autowired
	private FieldInfDao fieldInfDao;

	@Override
	public List<TblFieldInf> getFieldInfByTableName(String tableName) {
		List<TblFieldInf> list = new ArrayList<TblFieldInf>();
		if(StringUtils.isEmpty(tableName)) {
			log.error("表名为空："+tableName);
		}
		TblFieldInf tblFieldInf = new TblFieldInf();
		tblFieldInf.setTableName(tableName);
		try {
			list = fieldInfDao.getTableFieldInf(tblFieldInf);
		} catch (Exception e) {
			log.error("查询表‘"+tableName+"’字段信息异常： "+e.getMessage());
		}
		return list;
	}
	
	@Override
	public void exportTableInf(HttpServletResponse response, HttpServletRequest request) {
		
		String tableName = request.getParameter("exportTableName");
		String fileName = request.getParameter("exportFileName");
		String fileType = request.getParameter("exportFileType");
		String fields = request.getParameter("selectFields");
		
		Map<String,Object> params = new HashMap<String,Object>();
		List<String> queryFields = parseFields(fields);
		
		List<Map<String,Object>> queryResult = CommonSQLOperationUtils.queryMap(tableName, queryFields, params);
		
		log.info("查询到表'"+tableName+"'"+queryResult.size()+"条数据！");
		WriteInFileUtils.writeInFile(queryResult,fileName+fileType,response,tableName);
	}
	

	
	@Override
	public CodeMessageResult<TblFieldInf> updTblFieldsInf(TblFieldInf tblFieldInf) {
		CodeMessageResult<TblFieldInf> rs = new CodeMessageResult<TblFieldInf>();
		String code ="";
		String msg = "";
		if(tblFieldInf.getTableId()==null) {
			code = CodeMsgConstants.DELETE_FAILD;
			msg = "表id(tableId)不能为空！";
			rs.setCode(code);
			rs.setMsg(msg);
			log.info(code+": "+msg);
			return rs;
		}
		
		if(StringUtils.isEmpty(tblFieldInf.getFieldId())) {
			code = CodeMsgConstants.DELETE_FAILD;
			msg = "字段id(fieldId)不能为空！";
			rs.setCode(code);
			rs.setMsg(msg);
			log.info(code+": "+msg);
			return rs;
		}
		
		try {
			fieldInfDao.updTblFieldsInf(tblFieldInf);
			msg = CodeMsgConstants.UPDATE_SUCCESS_MSG;
			code = CodeMsgConstants.UPDATE_SUCCESS;
		}catch (Exception e) {
			log.error("更新异常: "+e.getMessage());
			msg = CodeMsgConstants.UPDATE_FAILD_MSG;
			code = CodeMsgConstants.UPDATE_FAILD;
		}
		log.info(code+": "+msg);
		rs.setCode(code);
		rs.setMsg(msg);
		return rs;
	}

	@Override
	public CodeMessageResult<TblFieldInf> deleteTblFieldsInf(TblFieldInf tblFieldInf) {
		CodeMessageResult<TblFieldInf> rs = new CodeMessageResult<TblFieldInf>();
		String code ="";
		String msg = "";
		if(tblFieldInf.getTableId()==null) {
			code = CodeMsgConstants.DELETE_FAILD;
			msg = "表id(tableId)不能为空！";
			rs.setCode(code);
			rs.setMsg(msg);
			log.info(code+": "+msg);
			return rs;
		}
		
		if(StringUtils.isEmpty(tblFieldInf.getFieldId())) {
			code = CodeMsgConstants.DELETE_FAILD;
			msg = "字段id(fieldId)不能为空！";
			rs.setCode(code);
			rs.setMsg(msg);
			log.info(code+": "+msg);
			return rs;
		}
		try {
			fieldInfDao.deleteTblFieldsInf(tblFieldInf);
			msg = CodeMsgConstants.DELETE_SUCCESS_MSG;
			code = CodeMsgConstants.DELETE_SUCCESS;
		}catch (Exception e) {
			log.error("删除异常: "+e.getMessage());
			msg = CodeMsgConstants.DELETE_FAILD_MSG;
			code = CodeMsgConstants.DELETE_FAILD;
		}
		log.info(code+": "+msg);
		rs.setCode(code);
		rs.setMsg(msg);
		return rs;
	}

	@Override
	public CodeMessageResult<TblFieldInf> createTblFieldsInf(TblFieldInf tblFieldInf) {
		CodeMessageResult<TblFieldInf> rs = new CodeMessageResult<TblFieldInf>();
		String code ="";
		String msg = "";
		
		if(tblFieldInf.getTableId()==null) {
			code = CodeMsgConstants.DELETE_FAILD;
			msg = "表id(tableId)不能为空！";
			rs.setCode(code);
			rs.setMsg(msg);
			log.info(code+": "+msg);
			return rs;
		}
		
		if(StringUtils.isEmpty(tblFieldInf.getFieldId())) {
			code = CodeMsgConstants.DELETE_FAILD;
			msg = "字段id(fieldId)不能为空！";
			rs.setCode(code);
			rs.setMsg(msg);
			log.info(code+": "+msg);
			return rs;
		}
		
		try {
			fieldInfDao.createTblFieldsInf(tblFieldInf);
			msg = CodeMsgConstants.CREATE_SUCCESS_MSG;
			code = CodeMsgConstants.CREATE_SUCCESS;
		}catch (Exception e) {
			log.error("新增异常: "+e.getMessage());
			msg = CodeMsgConstants.CREATE_FAILD_MSG;
			code = CodeMsgConstants.CREATE_FAILD;
		}
		log.info(code+": "+msg);
		rs.setCode(code);
		rs.setMsg(msg);
		return rs;
	}

	/**
	 * 解析查询字段
	 * 2018-12-25 14:28:12
	 * @param fields
	 * @return
	 */
	private List<String> parseFields(String fields) {
		List<String> queryFields = new ArrayList<String>();
		
		if(StringUtils.isEmpty(fields)) {
			return queryFields;
		}
		String field[] = fields.split(",");
		for (int i = 0; i < field.length; i++) {
			queryFields.add(field[i]);
		}
		return queryFields;
	}
	

}
