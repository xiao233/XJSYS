package com.java.entites;
/**
 * 查询参数对象
 * @author xjl
 * 2018-12-21 15:34:18
 */

import java.util.HashMap;
import java.util.Map;

public class SearchParamObject {
	Map<String,String> searchObj = new HashMap<String,String>();

	public Map<String,String> getSearchObj() {
		return searchObj;
	}

	public void setSearchObj(Map<String,String> searchObj) {
		this.searchObj = searchObj;
	}
}
