<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- xml/html 주석 -->
<%-- JSP 주석 --%>
<%!
	// 선언문(declaration) : 잘 사용 안함
	// 주의) 전체가 JSE(순수자바), JEE(X)
	// out.println("JSP")// (X) JSP 사용 불가
	String name = "jsp"; // 함수 선언 가능
	public void print(String str){
		System.out.println("print : " + str );
		// out.println("print : " + str ); // JSP 사용 불가
	}
	public String getStr(String str){
		System.out.println("print : " + str );
		return str;
	}
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
name : <%=name %><br> <!-- expression(표현식) ";" 사용불가 -->
print method : <%--= print("java") --%><br> <!-- void 리턴 메서드 (사용X) -->
getStr : <%=getStr("jsp<br>") %>
페이지 이전 <br>
<%
	// 스크립트릿(Scriptlet)
	// JEE(O)
	// out : JSP 기본(내장) 객체 
	out.println("JSP<br>"); // 웹브라우저 인쇄 ,<br> : 개행
	out.println("Servlet<br>"); // 웹브라우저 인쇄
	out.println("name : " + name);
%>
<hr>
<%@ include file ="includer.jsp" %> <%-- 붙박이  --%>
<hr>
<%
	String pageUrl = "includer.jsp";
%>
<jsp:include page="<%=pageUrl %>" /><br> <!-- 액션 태그(action tag : XML) -->
<hr>
페이지 이후
</body>
</html>