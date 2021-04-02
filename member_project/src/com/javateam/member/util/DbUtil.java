/**
 * 
 */
package com.javateam.member.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * DB 연결/해제(자원 반납) 유틸리티
 * 
 * @author javateam
 * @version 1.0
 */
public class DbUtil {
	
	/**
 	 * DB 연결 : JDBC 직접 연결 방식
	 * 
	 * usage) DbUtil.connect("sample", "sample");
	 *  
	 * @param id 계정 아이디
	 * @param pw 계정 패쓰워드
	 * @return DB 연결 객체
	 */
	public static Connection connect(String id, String pw) {
		
		Connection con = null; // DB 연결 객체
		// final String driver = "oracle.jdbc.driver.OracleDriver";
		final String driver = "oracle.jdbc.OracleDriver"; // JDBC 드라이버
		final String url = "jdbc:oracle:thin:@localhost:1521:xe"; // JDBC URL
		
		try {
				// JDBC driver 검색/로딩(loading)
				Class.forName(driver);
				// DB 연결 객체 반환(DB 연결)
				con = DriverManager.getConnection(url, id, pw);
				
		} catch (ClassNotFoundException e) {
			System.out.println("DbUtil connect CNFE :");
			e.printStackTrace();
		} catch(SQLException e) {
			System.out.println("DbUtil connect SE : ");
			e.printStackTrace();
		}
		
		return con;
	} //
	
	/**
 	 * DB 연결 : DBCP/JNDI 방식
	 * 
	 * usage) DbUtil.connect();
	 *  
	 * @return DB 연결 객체
	 */
	public static Connection connect() {
		
		Connection con = null; // DB 연결 객체
		
		try {
				Context initContext = new InitialContext();
				DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/xe");
				con=ds.getConnection();
				
		} catch (NamingException e) {
			System.out.println("DbUtil connect CNFE :");
			e.printStackTrace();
		} catch(SQLException e) {
			System.out.println("DbUtil connect SE : ");
			e.printStackTrace();
		}
		
		return con;
	} //
	
	/**
	 * DB 연결 해제(자원 반납)
	 * 
	 * @param con DB 연결 객체
	 * @param pstmt SQL 처리 객체
	 * @param rs SQL 처리 결과셋
	 */
	public static void close(Connection con,
							 PreparedStatement pstmt,
							 ResultSet rs) {
		try {
				if (rs != null) rs.close();
				// if (!rs.isClosed()) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
				
		} catch(SQLException e) {
			System.out.println("DbUtil close SE : ");
			e.printStackTrace();
		} // 
				
	} //
	
}