<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>response 객체 </title>
</head>
<body>
<%
/* // String타입 변수 strsite 선언후 download 파라미터 대입 */
String strSite=request.getParameter("download");

switch(strSite)
/* 1 선택시  http://java.sun.com 로 이동
   response.sendRedirect 와 동일하다 */
{ case "1" : 
	out.print("<script>" +"location.href='http://java.sun.com'"+	
			 "</script>" );
	break;
	/*  2선택시 바로 "http://tomcat.apache.org" 페이지로 이동 */
case "2" :
	response.sendRedirect("http://tomcat.apache.org");
	break;
	/*  3선택시 바로 "http://www.eclipse.org" 페이지로 이동 */
case "3":
	response.sendRedirect("http://www.eclipse.org");
	break;
	/*  기본 "http://www.oracle.com" 페이지로 이동 */
default:
	response.sendRedirect("http://www.oracle.com");
	break;
}
%>
</body>
</html>