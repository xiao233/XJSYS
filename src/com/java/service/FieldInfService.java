package com.java.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.entites.TblFieldInf;

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
}
