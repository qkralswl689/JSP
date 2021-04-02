<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>template</title>
<style>
/* 본문 내부 간격 */
section#content_body {
	/* padding:20px 0; */
	/* 템플릿 적용시 전체 레이아웃 높이 조정 : 본문 가용화면 확보 : 
	     가용화면(viewport) - 상단메뉴 높이 - 하단 section 높이 - (padding(높이))  */
	min-height:calc(100vh - 50px - 70px);
}
</style>
</head>
<body>

	<!-- 상단 공동 메뉴 -->
	<header id="top_section">
		<%@ include file="top_menu.jsp" %>
	</header>
	
	<!-- 컨텐츠 -->
	<section id="content_body">
		<jsp:include page="${content_page}" />
	</section>
	
	<!-- 하단 -->
	<footer>
		<%@ include file="bottom.jsp" %>
	</footer>
	
</body>
</html>