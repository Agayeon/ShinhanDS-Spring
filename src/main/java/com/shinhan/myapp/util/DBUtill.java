package com.shinhan.myapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.SQLDataException;

public class DBUtill {
	// Connection pooling 이용(WAS가 미리 Connection을 만들고 일정만큼 유지, 필요시 Connection얻어서 사용)
	public static Connection getConnection2() {
		Connection conn = null;
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// Oracle DB 연결을 Utill로 만들었음
	public static Connection getConnection() {
		Connection conn = null;
		// 강사 pc : 192.168.0.18
		// 내 pc : 192.168.0.240
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "hr", userpass = "1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, userid, userpass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	
	// DB 연결 시 사용한 자원 해제
	public static void dbDisconnect(
			Connection conn, Statement st, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String buildDebugSQL(String sqlTemplate, Object... params) {
	    for (Object param : params) {
	        String val = (param instanceof String) ? "'" + param + "'" : String.valueOf(param);
	        sqlTemplate = sqlTemplate.replaceFirst("\\?", val);
	    }
	    return sqlTemplate;
	}
}
