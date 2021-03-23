<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.RequestDispatcher" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>request 인자생성/페이지이동(forward)</title>
</head>
<body>
<%
	// 인자 생성
	request.setAttribute("name","goodee");

%>
<%-- 페이지 이동 --%>
<%--<jsp:forward page="forward.jsp" /> --%>
<%
	// forward => 인자 전송
	RequestDispatcher rd = request.getRequestDispatcher("forward.jsp");
	rd.forward(request, response);
%>
</body>
</html>