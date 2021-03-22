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
 	<jsp:useBean id="dto2" class="bean.MemberDto" scope="page">
		
	<!-- setter역할을 한다 --> 
		<%-- <jsp:setProperty name="dto2" property="id" value="java" /> --%>
		<jsp:setProperty name="dto2" property="*" />
	</jsp:useBean> 
	
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