package com.java.dao;

import java.util.List;

import com.java.entites.ViewDropCfg;

public interface ViewDropCfgDao {
	/**
	 * 通过下拉框名获取下拉框信息
	 * 2018-12-24 09:08:47
	 * @param dropName
	 * @return
	 */
	List<ViewDropCfg> getDropCfg(String dropdownName);
}
