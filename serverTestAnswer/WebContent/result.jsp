<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.MemberDto"  %>
<%
	MemberDto member = (MemberDto)request.getAttribute("member");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>result</title>
</head>
<body>
- 아이디 : <%=member.getMemberId() %><br>
- 패쓰워드 : <%=member.getMemberPw() %><br>
- 이름 : <%=member.getMemberName() %><br>
- 주소 : <%=member.getMemberAddress() %>
</body>
</html>