<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>pageContext 객체</title>
</head>
<body>
<%
out.print("회원님 환영합니다.<br>");
out.flush(); // 출력버퍼 내용을 비운다

// include : 임시적으로 지정된 페이지로 제어권을 넘긴다, 제어처리가 긑나면 원래 페이지로 제어권이 넘어온다
pageContext.include("pageContext3.jsp");
out.println("저희 사이트를 애용해 주셔서 감사합니다.");

%>

</body>
</html>