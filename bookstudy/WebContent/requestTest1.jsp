<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- 파라미터의 한글 처리를 위해 request 객체의 문자셋 인코딩 방식을 UTF-8 로 지정 --%>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Request Test</title>
<style>
h1 {
	text-align:center;
}
table{
	margin : auto;
	width: 400px;
	border : 1px solid red;
	}
</style>
</head>
<body>
	<h1>Request 예제입니다.</h1>
	<table>
		<tr>
			<td>이름</td>
			<%-- name이란 이름을 가진 파라미터 값을 얻어와 표현식으로 출력 --%>
			<td><%=request.getParameter("name") %></td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
				<%-- gender란 이름을 가진 파라미처 값이 male일 경우 성별을 남자,female경우 여자로 출력 --%>
				<%if(request.getParameter("gender").equals("male")) {%>남자
				<%}else {%>여자<%} %>
			</td>
		</tr>
		<tr>
			<%-- hobby란 이름을 가진 차라미터는 여러개의 값을 가질 수 있기 때문에 getParameterValues 메소드를 사용하여
				String 배열로 값을 가져온 뒤 for문을 이용해 String 배열에 저장된 값을 모두 출력 --%>
			<td>취미</td>
			<td>
				<%
				String[] hobby=request.getParameterValues("hobby");
				for(int i=0; i<hobby.length;i++){
				%>
				<%=hobby[i] %> &nbsp;&nbsp;	
				<%} %>
			</td>
		</tr>
	</table>
</body>
</html>