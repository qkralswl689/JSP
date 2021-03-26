<%@ page contentType="text/html; charset=UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
</head>
<body>
<!-- c:url value="http://search.daum.net/search" : 절대경로 찾기 편리하도록 한것 -->
context Path(경로) : ${pageContext.request.contextPath}<br>
context Path(경로) : <%=request.getContextPath() %>
<c:url value="http://search.daum.net/search" var="searchUrl">
	<c:param name="w" value="blog" />
	<c:param name="q" value="공원" />
</c:url>

<ul>
	<li><a href="${searchUrl}">링크1</a></li>
	<!-- /경로와 ./경로의 차이
		 / : 현재경로(jstl)를 생략하기 때문에 오류가 난다  -->
	<li><a href="<c:url value="/use_if_tag.jsp" />">링크2</a></li>
	<li><a href="<c:url value="./use_if_tag.jsp" />">링크3</a></li>
</ul>

</body>
</html>