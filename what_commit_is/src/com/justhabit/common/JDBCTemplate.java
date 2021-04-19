package com.justhabit.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	
	public static Connection getConnection() {
		
		Connection con = null;
		
		Properties prop = new Properties();
		
		try {
			
			prop.load(new FileReader("config/connection-info.properties"));
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, prop);
			
			/* auto commit default(=true) changing to false
			 * 그렇다면, 언제 commit? 언제 rollback?
			 *  */
			con.setAutoCommit(false);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return con;
		
	}
	
	public static void close(Connection con) {
		
		try {
			if(con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/* 데이터를 저장을 위한 Connection클래스의 commit()메소드를 이용 */
	public static void commit(Connection con) {
		
		try {
			if(con != null && !con.isClosed())
				con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/* 데이터의 자동저장을 막기위한 Connection클래스의 rollback()메소드를 이용 */
	public static void rollback(Connection con) {
		
		try {
			if(con != null && !con.isClosed())
				con.rollback();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
