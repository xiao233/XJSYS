package com.java.service;

import com.java.entites.CodeMessageResult;
import com.java.entites.TableInf;

public interface TableInfService {
	/**
	 * ��ѯ���б���Ϣ
	 * 2018-12-13 17:38:07
	 * @return
	 */
	public CodeMessageResult<TableInf> queryAll();
}
