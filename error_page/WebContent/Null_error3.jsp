<%-- 
	아래의 errorPage를 활성화하면 예외처리시
	web.xml에 표기된 아래의 사항 보다 우선적으로 처리됩니다. 번갈아 가면서 실행해봅시다.
	
	<error-page>
		<exception-type>java.lang.NullPointerException</exception-type>
		<location>/error/NullPointer.jsp</location>
	</error-page>  
--%>
<%--@ page errorPage="/error/viewError.jsp" --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
<title>NullPointerException 상황 : 인자 출력</title>
</head>
<body>
<%
	// 안타깝게도 이럴 경우에는 NullPointerException 에러 감지가 안됩니다 !	
	String name = request.getParameter("name")==null ? 
				  null : request.getParameter("name");
	out.println("name 의 자료형 : " + 
			    (name instanceof Object ? "문자열(String)" : "문자열(String)이 아님") + "<br>");
	
	// 아래의 경우를 번갈아 가면서 실행해 봅니다.
	// out.println("name : " + name + "<br>"); // NullPointerException 감지 안됨
	out.println("name : " + name.toString() + "<br>"); // NullPointerException 감지됨 -> 예외처리 페이지 이동
%>
	인자 값(name) : <%= name %>

</body>
</html>