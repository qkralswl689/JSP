package com.javateam.member.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTest {

	// oracle spec               
	final String oracleDriver = "oracle.jdbc.OracleDriver"; // 드라이버
	final String oracleURL = "jdbc:oracle:thin:@localhost:1521:xe"; // URL
	final String oracleID = "mingki"; // 계정 아이디
	final String oraclePW = "1234"; // 패쓰워드

	// DB 연결(connection)
	public Connection getConnection() {

		Connection con = null;

		try { // reflection
			Class.forName(oracleDriver);
			con = DriverManager.getConnection(oracleURL, oracleID, oraclePW);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

	public void getTable() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employees"; // SQL 

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("first_name"));
			}

			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		new JdbcTest().getTable();
	}

}
