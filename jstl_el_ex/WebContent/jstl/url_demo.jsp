<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 경로차이 비교 : 다 같은 결과 출력된다, 경로3 추천! -->
경로1 : ${pageContext.request.contextPath }/jstl<br>
경로2 : <%=request.getContextPath() %>/jstl<br>
경로3 : <c:url value="/jstl" /><br>
<!-- requestScope : 의미적으로 맞을 수도 있겟지만 ,contextPath를 지원하지 않는다 
		 requestScope객체에 contextPath가 없다-->
경로4 : ${requestScope.contextPath }/jstl<br>
</body>
</html>