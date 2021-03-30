<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String id=null;

	/* 세션에 ID가 등록되어있으면(로그인상태) 세션에 저장된 ID 값을 변수에 저장한다 */
	if (session.getAttribute("id")!=null){
		id=(String)session.getAttribute("id");
	}else{/* 아니면 로그인 폼으로 이동한다 */
		out.println("<script>");
		out.println("location.href='loginForm.jsp'");
		out.println("</script>");
	}
%>
<html>
<head>
<title>회원관리 시스템 메인 페이지</title>
</head>
<body>
<h3><%=id %> 로 로그인하셨습니다.</h3>
<!-- ID가 admin(관리자)인 경우 관리자 모드로 접속하는 링크 생성 -->
<%if(id.equals("admin")){%>
<a href="member_list.jsp">관리자모드 접속(회원 목록 보기)</a>
<%}%>
</body>
</html>
