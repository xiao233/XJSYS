package com.java.dao;

import java.util.List;

import com.java.entites.TblTableInf;

/**
 * ͨ�ò�ѯ
 * @author xjl
 * 2019-01-28 11:05:13
 */
public interface BaseDao {
	
	/**
	 * ��ҳ��ѯ������Ϣ
	 * 2019-01-28 11:02:08
	 * @param <T>
	 * @param tableInf
	 * @return
	 */
	public <T> List<T> queryPageAll(T t);
	
	/**
	 * ��ѯ����
	 * 2019-01-28 11:07:07
	 * @param t
	 * @return
	 */
	public <T> int queryCount(T t);
	
	/**
	 * ��ѯ���б���Ϣ
	 * 2018-12-13 17:38:07
	 * @return
	 */
	public <T> List<T> queryAll(T t);
}
