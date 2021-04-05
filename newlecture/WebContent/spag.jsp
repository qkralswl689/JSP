<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- ------------------------------- -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>spag</title>
</head>
<%
	pageContext.setAttribute("result", "hello");
%>
<body>
	<%=request.getAttribute("result") %> 입니다.
	${requestScope.result}<br>
	${names[1]}<br>
	${notice.title}<br>
	${result}<br>
	${empty param.n ? '값이 비어 있습니다' : param.n}<br>
	${param.n/2 }<br>
	${header.accept}
</body>
</html>