package com.java.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;


/**
 * 获取与数据库的连接
 * @author xjl
 * 2018-12-25 08:58:45
 */
public class ConnectionManager {
	
	private static Logger log = Logger.getLogger(ConnectionManager.class);
	
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/userdb?useUnicode=true&characterEncoding=utf8";
	private static String USERNAME = "root";
	private static String PASSWORD  = "123456";
	private static Connection conn ;
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			log.error("获取连接失败： "+e.getMessage());
		}
	}
	
	
	public static Connection getConnection() {
		try {
			conn =  DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}  catch (SQLException e) {
			log.error("获取连接失败："+e.getMessage());
		}
		return conn;
	}
	
	public static void closeConnection(Connection con,Statement st,ResultSet rs) {
		
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error("关闭数据库连接失败： "+e.getMessage());
			}
			
			try {
				if(st!=null) {
					st.close();
				}
			} catch (SQLException e) {
				log.error("关闭数据库连接失败： "+e.getMessage());
			}
			
			try {
				if(con!=null) {
					con.close();
				}
			} catch ( SQLException e) {
				log.error("关闭数据库连接失败： "+e.getMessage());
			}
	}
	
}
