<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>공통 메뉴</title>
<style>
/*  width 없으면 기본적으로 100%(화면 길이) */
body {
 	margin:0; 
}

ul#top_main_menu {
	list-style-type:none;
	margin:0;
	padding:0;
	overflow:hidden;
	background-color:#333;
	width:100%;
	height:50px;
}

/* 회원관리 서브 메뉴 */
ul#member_manage_sub_ul {
	list-style-type:none;
	margin:0;
	padding:0;
	overflow:hidden;
	background-color:#333;
	width:400px;
	height:50px;
}

ul#top_main_menu li[id^=top_menu_li] { 
	float:left;
	width:150px; 
	height:50px;
    display:flex;
    align-items:center;
    justify-content:center;
}

/* 회원관리 서브 메뉴 */
ul#member_manage_sub_ul li { 
	float:left;
	width:165px; 
	height:50px;
    display:flex;
    align-items:center;
    justify-content:center;
}

/* 회원관리 서브 메뉴  포함(공통)  */
ul#top_main_menu li[id^=top_menu_li] a.top_menu_link, 
ul#member_manage_sub_ul li a {
    display:block;
    color:white;
    text-align:center;
    text-decoration:none;
    padding:15px 30px;
}

/* 회원관리 서브 메뉴  포함(공통)  */
ul#top_main_menu li[id^=top_menu_li] a.top_menu_link:hover, 
ul#member_manage_sub_ul li a:hover {
    background-color:#fff;
    color:#000;
    font-weight:bold;
}

/* 추가 : 로그인 정보 : 최상단 */
span#login_sess_id {
	color:#fff;
	font-size:10pt;
	width:200px;
}	
	
/* 회원관리 서브 메뉴 */
div#member_manage_submenu {
	position:absolute;
	left:450px;
	top:50px;
	width:330px;
	height:50px;
	background:yellow;
	z-index:2;
	overflow:hidden;
}
</style>
<!-- google material -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- CSS 외장화 -->
<!--  modal -->
<link rel="stylesheet" href="/member_project/css/modal.css">

<!-- jquery -->  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  
<!-- jqueryui -->  
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- 회원관리 서브 메뉴 -->
<script>
$(function() {
	
	// 서브 메뉴 초기상태
	$("div#member_manage_submenu").hide();
	
	// 메인 메뉴(My Page)의 x좌표 획득 -> 서브 메뉴 좌표 설정
	// 메인 메뉴 근처에 서브 메뉴를 위치시킴. 
	console.log("좌표 : "+$("#top_menu_li3").offset().left);
	$("div#member_manage_submenu").css("left", $("#top_menu_li3").offset().left);
	
	// 서브 메뉴 이벤트 처리(핸들링)
	$("#top_menu_li3").mouseover(function() {
		$("div#member_manage_submenu").show();
	});
	
	$("div#member_manage_submenu").mouseout(function() {
		$("div#member_manage_submenu").hide();
	});
	
	$("div#member_manage_submenu").mouseover(function() {
		$("div#member_manage_submenu").show();
	});
	
});
</script>
</head>
<body>

	<!-- 회원관리 서브 메뉴 -->
	<div id="member_manage_submenu">
		<ul id="member_manage_sub_ul">
			<li><a href="${pageContext.request.contextPath}/member/viewAll.do">전체 회원 조회</a></li>
			<li><a href="${pageContext.request.contextPath}/member/viewRoles.do">회원 등급 조회</a></li>
		</ul>
	</div>

	<!-- 상단 공통 메뉴 -->
	<nav>
		<ul id="top_main_menu">
			<li id="top_menu_li1"><a class="top_menu_link" href="${pageContext.request.contextPath}/index.do">Home</a></li>
			
			<!-- 로그인/로그아웃 토글 모드 -->
			<c:if test="${empty sessionScope.LOGIN_SESSION}">
				<!-- 초기 페이지 인트로 변경에 따른 로그인 페이지 링크 변경 -->
				<li id="top_menu_li2"><a class="top_menu_link" href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
			</c:if>
			<c:if test="${not empty sessionScope.LOGIN_SESSION}">
				<li id="top_menu_li2_1"><span id="login_sess_id">${sessionScope.LOGIN_SESSION} 로그인 중</span></li>
				<li id="top_menu_li2_2"><a class="top_menu_link" href="${pageContext.request.contextPath}/member/logoutProc.do">로그아웃</a></li>
			</c:if>
			
			<!-- 회원관리 -->
			<li id="top_menu_li3"><a class="top_menu_link" href="${pageContext.request.contextPath}/member/viewAll.do">회원관리</a></li>
			
			<!-- My Page -->
			<li id="top_menu_li4"><a class="top_menu_link" href="${pageContext.request.contextPath}/member/myPage.do">My Page</a></li>
			<li id="top_menu_li5"><a class="top_menu_link" href="#">About</a></li>
	    </ul>
    </nav>

</body>
</html>