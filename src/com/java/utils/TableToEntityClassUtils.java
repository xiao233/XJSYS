package com.java.utils;

import java.util.HashMap;
import java.util.Map;

import com.java.constants.TableConstants;
import com.java.entites.TblFieldInf;
import com.java.entites.TblTableInf;
import com.java.entites.UserInf;

/**
 * 获取表名对应的实体类名
 * @author xjl
 * 2018-12-25 14:14:58
 */
public class TableToEntityClassUtils {
	
	private static Map<String,Class<?>> tableClassMap = null;
	
	static{
		tableClassMap = new HashMap<String,Class<?>>();
		tableClassMap.put(TableConstants.USER_INF, UserInf.class);
		tableClassMap.put(TableConstants.TBL_TABLE_INF, TblTableInf.class);
		tableClassMap.put(TableConstants.TBL_FIELD_INF, TblFieldInf.class);
	}
	public static Class<?> getClass(String tableName) {
		
		return tableClassMap.get(tableName);
	}
}
