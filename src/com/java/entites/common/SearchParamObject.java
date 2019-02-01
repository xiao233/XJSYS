package com.java.entites.common;
/**
 * 查询参数对象
 * @author xjl
 * 2018-12-21 15:34:18
 */

import java.util.HashMap;
import java.util.Map;

public class SearchParamObject {
	/**
	 * 每页大小
	 */
	private int pageSize;
	
	/**
	 * 当前页,从1开始
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
