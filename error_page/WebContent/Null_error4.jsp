<%--@ page errorPage="/error/viewError.jsp" --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
<title>NullPointerException 상황 : 인자 출력</title>
</head>
<body>
<%
	// 아래와 같이 일일이 null을 점검하여 throw로 예외를 던지면 그때는 감지되어 에러 처리됩니다.
	if (request.getParameter("name") == null) {
		throw new NullPointerException();
	} 

	String name = request.getParameter("name");
	out.println(name);
%>
	인자 값(name) : <%= name %>

</body>
</html>