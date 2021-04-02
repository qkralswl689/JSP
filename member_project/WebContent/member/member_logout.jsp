<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
<style>
/* 전체 레이아웃 */
div#wrap {
	width:100vw;
	height:100vh;
	display:flex;
	align-items:center;
	justify-content:center;
}

/* 로그인 박스(아이디/패쓰워드 입력란) */
div#login_box {
	width:500px;
/* 	background:yellow; */
}
</style>

<!-- google material -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- bootstrap CSS : 4.4.1 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery : 3.5.1 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- bootstrap JS : 4.4.1 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<script>
window.onload = function() {
	
	logout_btn.onclick = function() {
		
		alert("로그아웃");
		location.href = "${pageContext.request.contextPath}/member/logoutProc.do";
	} //
} //
</script>
</head>
<body>

	<div id="wrap" class="mx-auto">
	
		<div id="login_box" class="container">
		
			<div>
		        <div style="float:left" class="mt-2"><b>${LOGIN_SESSION}</b> 님이 로그인 하셨습니다.&nbsp;</div>
			 	<div><input type="button" class="btn btn-primary ml-3" id="logout_btn" value="로그아웃"></div>			
		 	</div>
			
		</div>
		
	</div>

</body>
</html>