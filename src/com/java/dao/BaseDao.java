package com.java.dao;

import java.util.List;

import com.java.entites.TblTableInf;

/**
 * 通用查询
 * @author xjl
 * 2019-01-28 11:05:13
 */
public interface BaseDao {
	
	/**
	 * 分页查询所有信息
	 * 2019-01-28 11:02:08
	 * @param <T>
	 * @param tableInf
	 * @return
	 */
	public <T> List<T> queryPageAll(T t);
	
	/**
	 * 查询总数
	 * 2019-01-28 11:07:07
	 * @param t
	 * @return
	 */
	public <T> int queryCount(T t);
	
	/**
	 * 查询所有表信息
	 * 2018-12-13 17:38:07
	 * @return
	 */
	public <T> List<T> queryAll(T t);
}
