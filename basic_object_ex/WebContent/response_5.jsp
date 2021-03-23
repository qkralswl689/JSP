<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> response 객체 </TITLE>
</HEAD>
<BODY>

<%
String strTemp="response_5.jsp?id=apple&pw=pineapple";

out.println(strTemp + "<BR>");
			// encodeURL() : 세션정보를 포함하고있는 링크에서 사용할 URL을 인코딩한다
out.println("encodeURL() : " + response.encodeURL(strTemp) + "<BR>");
			// encodeRedirect() :  웹 서버가 웹 브라우저에게 지정한 URL로 자동 이동되도록 한다.  
out.println("encodeRedirect() : " + response.encodeRedirectURL(strTemp) + "<BR>");
%>

</BODY>
</HTML>