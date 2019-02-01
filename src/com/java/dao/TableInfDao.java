package com.java.dao;

import com.java.entites.TblTableInf;

public interface TableInfDao extends BaseDao{
	
	/**
	 * ���ݱ�id���±���Ϣ
	 * 2019-02-01 09:01:57
	 * @param tableInf
	 * @return
	 */
	public  void updateTableInfById(TblTableInf tableInf);
	
	/**
	 * ɾ������Ϣ
	 * 2019-02-01 09:03:31
	 * @param tableInf
	 * @return
	 */
	public void deleteTableInf(TblTableInf tableInf);

	/**
	 * ��ӱ���Ϣ
	 * 2019-02-01 11:38:46
	 * @param tableInf
	 */
	public void createTableInf(TblTableInf tableInf);
}
