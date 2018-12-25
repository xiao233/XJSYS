package com.java.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.FieldInfDao;
import com.java.dao.jdbc.CommonSQLOperationUtils;
import com.java.entites.SearchParamObject;
import com.java.entites.TblFieldInf;
import com.java.service.FieldInfService;
import com.java.utils.StringUtils;
import com.java.utils.TableToEntityClassUtils;
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
	public void exportTableInf(HttpServletResponse response, SearchParamObject searchParamObject) {
		
		Map<String,String> searchObj = searchParamObject.getSearchObj();
		String tableName = searchObj.get("exportTableName");
		String fileName = searchObj.get("exportFileName");
		String fileType = searchObj.get("exportFileType");
		String fields = searchObj.get("selectFields");
		
		Map<String,Object> params = new HashMap<String,Object>();
		List<String> queryFields = parseFields(fields);
		
		List<?> queryResult = CommonSQLOperationUtils.query(tableName, queryFields, params,
				TableToEntityClassUtils.getClass(tableName));
		
		log.info("查询到表'"+tableName+"'"+queryResult.size()+"条数据！");
		WriteInFileUtils.writeInFile(queryResult,fileName+fileType,response);
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
