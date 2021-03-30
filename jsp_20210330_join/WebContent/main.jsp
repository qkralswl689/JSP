<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String id = null;

	//입력받은 id값이 세션의 id에 등록되어있는 상태라면 
	if(session.getAttribute("id")!=null){ 
		id=(String)session.getAttribute("id"); //세션에 저장된 Id값을 변수에 저장한다
	}else{//아니면
		out.println("<script>");
		out.println("location.href='loginForm.jsp'");
		out.println("</script>");
	}
%>

<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원관리 시스템 메인 페이지</title>
</head>
<body>
<h3><%=id %> 로 로그인하셨습니다.</h3>
<%if(id.equals("admin")){%>
<a href="member_list.jsp">관리자모드 접속(회원 목록 보기)</a>
<%}%>
</body>
</html>