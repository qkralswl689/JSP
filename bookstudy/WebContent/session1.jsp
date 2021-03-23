<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>session 객체</title>
</head>
<body>
<!-- session.invalidate() : 세션 초기화 -->
<% session.invalidate(); %>
<!-- session2의 액션 사용, post 메소드 사용 -->
<FORM action="session2.jsp" Method="post">

아이디   : <input type="text" name="strId"> <BR>
비밀번호 : <input type="password" name="strPwd"> <BR><BR>

<input type="submit" Value="로그인">
<input type="reset" Value="취소">
</Form>
</body>
</html>