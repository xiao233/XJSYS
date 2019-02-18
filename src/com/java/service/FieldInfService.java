package com.java.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.entites.TblFieldInf;
import com.java.entites.common.CodeMessageResult;

/**
 * 获取字段信息
 * @author xjl
 * 2018-12-24 14:00:29
 */
public interface FieldInfService {
	/**
	 * 通过表name获取此表的所有字段信息
	 * 2018-12-24 14:01:21
	 * @param tableName
	 * @return
	 */
	List<TblFieldInf> getFieldInfByTableName(String tableName);

	/**
	 * 导出表信息
	 * 2018-12-25 14:07:43
	 * @param response
	 * @param searchParamObject
	 */
	void exportTableInf(HttpServletResponse response, HttpServletRequest request);

	/**
	 * 更新表信息
	 * 2019-02-18 14:26:46
	 * @param tblFieldInf
	 * @return
	 */
	public CodeMessageResult<TblFieldInf> updTblFieldsInf(TblFieldInf tblFieldInf);

	/**
	 * 根据fieldId删除字段信息
	 * 2019-02-18 14:28:38
	 * @param tblFieldInf
	 * @return
	 */
	public CodeMessageResult<TblFieldInf> deleteTblFieldsInf(TblFieldInf tblFieldInf);

	/**
	 * 增加表字段信息
	 * 2019-02-18 14:30:12
	 * @param tblFieldInf
	 * @return
	 */
	public CodeMessageResult<TblFieldInf> createTblFieldsInf(TblFieldInf tblFieldInf);
}
