<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%
	String id=null;
	/* session에 ID라는 이름의 속성값이 존재하지 않거나 admin이 아니라면
		loginForm.jsp페이지로 이동 처리*/
	if ((session.getAttribute("id")==null) || 
	  (!((String)session.getAttribute("id")).equals("admin"))) {
		out.println("<script>");
		out.println("location.href='loginForm.jsp'");
		out.println("</script>");
	}
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try {
			Context init = new InitialContext();
			DataSource ds = 
				(DataSource) init.lookup("java:/comp/env/jdbc/scott");
			conn = ds.getConnection();
			/* member테이블의 모든 칼럼값을 가져오는 select문으로 prepareStatement 객체 생성 */
			pstmt=conn.prepareStatement("SELECT * FROM member");
			rs=pstmt.executeQuery();
	}catch(Exception e){
		e.printStackTrace();
	}

%>
<html>
<head>
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
