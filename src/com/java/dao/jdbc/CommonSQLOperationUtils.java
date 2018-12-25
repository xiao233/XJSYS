package com.java.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.java.utils.ConvertToObjectUtils;


/**
 * 通用sql操作
 * @author xjl
 * 2018-12-25 09:15:22
 */
public class CommonSQLOperationUtils<T> {
	
	private static Logger log = Logger.getLogger(CommonSQLOperationUtils.class);
	
	/**
	 * 通用查询操作
	 * 2018-12-25 11:17:46
	 * @param tableName 表名
	 * @param queryFields 查询字段
	 * @param params 查询条件
	 * @param tableClass 对应实体的类
	 * @return
	 */
	public static <T> List<T> query(String tableName,List<String> queryFields,
			Map<String,Object> params,Class<T> tableClass){
		Connection con = ConnectionManager.getConnection();
		
		StringBuilder sql = new StringBuilder("");
		
		sql.append("SELECT ");
		
		//获取查询字段
		for (int i = 0; i < queryFields.size(); i++) {
			sql.append(queryFields.get(i));
			if(i!=queryFields.size()-1) {
				sql.append(", ");
			}
		}
		sql.append(" FROM ");
		sql.append(tableName);
		sql.append(" WHERE 1=1 ");
		
		//获取查询条件和占位符
		List<Object> paramList = new ArrayList<Object>();
		Set<Entry<String, Object>> condition = params.entrySet();
		for (Entry<String, Object> entry : condition) {
			sql.append(" AND "+entry.getKey()+" = ?");
			paramList.add(entry.getValue());
		}
		
		log.info("查询条件: "+sql);
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<T> result = null;
		try {
			pst = con.prepareStatement(sql.toString());
			
			//占位
			for (int i = 0; i < paramList.size(); i++) {
				pst.setObject(i+1, paramList.get(i));
			}
			
			//执行
			rs = pst.executeQuery();
			List<Map<String,Object>> queryList = new ArrayList<Map<String,Object>>();
			
			//遍历查询结果
			while(rs.next()) {
				Map<String,Object> obj = new HashMap<String,Object>();
				for (String field : queryFields) {
					obj.put(field, rs.getObject(field));
				}
				queryList.add(obj);
			}
			
			//将查询结果转换成相应实体类
			result = ConvertToObjectUtils.mapToObject(queryList,tableClass);
			
		} catch (SQLException e) {
			log.error("查询异常: "+e.getMessage());
		} finally {
			ConnectionManager.closeConnection(con, pst, rs);
		}
		
		return result;
	}

	
}
