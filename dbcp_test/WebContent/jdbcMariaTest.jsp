<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<%
	Connection conn=null;
	
	// MariaDSB Connector/J : https://mariadb.com/kb/en/about-mariadb-connector-j/ 
	String driver="org.mariadb.jdbc.Driver";
	String url="jdbc:mariadb://localhost:3306/bigdb";
	
	Boolean connect=false;
	
	try{
		Class.forName(driver);
		//conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/bigdb?user=root&password=myPassword");
		conn=DriverManager.getConnection(url,"java","java");
		
		connect=true;
		
		conn.close();
	}catch(Exception e){
		connect=false;
		e.printStackTrace();
	}
%>
<html>
<head>
<title>JDBC 연동 테스트 예제</title>
</head>
<body>
<h3>
<%if(connect==true){ %>
	연결되었습니다.
<%}else{ %>
	연결에 실패하였습니다.
<%} %>
</h3>
</body>
</html>
