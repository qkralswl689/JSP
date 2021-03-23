<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Request 객체1</title>
</head>
<body>
<TABLE border=1>
<TR>
	<TD><B> Client &amp; Server Information </B></TD>
    	<TD><B> 값 </B></TD>
</TR>
<%
									   // getCharacterEncoding() :  웹 브라우저의 문자 인코딩을 가져옴
out.print("<TR><TD>인코딩 방식</TD><TD>" +request.getCharacterEncoding() +"</TD></TR>");
										// getContentType() : 전송할 때 사용한 웹 브라우저의 콘텐츠 유형을 가져옴
out.print("<TR><TD>MIME 타입</TD><TD>" + request.getContentType() + "</TD></TR>");
										// getContentLength() : 전송한  웹 브라우저의 요청 파라미터 길이를 가져옴
									   // 알 수 없다면 -1 return
out.print("<TR><TD>요청 정보 길이</TD><TD>" + request.getContentLength() + "</TD></TR>");
										// getHeaderNames() : 모든 헤더 이름을 가져옴
out.println ("<TR><TD>Header 이름</TD><TD>" + request.getHeaderNames()+ "</TD></TR>");
										// getPathInfo() : URI뒤에 추가 경로 정보를 제공하고 서블릿에 엑세스 하는데 사용되는 ㅗ안전한 경로 제공
out.println ("<TR><TD>웹 전달 경로</TD><TD>" + request.getPathInfo() + "</TD></TR>");
										//getRemoteHost() : 요청한 호스트의 완전한 이름을 구함
out.println ("<TR><TD>클라이언트 이름</TD><TD>" + request.getRemoteHost() + "</TD></TR>");
										// getRemoteAddr() : 웹 브라우저의 ip주소를 가져옴 
out.println ("<TR><TD>클라이언트 IP 주소</TD><TD>" + request.getRemoteAddr()+ "</TD></TR>");
										// getRequestURL() : 웹 브라우저가 요청한 URL 경로를 가져옴
out.println ("<TR><TD>클라이언트 URL 경로</TD><TD>" + request.getRequestURL()+ "</TD></TR>");
										// getMethod() :  웹 브라우저의 HTTP 요청 메소드(get, post)를 가져옴
out.println ("<TR><TD>전송 방식</TD><TD>" + request.getMethod() + "</TD></TR>");
										// getProtocol() : 웹 브라우저의 요청 프로토콜을 가져옴
out.println ("<TR><TD>프로토콜 이름</TD><TD>" + request.getProtocol() + "</TD></TR>");
										// getServerName() : 서버 이름을 가져옴
out.println ("<TR><TD>서버 이름</TD><TD>" + request.getServerName() + "</TD></TR>");
										// getServerPort() : 실행중인 서버 포트 번호를 가져옴
out.println ("<TR><TD>서버 포트번호</TD><TD>" + request.getServerPort()+ "</TD></TR>");								
%>
</body>
</html>