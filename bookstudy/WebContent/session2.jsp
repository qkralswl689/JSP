<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>session 객체</title>
</head>
<body>
<%
// 문자열 변수 Id 선언후 strID를 파라미터 인자로 받는다
String Id = request.getParameter("strId");
// 세션에 저장된 자료는 다시 getAttribute()메서드를 이용해 조회한다
// 세션에 저장된 id를 조회해서 그 값이 null이라면
if (session.getAttribute("sessId") == null){
	// 세션의 id를 위에서 전달받은 인자값 : 변수Id 값으로 설정한다
	session.setAttribute("sessId", Id);
}
//세션 유지시간은 5초로 설정
session.setMaxInactiveInterval(5);

// 문자열 변수 sessId선언후 세션에서 받은 sessId값을 문자형으로 형변환하여 할당한다
String sessId=(String)session.getAttribute("sessId");

// 문자열 변수 strSessionId 선언후 세션에서 얻은 Id값을 할당한다
String strSessionId = session.getId();
// 정수형 변수 intTime선언 후 세션유지시간을 할당한다
int intTime=session.getMaxInactiveInterval();
%>
<%-- sessId값 출력 --%>
<B> <%=sessId %> 님 환영합니다. </B> <P> 

세션 ID : <%=strSessionId %> <BR>
세션 시간 : <%=intTime %>

</body>
</html>