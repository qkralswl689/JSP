<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>500 에러 유발</title>
</head>
<body>
<%-- 
	web.xml에서 아래의 처리 코드 부분을 생략한 후와 존재할 때의 실행 결과를 비교합니다.  

	<error-page>
		<error-code>500</error-code>
		<location>/error/500.jsp</location>
	</error-page>
	
 --%>
<%
	String str;
%>
str : <%=str %>
</body>
</html>