<%@ page contentType="text/html; charset=UTF-8" %>
<%--
	여기서 buffer 크기를 1kb로 했을 경우와 10kb로 했을 때의 차이를 비교해봅니다.
 --%>
<%@ page buffer="1kb" %>
<%@ page errorPage="/error/viewError.jsp" %>
<!doctype html>
<html lang="ko">
<head>
<title>버퍼 플러시(buffer flush) 이후 예외 발생</title>
</head>
<body>

<%  
	for (int i=0; i<256; i++) { 
		out.println(i); 
	}  
%>

<!-- divide by zero -->
<%= 1 / 0 %>

</body>
</html>