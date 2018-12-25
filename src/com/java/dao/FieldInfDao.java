package com.java.dao;

import java.util.List;

import com.java.entites.TblFieldInf;

/**
 * 获取字段信息
 * @author xjl
 * 2018-12-24 13:46:59
 */
public interface FieldInfDao {
	
	List<TblFieldInf> getFieldInf(TblFieldInf tblFieldInf);
	
	List<TblFieldInf> getTableFieldInf(TblFieldInf tblFieldInf);
}
