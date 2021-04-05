package com.javateam.jdbc.member.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * DB 연결/해제 유틸리티(Utility) <br>
 * <br>
 * 사용 용례) <br>
 * DB 연결시 : DbUtil.connect(); DB 연결 해제시 : DbUtil.close(con, pstmt, rs); <br>
 * 
 * @author javateam
 * @version 1.0
 */
public class DbUtil {

	/** DB 연결 정보 */
	/** JDBC 드라이버(driver) */
	static final String DRIVER = "org.mariadb.jdbc.Driver";
	/** JDBC URL */
	static final String URL = "jdbc:mysql://localhost:3306/bigdb";
	/** DB account(ID) */
	static final String USER_ID = "java";
	/** DB account(PW) */
	static final String USER_PW = "java";

	/**
	 * DB 연결 <br>
	 * <br>
	 * 용례) DbUtil.connect(); <br>
	 * 
	 * @return DB 연결 객체
	 */
	public static Connection connect() {

		/** DB 연결 객체 */
		Connection con = null;

		try {

			// JDBC driver 등록
			Class.forName(DRIVER);

			// JDBC URL, ID, PW를 이용하여 연결 객체 생성
			try {

				con = DriverManager.getConnection(URL, USER_ID, USER_PW);

			} catch (SQLException e) {
				System.err.println("DB 연결에 실패하였습니다.");
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			System.err.println("JDBC 드라이버 로딩에 실패하였습니다.");
			e.printStackTrace();
		}

		return con;
	} //

	/**
	 * DB 연결 해제(자원 반납) <br>
	 * <br>
	 * 용례) DbUtil.close(con, pstmt, rs); // select DbUtil.close(con, pstmt, null);
	 * // insert, update, delete <br>
	 * 
	 * @param con  DB 연결 객체
	 * @param stmt SQL 처리 객체
	 * @param rs   SQL 결과셋 객체
	 */
	 
	 public static void close(Connection con, Statement stmt, ResultSet rs) {
	  
		  try {
		  
			  if (rs != null) { 
				  rs.close(); 
				  System.out.println("SQL 결과셋을 반납하였습니다."); 
			  } //
			  // if (!rs.isClosed()) rs.close(); 
			  // if (rs.isClosed() == false) rs.close();
			  
			  if (stmt != null) { 
				  stmt.close(); 
				  System.out.println("SQL 처리객체를 반납하였습니다."); 
			  } //
			  // if (stmt.isClosed() == false) rs.close(); 
			  if (con != null) { 
				  con.close();
				  System.out.println("DB 연결 객체를 반납하였습니다."); 
			  } 
			  // if (con.isClosed() == false) rs.close();
		  
		  } catch (SQLException e) { 
			  System.err.println("DB 자원 반납에 실패하였습니다.");
			  e.printStackTrace(); 
	  	  } //
	 
	} 
	
	/*
	public static String close(Connection con, Statement stmt, ResultSet rs) {
		  
		  String result = "";
		  
		  try {
		  
			  if (rs != null) { 
				  rs.close(); 
				  System.out.println("SQL 결과셋을 반납하였습니다."); 
				  result += "SQL 결과셋을 반납하였습니다.\n";
			  } //
			  
			  if (stmt != null) { 
				  stmt.close(); 
				  System.out.println("SQL 처리객체를 반납하였습니다."); 
				  result += "SQL 처리객체를 반납하였습니다.";
			  } //
			  if (con != null) { 
				  con.close();
				  System.out.println("DB 연결 객체를 반납하였습니다.");
				  result += "DB 연결 객체를 반납하였습니다.";
			  } 
		  
		  } catch (SQLException e) { 
			  System.err.println("DB 자원 반납에 실패하였습니다.");
		  	  e.printStackTrace(); 
		  } //
	 
	}
	*/
	/*
	public static int close(Connection con, Statement stmt, ResultSet rs) {
		  
		  int result = 0;
		  
		  try {
		  
			  if (rs != null) { 
				  rs.close(); 
				  System.out.println("SQL 결과셋을 반납하였습니다."); 
				  result = 1;
			  } //
			  
			  if (stmt != null) { 
				  stmt.close(); 
				  System.out.println("SQL 처리객체를 반납하였습니다."); 
				  result = 2;
			  } //
			  if (con != null) { 
				  con.close();
				  System.out.println("DB 연결 객체를 반납하였습니다.");
				  result = 3;
			  } 
		  
		  } catch (SQLException e) { 
			  System.err.println("DB 자원 반납에 실패하였습니다.");
		  	  e.printStackTrace(); 
		  } //
	 
		  return result;
	}
	*/
	
	/*
	public static boolean close(Connection con, Statement stmt, ResultSet rs) {
		
		boolean result = false;

		try {

			if (rs != null) {
				rs.close();
				// System.out.println("SQL 결과셋을 반납하였습니다.");
				result = true;
			}

			if (stmt != null) {
				stmt.close();
				System.out.println("SQL 처리객체를 반납하였습니다.");
				result = true;
			}

			if (con != null) {
				con.close();
				System.out.println("DB 연결 객체를 반납하였습니다.");
				result = true;
			}

		} catch (SQLException e) {
			System.err.println("DB 자원 반납에 실패하였습니다.");
			e.printStackTrace();
			result = false;
		}

		return result;
	} //
	*/

}