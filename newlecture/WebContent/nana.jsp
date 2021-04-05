<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	String cnt_ = request.getParameter("cnt"); 

		int cnt = 100; 
		if(cnt_ != null && !cnt_.equals("")) { 
			cnt = Integer.parseInt(cnt_); }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%for(int i=0; i<cnt; i++){ %>
	안녕 Servlet!!<br >
	<% } %>
</body>
</html>