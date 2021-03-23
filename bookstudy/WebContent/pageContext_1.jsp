<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> pageContext 객체 </TITLE>
</HEAD>
<BODY>

<% 
// out. / getOut() : 결과가 같다
out.println("회원님 환영합니다.<BR>");
pageContext.getOut().print("마일리지를 드립니다<br>");
pageContext.getOut().println("마일리지 100점이 지급되었습니다.<BR>");
out.println("저희 사이트를 애용해 주셔서 감사합니다.");
%>

</BODY>
</HTML>