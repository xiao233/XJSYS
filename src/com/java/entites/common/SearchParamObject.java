package com.java.entites.common;
/**
 * ��ѯ��������
 * @author xjl
 * 2018-12-21 15:34:18
 */

import java.util.HashMap;
import java.util.Map;

public class SearchParamObject {
	/**
	 * ÿҳ��С
	 */
	private int pageSize;
	
	/**
	 * ��ǰҳ,��1��ʼ
	 */
	private int pageCurr;
	
	Map<String,String> searchObj = new HashMap<String,String>();

	public Map<String,String> getSearchObj() {
		return searchObj;
	}

	public void setSearchObj(Map<String,String> searchObj) {
		this.searchObj = searchObj;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCurr() {
		return pageCurr;
	}

	public void setPageCurr(int pageCurr) {
		this.pageCurr = pageCurr;
	}
	
}
