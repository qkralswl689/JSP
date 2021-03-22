<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>action</title>
</head>
<body>
<%
	// POST 방식 전용 인자 인코딩 처리
	request.setCharacterEncoding("UTF-8");

	// html body 
	// 인자 여부 확인하기 위해 3항연산자 사용
	String id = request.getParameter("id") == null ? "없음" : request.getParameter("id");
	String pw = request.getParameter("pw") == null ? "없음" : request.getParameter("pw");
	String name = request.getParameter("name") == null ? "없음" : request.getParameter("name");
	String address = request.getParameter("address") == null ? "없음" : request.getParameter("address");
	
	// out JSP 기본 객체 활용 (인자 전송)
	//out.println("id : " + id + "<br>");
	//out.println("pw : " + pw + "<br>");
	//out.println("name : " + name + "<br>");
	//out.println("address : " + address );
	
%>
<%-- 표현식(expression) 활용  (인자 전송)--%>
<%--  
id : <%=id %><br>
pw : <%=pw %><br>
name : <%=name %><br>
address : <%=address %>
--%>

<!-- EL(Expressional Language) 기본객체(param)활용 (인자 전송) -->
id : ${param.id}<br>
pw : ${param.pw}<br>
name : ${param.name}<br>
address : ${param.address}<br>

</body>
</html>