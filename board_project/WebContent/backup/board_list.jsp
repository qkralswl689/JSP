<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>

<!-- contextPath -->
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<c:set var="bootstrap_css" value="${rootPath}/js/bootstrap/4.4.1/css/bootstrap.min.css"/>
<c:set var="bootstrap_js" value="${rootPath}/js/bootstrap/4.4.1/js/bootstrap.min.js" />
<c:set var="popper_js" value="${rootPath}/js/bootstrap/popper/popper.min.js" />
<c:set var="jquery" value="${rootPath}/js/jQuery/3.4.1/jquery-3.4.1.min.js"  />

<!-- bootstrap4에서의 google material icons활용 -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- bootstrap CSS : 4.4.1 -->
<link rel="stylesheet" href="${bootstrap_css}">

<!-- jQuery : 3.4.1 -->
<script src="${jquery}" charset="UTF-8"></script>

<!-- popper.js -->
<script src="${popper_js}" charset="UTF-8"></script>

<!-- bootstrap JS : 4.4.1 -->
<script src="${bootstrap_js}" charset="UTF-8"></script>

<style type="text/css">
/* 페이징 인자 모니터링 */
#paging_params_tbl { 
	color:yellow;
	font-weight:bolder;
}

#paging_params_tbl td {
	width:100px;
}

/* 등록된 글이 없을 경우, 페이징 처리 */
#emptyArea, #pageList  
{
	margin:auto;
	text-align:center;
	display:flex;
	align-items:center;
	justify-content:center;
}
</style>
</head>
<body>

	<!-- 페이징 인자들 -->
 	<div class="fixed-top">
 		<table id="paging_params_tbl" class="table table-dark">
 			<tr>
 				<td><b>게시판 페이징 인자들</b></td>
				<td>총 게시글 수 : ${pageVO.listCount}</td>
				<td>현재 페이지 : ${pageVO.page}</td>
				<td>총 페이지 : ${pageVO.maxPage}</td>
				<td>시작 페이지 : ${pageVO.startPage}</td>
				<td>끝 페이지 : ${pageVO.endPage}</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
	
	<!-- 개별 게시글 정보 보기(팝업) 시작 -->
	<%--@ include file="/board/list_popup.jsp" --%>
	<!-- 개별 게시글 정보 보기(팝업) 끝 -->
	
	<h3 align="center">
		글 목록   &nbsp;<a href="/board/write.do">게시판 글쓰기</a>
	</h3>
	<br>
	
	<!-- 게시판 리스트 시작 -->
	<section id="listForm" style="width:800px; margin:auto;">
		
    	<c:if test="${not empty boardList && pageVO.listCount > 0}">
		
			<!-- 게시글 부분 시작 -->
			<table id="board_tbl" class="table table-striped table-hover">
			
				<tr id="tr_top">
				    <td>번호</td>
					<td>글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>날짜</td>
					<td>조회수</td>
				</tr>
	
				<c:forEach var="board" items="${boardList}" varStatus="st">
					
					<tr>
					    <!-- 페이지당 상대 글번호 계산 ex) 한계량(limit=5) => 1,2,3,4,5 -->
						<td>${st.count + (pageVO.page-1)*limit}</td>
						<!-- 게시글 번호 -->
						<td>${board.boardNum}</td>
		
						<td>
						    <!-- 답글 레벨에 따른 계산 -->
							<c:choose>
								<c:when test="${board.boardReLev != 0}">
									<c:forEach var="a" 
											   begin="0" 
											   end="${board.boardReLev * 2}" 
											   step="1" 
											   varStatus="st">
										&nbsp;										
									</c:forEach>
								 	<span class="material-icons" 
									  	  style="color:#0079FF; font-size:9pt">
					   	     		   keyboard_arrow_right
					   	     		   
						     		</span>
								</c:when>
								<c:otherwise>
									<span class="material-icons" 
									  	  style="color:#0079FF; font-size:9pt">
					   	     		   keyboard_arrow_right
						     		</span> 
								</c:otherwise> 
							</c:choose>
							
							<a href="#"
							   id="subject_${board.boardNum}">
							   ${board.boardSubject}
							</a>
						</td>
		
						<td>${board.boardName}</td>
						<td><fmt:formatDate value="${board.boardDate}" 
								 pattern="yyyy-M-d hh:mm:ss"/></td>
						
						<!-- 조회수 갱신 : id 할당-->		 
						<td id="readcount_${board.boardNum}">
							${board.boardReadCount}
						</td>
					</tr>
					
				</c:forEach>
					
			</table>
			<!-- 게시글 부분 끝 -->
		
			<!-- 페이징 시작 -->
			<%@ include file="../board/paging.jspf" %>
		    <!-- 페이징 끝 -->	
		
		</c:if>
	
		<!-- 등록글 없을 경우 -->
		<c:if test="${empty boardList || pageVO.listCount==0}">
			<section id="emptyArea">등록된 글이 없습니다.</section>
		</c:if> 
		
	
	</section>
	<!-- 게시판 리스트 끝 -->
	
</body>
</html>