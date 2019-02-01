package com.java.service;

import com.java.entites.TblTableInf;
import com.java.entites.common.CodeMessageResult;

public interface TableInfService {
	/**
	 * 查询所有表信息
	 * 2018-12-13 17:38:07
	 * @return
	 */
	public CodeMessageResult<TblTableInf> queryAll(TblTableInf tableInf);

	/**
	 * 分页查询所有表信息
	 * 2019-01-28 11:02:08
	 * @param tableInf
	 * @return
	 */
	public CodeMessageResult<TblTableInf> queryPageAll(TblTableInf tableInf);

	/**
	 * 根据表id更新表信息
	 * 2019-02-01 09:01:57
	 * @param tableInf
	 * @return
	 */
	public CodeMessageResult<TblTableInf> updateTableInfById(TblTableInf tableInf);
	
	/**
	 * 删除表信息
	 * 2019-02-01 09:03:31
	 * @param tableInf
	 * @return
	 */
	public CodeMessageResult<TblTableInf> deleteTableInf(TblTableInf tableInf);

	public CodeMessageResult<TblTableInf> createTableInfById(TblTableInf tableInf);

}
