<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%
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
			out.println(id+" ");
		}
				
	} catch(Exception e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	} finally {
		rs.close();
		pstmt.close();
		conn.close();
	}
%>
<html>
<head>
<title>JDBC 연동 테스트 예제</title>
</head>
<body>
</body>
</html>
