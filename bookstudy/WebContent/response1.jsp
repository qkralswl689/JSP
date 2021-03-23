<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>response 객체</title>
</head>
<body>
JSP 환경 설정을 위한 다운로드 페이지 입니다.
<%-- response2액션 상용, get방식 이용 --%>
<form action="response2.jsp" method="get">
<%-- select 이름 : downLoad --%>
<select name="download">
	<%-- select형식 옵션 사용 --%>
	<option selected value="1">JDK</option>
	<option value="2">Tomcat</option>
	<option value="3">eclipes</option>
	<option vlaue="4">oracle</option>
</select>
<%-- submit 타입 버튼 생성, 버튼 이름 : 이동 --%>
<input type="submit" value="이동">
</form>
</body>
</html>