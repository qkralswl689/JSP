<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> session 객체 </TITLE>
</HEAD>
<BODY>
<%--session.invalidate() : 세션 전부제거 (내가만든것만)
removeAttribute() : 세션 하나만 제거--%>
<% session.invalidate(); %>
<FORM action="session_2.jsp" Method="post">

아이디   : <input type="text" name="strID"> <BR>
비밀번호 : <input type="password" name="strPwd"> <BR><BR>

<input type="submit" Value="로그인">
<input type="reset" Value="취소">
</Form>

</BODY>
</HTML>
