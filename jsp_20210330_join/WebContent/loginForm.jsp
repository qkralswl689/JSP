<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 로그인 페이지</title>
<style>
	table{
		margin : auto;
		width : 400px;
		border : 1px solid gray;
		text-align: center;
	}
	.td_title{
		font-weight: bold;
		font-size: x-large;
	}
</style>
</head>
<body>
<!-- form영역에 있는 데이터가 전송되는 요청 URL을   loginProcess.jsp 페이지로 지정-->
<form name="loginform" action="loginProcess.jsp" method="post">
<table>
	<tr>
		<td colspan="2" class = "td_title">
			로그인 페이지
		</td>
	</tr>
	<tr>
		<!--로그인에 사용할 ID입력 양식  -->
		<td><label for = "id">아이디 : </label></td>
		<td><input type="text" name="id" id = "id"/></td>
	</tr>
	<tr>
		<!--로그인에 사용할 PW입력 양식  -->
		<td><label for = "pass">비밀번호 : </label></td>
		<td><input type="password" name="pass" id = "pass"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<!-- 로그인 링크를 클릭하면 js를 사용해 loginform  객체의 submit() 메소드를 호출하여
					 loginProcess.jsp 페이지로 요청한다 -->
			<a href="javascript:loginform.submit()">로그인</a>&nbsp;&nbsp;
			<!-- 회원가입 텍스트를 클릭하면 joinForm.jsp 로 요청을 전송하는 링크를 지정  -->
			<a href="joinForm.jsp">회원가입</a>
		</td>
	</tr>
</table>
</form>
</body>
</html>