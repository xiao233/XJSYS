package com.java.dao.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
