<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>마이 페이지(My Page)</title>

<style>
div#myPage_wrap {
	width:100%;
	/* 템플릿 적용시 전체 레이아웃 높이 조정 : 본문 가용화면 확보 : 
	     가용화면(viewport) - 상단메뉴 높이 - 하단 section 높이 - (padding(높이))  */
	min-height:calc(100vh - 50px - 70px);
	
	display:flex;
	align-items:center;
	justify-content:center;
}	
</style>

<!-- google material -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- CSS 외장화 -->
<!--  modal -->
<link rel="stylesheet" href="/member_project/css/modal.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<script>
$(function() {
	
	// 개별 회원정보 조회
	$("#member_view_btn").click(function() {
		$(location).attr('href','${pageContext.request.contextPath}/member/view.do?memberId=${LOGIN_SESSION}');
	});
	
	// 개별 회원정보 수정
	$("#member_update_btn").click(function() {
		$(location).attr('href','${pageContext.request.contextPath}/member/update.do?memberId=${LOGIN_SESSION}');
	});
	
	// 개별 회원정보 삭제
	$("#member_delete_btn").click(function() {
		$(location).attr('href','${pageContext.request.contextPath}/member/delete.do?memberId=${LOGIN_SESSION}');
	});
	
});//
</script>
</head>
<body>

	<div id="myPage_wrap">
		<div class="btn-group mx-auto">	
			<input type="button" id="member_view_btn" value="회원정보 조회" class="btn btn-primary">
			<input type="button" id="member_update_btn" value="회원정보 수정" class="btn btn-outline-primary"> 
			<input type="button" id="member_delete_btn" value="회원정보 삭제" class="btn btn-primary">
		</div>	
	</div>
	
</body>
</html>