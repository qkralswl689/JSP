<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>if 태그</title>
</head>
<body>
<c:if test="true">
무조건 수행<br>
</c:if>

<!-- 아래(EL)와 같은표현 이지만 사용 X -->
<%-- <c:if test='<%=request.getParameter("name").equals("bk") %>'> --%>
<c:if test="${param.name == 'bk'}">
name 파라미터의 값이 ${param.name} 입니다.<br>
</c:if>

age : ${param.age }<br>
<c:if test='<%=Integer.parseInt(request.getParameter("age")) > 18 %>'>
<%-- <c:if test="${18 < param.age}"> --%>
당신의 나이는 18세 이상입니다.
</c:if>
</body>
</html>
