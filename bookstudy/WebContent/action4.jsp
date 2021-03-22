<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.MemberDto" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>action4</title>
</head>
<body>

	<%-- 	<!-- <jsp:useBean/> : 자바빈 객체를 생성하기 위한 태그 -->
	<!--id=빈이름, class= 자바빈 클래스 명 , scope= 사용 범위 -->
	page : 빈 객체 공유 범위가 현재 페이지의 범위에만 한정, 페이지가 변경되면 공유가 유지되지 않는다 --%>
	<jsp:useBean id="dto2" class="bean.MemberDto" scope="page">
			

		<%-- <jsp:setProperty/> : 자바빈 클래스의 속성 값을 설정하기 위한 태그 -->
		<!-- name = 빈 이름 , property=속성명 , value= 설정할 속성 값 -->
		<!-- <jsp:setProperty name="dto2" property="id" value="java" /> -->
		<!-- property= * : 와일드카드 => 클라이언트에서 전송되어 오는 파라미터 값이
						   모두 같은 이름의 빈 객체의 속성 값으로 자동으로 할당,
						   정보를 저장하는 빈 클래스를 작성 --%>
		
		<jsp:setProperty name="dto2" property="*" />
	</jsp:useBean> 
	
	<%-- <jsp:getProperty/> : 자바빈 클래스의 속성 값을 가져오기 위한 태그 -->
	<!-- name = 빈 이름, property=속성명 --%>
	
	id : <jsp:getProperty property="id" name="dto2" /><br>
	pw : <jsp:getProperty property="pw" name="dto2" /><br>
	name : <jsp:getProperty property="name" name="dto2" /><br>
	address : <jsp:getProperty property="address" name="dto2" /><br>
	hobby :
	
	<%
		for(String s : dto2.getHobby()) {
	%>		
		<%=s + " " %>
	<% 	
		}
	%> 
	
		
	
</body>
</html>