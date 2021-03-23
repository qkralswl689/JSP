<%@ page errorPage="/error/viewError.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
<title>NullPointerException 상황 : 인자 출력</title>
</head>
<body>
<%
	// 안타깝게도 이럴 경우에는 NullPointerException 에러 감지가 안됩니다 !	
	String name = request.getParameter("name");
	out.println("name 의 자료형 : " + 
			    (name instanceof Object ? "문자열(String)" : "문자열(String)이 아님") + "<br>");
%>
	인자 값(name) : <%= name %>

</body>
</html>