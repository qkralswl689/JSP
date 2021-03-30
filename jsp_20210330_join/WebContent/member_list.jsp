<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%
	String id = null;
	
	// 전달받은id값이 null이거나 admin이 아니라면 
	if((session.getAttribute("id") == null) ||
		(!((String)session.getAttribute("id")).equals("admin"))) {
		out.println("<script>");
		out.println("location.href='loginForm.jsp'"); // loginForm.jsp 페이지로 이동
		out.println("</script>");
	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try{
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:/comp/env/jdbc/scott");
		con = ds.getConnection();
		pstmt=con.prepareStatement("SELECT * FROM member");
		rs=pstmt.executeQuery();
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원관리 시스템 관리자모드(회원 목록 보기)</title>
<style>
	table{
		margin : auto;
		width : 400px;
		border : 1px solid gray;
		text-align: center;
	}
	.td_title{
		font-weight: bold;
		font-size: x-large;
	}
</style>
</head>
<body>
<table>
	<tr><td colspan=2 class="td_title">회원 목록</td></tr>
	<!-- member 테이블에 존재하는 레코드 수 만큼 회원 정보를 출력 -->
	<%while(rs.next()){%> 
	<tr>
		<td>
			<!-- 회원아이디 클릭시 해당 회원의 정보를 보여주는 member_info.jsp페이지로 이동하는 링크 생성
				 정보를 보여줄 회원을 판단하기 위해 id값을 파라미터로 전송한다 -->
			<a href="member_info.jsp?id=<%=rs.getString("id") %>">
				<!-- 회원의 id 컬럼 값을 출력 -->
				<%=rs.getString("id") %> 
			</a>
		</td>
		<td>
			<!-- 회원정보를 삭제하는 페이지인 member_delete.jsp 페이지로 이동하는 링크 설정,
					삭제할 회원 판단위해 id값을 파라미터로 전송 -->
		    <a href="member_delete.jsp?id=<%=rs.getString("id") %>">삭제</a>
		</td>
	</tr>
	<%} %>
</table>
</body>
</html>
</html>