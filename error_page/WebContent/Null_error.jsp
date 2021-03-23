<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>NullPointerException 상황</title>
</head>
<body>
<%
	// Object obj; // 이런 경우는 500 에러 발생 
	Object obj = null; // 이런 경우는 NullPointerException 발생
	// out.println(obj); // 이런 경우는 NullPointerException 감지 안됨
	out.println(obj.toString()); // 이런 경우는 NullPointerException 감지됨 !	
%>
</body>
</html>