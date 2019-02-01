package com.java.service;

import com.java.entites.TblTableInf;
import com.java.entites.common.CodeMessageResult;

public interface TableInfService {
	/**
	 * ��ѯ���б���Ϣ
	 * 2018-12-13 17:38:07
	 * @return
	 */
	public CodeMessageResult<TblTableInf> queryAll(TblTableInf tableInf);

	/**
	 * ��ҳ��ѯ���б���Ϣ
	 * 2019-01-28 11:02:08
	 * @param tableInf
	 * @return
	 */
	public CodeMessageResult<TblTableInf> queryPageAll(TblTableInf tableInf);

	/**
	 * ���ݱ�id���±���Ϣ
	 * 2019-02-01 09:01:57
	 * @param tableInf
	 * @return
	 */
	public CodeMessageResult<TblTableInf> updateTableInfById(TblTableInf tableInf);
	
	/**
	 * ɾ������Ϣ
	 * 2019-02-01 09:03:31
	 * @param tableInf
	 * @return
	 */
	public CodeMessageResult<TblTableInf> deleteTableInf(TblTableInf tableInf);

	public CodeMessageResult<TblTableInf> createTableInfById(TblTableInf tableInf);

}
