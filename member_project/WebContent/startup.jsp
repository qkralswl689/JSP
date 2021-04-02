<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>초기 화면</title>
<style>
div#startup_wrap {
	width:100vw;
	/* 템플릿 적용시 전체 레이아웃 높이 조정 : 본문 가용화면 확보 : 
	     가용화면(viewport) - 상단메뉴 높이 - 하단 section 높이 - padding(높이)  */
	min-height:calc(100vh - 50px - 70px - 40px);
	display:flex;
	align-items:center;
	justify-content:center;
	
	font-size:5em;
	color:#60C5F1;
}
</style>
</head>
<body>

	<div id="startup_wrap">
		JavaTeam HRD System
	</div>
	
</body>
</html>