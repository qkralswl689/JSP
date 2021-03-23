<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> application 객체  </TITLE>
</HEAD>
<BODY>
<%-- 서버 유지 되어있으면 정보가 유지된다 --%>
<%
// setAttribute(name,value) : name의 속성값을 value로 저장
// Name 의 속성값을 홍길동으로 저장
application.setAttribute("Name", "홍길동");
application.setAttribute("Age", "20세");
application.setAttribute("Email", "abcd@abcd.com");
application.setAttribute("Job", "Programmer");
application.setAttribute("Hobby", "독서");
%>

<!--  getAttributeNames() : 현재 application 객체에 저장된 속성들의 이름을 열거형식으로 가져온다.-->
이 름  : <%=application.getAttribute("Name") %> <BR>
나 이  : <%=application.getAttribute("Age") %> <BR>
이메일 : <%=application.getAttribute("Email") %> <BR>
직 업  : <%=application.getAttribute("Job") %> <BR>
취 미  : <%=application.getAttribute("Hobby") %>

</BODY>
</HTML>
