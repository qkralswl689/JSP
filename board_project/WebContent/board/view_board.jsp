<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>개별 게시글 조회</title>

<!-- contextPath -->
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<c:set var="bootstrap_css" value="${rootPath}/js/bootstrap/4.4.1/css/bootstrap.min.css"/>
<c:set var="bootstrap_js" value="${rootPath}/js/bootstrap/4.4.1/js/bootstrap.min.js" />
<c:set var="popper_js" value="${rootPath}/js/bootstrap/popper/popper.min.js" />
<c:set var="jquery" value="${rootPath}/js/jQuery/3.4.1/jquery-3.4.1.min.js"  />

<!-- bootstrap CSS : 4.4.1 -->
<link rel="stylesheet" href="${bootstrap_css}">

<!-- jQuery : 3.4.1 -->
<script src="${jquery}" charset="UTF-8"></script>

<!-- popper.js -->
<script src="${popper_js}" charset="UTF-8"></script>

<!-- bootstrap JS : 4.4.1 -->
<script src="${bootstrap_js}" charset="UTF-8"></script>

<script>
// IE 지원 URI 인코딩
window.onload = function() {
	
	var download_link = document.getElementById("download_link");
	
	// IE일 경우 변환
	// 변환없으면 "유효한 문자들은 RFC 7230과 RFC 3986에 정의되어 있습니다" 에러 유발
	var agent = navigator.userAgent.toLowerCase();

	if ( (navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) 
		|| (agent.indexOf("msie") != -1) ) {
	
		alert("인터넷 익스플로러 브라우저 입니다.");
		download_link.href = encodeURI(download_link.href);
	    console.log("download_link.href : "+download_link.href);
	} //
	
} //
</script>

<style>
section#viewForm {
	width: 600px;
}

/* 글내용 읽기 필드 크기 조절 방지 */
textarea {
	resize: none;
}
</style>
</head>

<body>

	<!-- 개별 게시글 조회 시작 -->
	<section id="viewForm" class="container">

		<h3>개별 게시글 조회</h3>

		<!-- bootstrap table 적용 -->
		<table class="table table-striped">
		
			<tr class="form-group">
				<td class="col-xs-2">
					<label for="boardNum" class="control-label"> 
						<span style="color:red">*</span>게시글 번호
					</label>
				</td>
				<td class="col-xs-10">
					<input type="text" 
						   id="boardNum"
						   name="boardNum" 
						   class="form-control"
						   readonly 
						   value="${boardVO.boardNum}" />
			    </td>
			</tr>
			
			<!-- 추가 : 게시글 등록일 -->
			<tr class="form-group">
				<td class="col-xs-2">
					<label class="control-label"> 
						<span style="color:red">*</span>게시글 등록일
					</label>
				</td>
				<td class="col-xs-10">
					<fmt:formatDate value="${boardVO.boardDate}" 
								    pattern="yyyy년  M월 d일 HH:mm:ss" />
			    </td>
			</tr>

			<tr class="form-group">
				<td class="col-xs-2">
					<label for="boardName" class="control-label"> 
						<span style="color:red">*</span>글쓴이
					</label>
				</td>
				<td class="col-xs-10">
					<input type="text" 
						   id="boardName"
						   name="boardName" 
						   class="form-control"
						   readonly 
						   value="${boardVO.boardName}"/>
			    </td>
			</tr>

			<tr>
				<td>
					<label for="boardPass" class="control-label"> 
						<span style="color:red">*</span>비밀번호
					</label>
				</td>
				<td>
					<input type="text" 
						   id="boardPass" 
						   name="boardPass"
						   class="form-control" 
						   readonly 
						   value="${boardVO.boardPass}"/>
			    </td>
			</tr>

			<tr>
				<td>
					<label for="boardSubject" class="control-label">
						<span style="color:red">*</span>제 목
					</label>
				</td>
				<td>
					<input type="text" 
						   id="boardSubject" 
						   name="boardSubject"
						   class="form-control"
						   readonly 
						   value="${boardVO.boardSubject}"/>
			    </td>
			</tr>

			<tr>
				<td>
					<label for="boardContent" class="control-label">
						<span style="color:red">*</span>내 용
					</label>
				</td>
				<td>
					<textarea id="boardContent"
							  name="boardContent"
							  cols="40" 
							  rows="15" 
							  class="form-control"
							  readonly>${boardVO.boardContent}</textarea>
			  	</td>
			</tr>

			<tr>
				<td>
					<label for="boardFile" class="control-label">파일 첨부</label>
				</td>
				<td>
				    <!-- 업로드 파일 정책 변경 전 -->
					<%-- <a href="${pageContext.request.contextPath}/upload/${boardVO.boardFile}">${boardVO.boardFile}</a> --%>
					<!-- 업로드 파일 정책 변경 후 -->	   
					<a id="download_link" href="${pageContext.request.contextPath}/board/download.do?uploadFile=${boardVO.boardFile}&boardNum=${boardVO.boardNum}">
						${originalUploadFile}
					</a>
			    </td>
			</tr>

		</table>

	</section>
	<!-- 개별 게시글 조회 끝 -->

</body>
</html>