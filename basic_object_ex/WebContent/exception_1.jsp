<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%-- 에러가 나면 exception_2.jsp로 이동 --%>
<%@ page errorPage="exception_2.jsp" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> exception 객체  </TITLE>
</HEAD>
<BODY>

<% 
int intNum1=50;
int intNum2=0;

int intResult=intNum1 / intNum2;
%> 

</BODY>
</HTML>