package com.java.dao.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.java.entites.UserInf;

public class JDBCTest {
	@Test
	public void conTest() {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("user_name", "xiao");
		List<String> queryFields = new ArrayList<String>();
		queryFields.add("user_seq");
		queryFields.add("user_name");
		queryFields.add("user_paw");
		CommonSQLOperationUtils.query("user_inf", queryFields , params, UserInf.class);
	}
	
	@Test
	public void sqlTest() {
		String sql = " select drop_name,drop_remark from tbl_drop_inf";
		//showList(CommonSQLOperationUtils.queryMap(sql));
		//showList(CommonSQLOperationUtils.getTblColumnsALL("tbl_drop_inf"));
		
		//showListObj(CommonSQLOperationUtils.getTblColumnsALLObj("tbl_drop_inf"));
		
		//showListObj(CommonSQLOperationUtils.getTblTablesALLObj("tbl_drop_inf"));
		
		showListObj(CommonSQLOperationUtils.getAllTblTablesALLObj());
	}
	
	private void showListObj(List<Object> tblConfigsObject) {
		// TODO Auto-generated method stub
		for (Object object : tblConfigsObject) {
			System.out.println(object.toString());
			System.out.println("=========================================================\n");
		}
	}

	public void showList( List<Map<String,Object>> list) {
		for (Map<String, Object> map : list) {
			System.out.println("=========================================================");
			showMap(map);
			System.out.println("\n");
		}
	}

	private void showMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		for(Entry<String, Object> entry:map.entrySet()) {
			System.out.println(entry.getKey()+"--"+entry.getValue());
		}
	}
}
