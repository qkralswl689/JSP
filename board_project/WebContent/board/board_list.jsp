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
<!-- <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> -->

<!-- bootstrap CSS : 4.4.1 -->
<link rel="stylesheet" href="${bootstrap_css}">

<!-- jQuery : 3.4.1 -->
<script src="${jquery}" charset="UTF-8"></script>

<!-- popper.js -->
<script src="${popper_js}" charset="UTF-8"></script>

<!-- bootstrap JS : 4.4.1 -->
<script src="${bootstrap_js}" charset="UTF-8"></script>

<!-- 추가/변환 : 게시판 자체 CSS -->
<link rel="stylesheet" href="${rootPath}/css/board.css">

<!-- board custom javascript 외장화-->
<script src="${rootPath}/js/custom/board.js" charset="UTF-8"></script>

</head>
<body>

	<!-- 모달 박스(대화 상자:다이얼로그 박스) -->
	<%@ include file="view_board_modal.jsp" %>
	<%@ include file="update_board_modal.jsp" %>
	<%@ include file="reply_board_modal.jsp" %>
	<%@ include file="delete_board_modal.jsp" %>
	
	<!-- 페이징 인자들 -->
 	<div id="paging_params">  <!-- class="fixed-top" -->
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
	
	<div id="board_header">
		<h3 align="center">
			글 목록   &nbsp;<a href="${rootPath}/board/write.do">게시판 글쓰기</a>
		</h3>
	</div>
	
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
								 	
								 	<!-- 
								 	<span class="material-icons" 
									  	  style="color:#0079FF; font-size:9pt">
					   	     		   keyboard_arrow_right
						     		</span> 
						     		-->
						     		
						     		<!-- 댓글 아이콘 : 변경 -->
						     		<span class="material-icons" 
									  	  style="color:#0079FF; font-size:9pt">
					   	     		   subdirectory_arrow_right
						     		</span>
						     		
						     		<a href="#"
									   id="subject_${board.boardNum}">
									   ${board.boardSubject}
									</a>
						     		
								</c:when>
								<c:otherwise>
									
									<span class="material-icons" 
									  	  style="color:#0079FF; font-size:9pt">
					   	     		   keyboard_arrow_right
						     		</span>
						     		 
						     		<a href="#"
									   id="subject_${board.boardNum}">
									   <b>${board.boardSubject}</b>
									</a>
								</c:otherwise> 
							</c:choose>
							
							<!-- 기존 링크 -->
							<!-- 
							<a href="#"
							   id="subject_${board.boardNum}">
							   ${board.boardSubject}
							</a>
							-->
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
			<%--@ include file="paging.jspf" --%>
			<!-- 검색 여부에 따라 페이징을 구분 -->
		    <c:choose>
			    <c:when test="${searchYN=='search'}">
					<%@ include file="paging_search.jspf" %>
			    </c:when>
			    <c:otherwise>
					<%@ include file="paging.jspf" %>
				</c:otherwise>
			</c:choose>
		    <!-- 페이징 끝 -->	
		
		</c:if>
	
		<!-- 등록글 없을 경우 -->
		<c:if test="${empty boardList || pageVO.listCount==0}">
			<section id="emptyArea">등록된 글이 없습니다.</section>
		</c:if>
	
	</section>
	<!-- 게시판 리스트 끝 -->
	
	<!-- //////////////////////////////////////////////////////////////////// -->
	
	<!-- 게시판 검색부 시작 -->
	<div id="board_search" class="container mt-3 mx-auto"  style="width:520px">
		
		<form id="search_form" 
			  name="search_form" 
			  class="form-inline"
			  action="${rootPath}/board/search.do" >
			
			<select id="search_kind" name="search_kind" class="custom-select mr-2">
				<option>작성자</option>
				<option>글제목</option>
			</select>
			
			<input type="text" 
				   id="search_word" 
				   name="search_word"
			       pattern="[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{2,50}"
			       title="검색어를 2자 이상 입력하십시오"
			       required 
				   class="form-control mr-2" />
			
			<input type="submit" value="검색" class="btn btn-outline-primary mr-2" />
			
			<input type="button" id="board_list_btn" value="게시글 목록" class="btn btn-outline-primary" />
			
		</form>
			
	</div>
	<!--// 게시판 검색부 끝 -->
</body>
</html>