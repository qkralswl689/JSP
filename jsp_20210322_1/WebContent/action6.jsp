<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.MemberDto" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>action6</title>
</head>
<body>
	<%-- 자바빈
		MemberDto dto = new MemberDto();
		
		dto.setId("java");
		out.println("id : " + dto.getId());
	--%>
	
	<%-- 위와 같은기능 --%>
	<!--  id=빈이름, class= 자바빈 클래스 명 , scope= 사용 범위  -->
	<!--  page : 빈 객체 공유 범위가 현재 페이지의 범위에만 한정, 페이지가 변경되면 공유가 유지되지 않는다 -->
 	<jsp:useBean id="dto2" class="bean.MemberDto" scope="page">
		
	<!-- setter역할을 한다 --> 
		<%-- <jsp:setProperty name="dto2" property="id" value="java" /> --%>
		<!-- 자바빈 클래스의 속성 값을 설정하기 위한 태그 -->
		<!-- name = 빈 이름 , property=속성명 -->
		<jsp:setProperty name="dto2" property="*" />
	</jsp:useBean> 
	<!-- 자바빈 클래스의 속성 값을 가져오기 위한 태그 -->
	<!-- name = 빈 이름, property=속성명 -->
	id : <jsp:getProperty property="id" name="dto2" /><br>
	pw : <jsp:getProperty property="pw" name="dto2" /><br>
	name : <jsp:getProperty property="name" name="dto2" /><br>
	address : <jsp:getProperty property="address" name="dto2" /><br>
	hobby : <%-- <jsp:getProperty property="hobby" name="dto2" /> --%>
	
	
<%-- 	<%
		for(String s : dto2.getHobby()) {
	%>		
		<%=s + " " %>
	<% 	
		}
	%> --%>
	
	<%-- <%=dto2.getHobby()[0] %> --%>
</body>
</html>