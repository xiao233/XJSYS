package com.java.entites;

import java.io.Serializable;

/**
 * ��������Ϣʵ����
 * @author xjl
 * 2018-12-24 09:04:56
 */
public class ViewDropCfg implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dropName; //����������
	
	private String cfgValue; //ֵ
	
	private String cfgKey; //��

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
