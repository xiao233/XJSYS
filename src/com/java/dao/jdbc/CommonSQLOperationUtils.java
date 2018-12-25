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
 * ͨ��sql����
 * @author xjl
 * 2018-12-25 09:15:22
 */
public class CommonSQLOperationUtils<T> {
	
	private static Logger log = Logger.getLogger(CommonSQLOperationUtils.class);
	
	/**
	 * ͨ�ò�ѯ����
	 * 2018-12-25 11:17:46
	 * @param tableName ����
	 * @param queryFields ��ѯ�ֶ�
	 * @param params ��ѯ����
	 * @param tableClass ��Ӧʵ�����
	 * @return
	 */
	public static <T> List<T> query(String tableName,List<String> queryFields,
			Map<String,Object> params,Class<T> tableClass){
		Connection con = ConnectionManager.getConnection();
		
		StringBuilder sql = new StringBuilder("");
		
		sql.append("SELECT ");
		
		//��ȡ��ѯ�ֶ�
		for (int i = 0; i < queryFields.size(); i++) {
			sql.append(queryFields.get(i));
			if(i!=queryFields.size()-1) {
				sql.append(", ");
			}
		}
		sql.append(" FROM ");
		sql.append(tableName);
		sql.append(" WHERE 1=1 ");
		
		//��ȡ��ѯ������ռλ��
		List<Object> paramList = new ArrayList<Object>();
		Set<Entry<String, Object>> condition = params.entrySet();
		for (Entry<String, Object> entry : condition) {
			sql.append(" AND "+entry.getKey()+" = ?");
			paramList.add(entry.getValue());
		}
		
		log.info("��ѯ����: "+sql);
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<T> result = null;
		try {
			pst = con.prepareStatement(sql.toString());
			
			//ռλ
			for (int i = 0; i < paramList.size(); i++) {
				pst.setObject(i+1, paramList.get(i));
			}
			
			//ִ��
			rs = pst.executeQuery();
			List<Map<String,Object>> queryList = new ArrayList<Map<String,Object>>();
			
			//������ѯ���
			while(rs.next()) {
				Map<String,Object> obj = new HashMap<String,Object>();
				for (String field : queryFields) {
					obj.put(field, rs.getObject(field));
				}
				queryList.add(obj);
			}
			
			//����ѯ���ת������Ӧʵ����
			result = ConvertToObjectUtils.mapToObject(queryList,tableClass);
			
		} catch (SQLException e) {
			log.error("��ѯ�쳣: "+e.getMessage());
		} finally {
			ConnectionManager.closeConnection(con, pst, rs);
		}
		
		return result;
	}

	
}
