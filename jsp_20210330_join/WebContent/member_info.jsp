m<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%
	String id = null;
	// 입력받은 id값이 null이거나 admin이 아니라면
	if((session.getAttribute("id")==null) ||
		(!((String)session.getAttribute("id")).equals("admin"))){
		
		out.println("<script>");
		out.println("location.href='loginForm.jsp'"); // loginForm.jsp 페이지로 이동한다
		out.println("</script>");
	}
	
	String info_id = request.getParameter("id");
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try {
			Context init = new InitialContext();
			DataSource ds = 
				(DataSource) init.lookup("java:/comp/env/jdbc/scott");
			conn = ds.getConnection();
			
			pstmt=conn.prepareStatement("SELECT * FROM member WHERE id=?");
			pstmt.setString(1,info_id);
			rs=pstmt.executeQuery();
			rs.next();
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원관리 시스템 관리자모드(회원 정보 보기)</title>
<style>
	table{
		margin : auto;
		width : 400px;
		border : 1px solid gray;
		text-align: center;
	}
</style>
</head>
<body>
<table>
	<tr>
		<td>아이디 : </td>
		<td><%=rs.getString("id") %></td>
	</tr>
	<tr>
		<td>비밀번호 : </td>
		<td><%=rs.getString("pass") %></td>
	</tr>
	<tr>
		<td>이름 : </td>
		<td><%=rs.getString("name") %></td>
	</tr>
	<tr>
		<td>나이 : </td><td><%=rs.getString("age") %></td>
	</tr>
	<tr>
		<td>성별 : </td><td><%=rs.getString("gender") %></td>
	</tr>
	<tr>
		<td>이메일 주소 : </td>
		<td><%=rs.getString("email") %></td>
	</tr>
	<tr>
		<!-- 회원목록을 보는 페이지로 링크를 생성 -->
		<td colspan=2><a href="member_list.jsp">리스트로 돌아가기</a></td>
	</tr>
</table>
</body>
</html>
