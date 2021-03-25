<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="vo.Member" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	/* 멤버 객체 생성 */
	Member member = new Member();
	HashMap<String, String> pref = new HashMap<String, String>();
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
</head>
<body>
<%
	member.setName("java"); // null값 발생시 대입할 값 설정
	//pageContext.setAttribute("member2", member2);
	request.setAttribute("member" , member);
	
	// Member로 치환하여 대입
	//Member member2 = (Member)request.getAttribute("member");
	//out.print("name2 : " + member2.getName());
%>
<!-- jstl scope의 기본값 : page,
c:set : 4가지 scope객체로 만든 setAttribute로 만든 객체를 태그로 만든것 -->
<!-- jstl : 가독성이 좋다 -->
jstl : <c:set var="member" value="<%=member %>" />
<hr>
name3 : <%=((Member)request.getAttribute("member")).getName() %>
<hr>
name4 : ${member.name}<br>

<!-- EL사용시 정확한 표현 -->
name5 : ${requestScope.member.name}<br>
name6 : ${pageScope.member.name}<br>

<br>
<!-- EL : null값 발생시 공백으로 출력한다  -->
<!-- c:set : 인스턴스 삽입 , jstl식 변수 선언 , pageContext이용한것과 같다-->
<!-- member변수에 member객체(자바) 대입 -->
<c:set var="member" value="<%= member %>" />

<!-- property="name" : name이라는 멤버필드의 값으로 , value="JSP" : "jsp"를 넣겟다  -->
<c:set target="${member}" property="name" value="JSP" />

<!-- name 필드 출력 , member라는 변수(jstl변수), getName/name : 같은결과를 나타낸다-->
name : ${member.getName()}<br>

<!-- el사용시 MemberVO의 private의 name에 바로 접근하는것이 아니고 getName을 통해 name에 접근하는것이다 -->
name : ${member.name}<br>

<!-- java 사용해서 memberVO접근  -->
name : <%=member.getName() %><br>


</body>
</html>