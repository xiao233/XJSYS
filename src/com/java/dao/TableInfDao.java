package com.java.dao;

import java.util.List;

import com.java.entites.TableInf;

public interface TableInfDao {
	/**
	 * 查询所有表信息
	 * 2018-12-13 17:38:07
	 * @return
	 */
	public List<TableInf> queryAll();
}
