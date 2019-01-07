package com.java.entites;

import java.io.Serializable;

public class TblFieldInf implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tableName;
	
	private Integer tableId;
	
	private String fieldId;
	
	private String fieldName;
	
	private String fieldNameCn;
	
	private String isShow;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldNameCn() {
		return fieldNameCn;
	}

	public void setFieldNameCn(String fieldNameCn) {
		this.fieldNameCn = fieldNameCn;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	
}
