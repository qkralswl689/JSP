<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- text 객체 import -->
<%@ page import="java.text.*" %>
<!-- SimpleDateFormat 객체 생성 -->
<%
	SimpleDateFormat f = new SimpleDateFormat("yyyy-M-d hh:mm:ss");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>session 객체</title>
</head>
<body>

<%
/* long타입 lastTime 변수 생성, 세션에서 마지막으로 접속한 시간 할당  */
long lastTime=session.getLastAccessedTime();
/* long타입 createTime 변수 생성, 세션 생성시간 할당 */
long createTime=session.getCreationTime();
/* long타입 useTime 변수 생성, (세션 마지막 접속시간 - 세션생성시간) / 1000 값 할당  */
long useTime=(lastTime - createTime) / 1000;
%>
<!-- 맨 위 작성한 형식으로 lastTime,createTime 값  출력  -->
<%=f.format(lastTime) %><br>
<%=f.format(createTime) %><br>
<!-- useTime 변수 값 출력 -->
<%=useTime %>초 동안 사이트에 접속되어 있습니다.
</body>
</html>