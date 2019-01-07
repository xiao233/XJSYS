package com.java.entites;

import java.io.Serializable;

/**
 * 下拉框信息实体类
 * @author xjl
 * 2018-12-24 09:04:56
 */
public class ViewDropCfg implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dropName; //下拉框名称
	
	private String cfgValue; //值
	
	private String cfgKey; //建

	public String getDropName() {
		return dropName;
	}

	public void setDropName(String dropName) {
		this.dropName = dropName;
	}

	public String getCfgValue() {
		return cfgValue;
	}

	public void setCfgValue(String cfgValue) {
		this.cfgValue = cfgValue;
	}

	public String getCfgKey() {
		return cfgKey;
	}

	public void setCfgKey(String cfgKey) {
		this.cfgKey = cfgKey;
	}
	
	
}
