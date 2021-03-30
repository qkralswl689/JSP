package dbcp_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcMariaTest {

	public static void main(String[] args) throws SQLException {

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String driver="org.mariadb.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/bigdb";
		String id="";
		
		Boolean connect=false;
		
		try{
			Class.forName(driver);
			conn=DriverManager.getConnection(url,"java","java");
			String sql ="SELECT empno FROM emp";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {						
				id = rs.getString(1);		
				System.out.println(id+" ");
			}
					
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		
	} // main

}