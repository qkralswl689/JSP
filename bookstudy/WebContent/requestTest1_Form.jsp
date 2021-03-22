<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Request Test</title>
<style>
<%-- h1태그 영역과 아이디 속성 값이 commandCell인 영역의 내용들을 가운데 정렬해서 출력 --%>

h1,#commandCell{
text-align: center;
}

<%-- table 태그 영역을 화면의 가운데 정렬하고 width 속성값과 border속성 값을 설정--%>

table{
	margin : auto;
	width: 400px;
	border : 1px solid red;
	}
</style>
</head>
<body>
	<h1>Request 예제입니다.</h1>
	<form action="requestTest1.jsp" method="post">
	<table>
		<tr>
			<td><lable for ="name">이름</lable></td>
			<td><input type="text" name="name" id="name"></td>
		</tr>
		<tr>
			<td><lable for ="gender">성별</lable></td>
			<td>남<input type="radio" name="gender" value="male" id="gender">
				여<input type="radio" name="gender" value="female">
			</td>
		</tr>
		<tr>
			<td><label for="hobby">취미</label></td>
			<td>독서<input type="checkbox" name="hobby" value="독서" id="hobby">
				게임<input type="checkbox" name="hobby" value="게임" id="hobby">
				TV시청<input type="checkbox" name="hobby" value="TV시청" id="hobby">
				축구<input type="checkbox" name="hobby" value="축구" id="hobby">
				기타<input type="checkbox" name="hobby" value="기타" id="hobby">
			</td>
		</tr>
		<tr>
			<td colspan="2" id="commandCell"><input type="submit" value="전송"></td>
		</tr>
	</table>
	
	</form>

</body>
</html>