<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> application 객체 </TITLE>
</HEAD>
<BODY>

<TABLE border=1>
<TR>
	<TD><B> Server Information </B></TD>
    	<TD><B> 값 </B></TD>
</TR>

<% 
										// getServletInfo() : JSP / 서블릿 컨테이너의 이름과 버전 반환
out.println ("<TR><TD>서버정보</TD><TD>" + application.getServerInfo() + "</TD></TR>");
										//  getMimeType(파일명) : 파일의 MIME 타입 반환
out.println ("<TR><TD>MIME 타입</TD><TD>" + application.getMimeType("application_1.jsp") + "</TD></TR>");
										// getResource(path) : path에 지정된 자원을 url 객체로 반환
out.println ("<TR><TD>URL 정보</TD><TD>" + application.getResource("/") + "</TD></TR>");
										// getRealPath(path) : 지정한 경로를 웹 애플리케이션 시스템상의 경로로 변경하여 반환
out.println ("<TR><TD>로컬경로</TD><TD>" + application.getRealPath("/") + "</TD></TR>");
										// getContext(path) : path에 지정된 자원의 context정보(상황) 반환
out.println ("<TR><TD>컨텍스트정보</TD><TD>" + application.getContext("/") + "</TD></TR>");
%>
</TABLE>

</BODY>
</HTML>
