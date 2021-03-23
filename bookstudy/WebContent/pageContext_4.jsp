<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML>
<HTML lang="ko">
<HEAD>
<TITLE> pageContext 객체 </TITLE>
</HEAD>
<BODY>

<%
// setAttribute(String name, Object value) : 이름이 name인 속성의 값을 value로 지정한다
// setAttribute("pageValue","pageContext Example") : 이름이 pageValue인 속성의 값을 pageContext Example로 지정한다
pageContext.setAttribute("pageValue","pageContext Example");
Object code=pageContext.getAttribute("pageValue");

// code가null이 아니라면
if (code != null){
		/*읽어드린 속성은 object이므로 형변환을 해야한다 : (String)code : 문자열로 형변환  */
		// code를 String으로 형변환하여 문자열변서 strValue에 저장한다
          String strValue=(String)code;
		// strValue를 출력한다
          out.println("page Value=" + strValue + "<BR>");
}
else{ // null 이라면
		// 출력한다
          out.println("Not found!!");
}
%>

</BODY>
</HTML>