<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>

<!-- contextPath -->
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<c:set var="bootstrap_css" value="${rootPath}/js/bootstrap/4.4.1/css/bootstrap.min.css"/>
<c:set var="jqueryUI_css" value="${rootPath}/js/jQuery/ui/1.12.1/jquery-ui.min.css" />
<c:set var="bootstrap_js" value="${rootPath}/js/bootstrap/4.4.1/js/bootstrap.min.js" />
<c:set var="popper_js" value="${rootPath}/js/bootstrap/popper/popper.min.js" />
<c:set var="jquery" value="${rootPath}/js/jQuery/3.4.1/jquery-3.4.1.min.js"  />
<c:set var="jqueryUI" value="${rootPath}/js/jQuery/ui/1.12.1/jquery-ui.min.js" />
<c:set var="angularjs" value="${rootPath}/js/angularjs/1.7.8/angular.min.js"  />

<!-- bootstrap CSS : 4.4.1 -->
<link rel="stylesheet" href="${bootstrap_css}">

<!-- jQueryUI CSS : 1.12.1 -->
<%-- <link rel="stylesheet" href="${jqueryUI_css}"> --%>

<!-- jQuery : 3.4.1 -->
<script src="${jquery}" charset="UTF-8"></script>

<!-- jQueryUI : 1.12.1 -->
<%-- <script src="${jqueryUI}" charset="UTF-8"></script> --%>

<!-- popper.js -->
<script src="${popper_js}" charset="UTF-8"></script>

<!-- bootstrap JS : 4.4.1 -->
<script src="${bootstrap_js}" charset="UTF-8"></script>

<!-- AngularJS : 1.7.8 -->
<script src="${angularjs}" charset="UTF-8"></script>

<style>
section#writeForm {
	width: 600px;
}

/* 글내용 쓰기 필드 크기 조절 방지 */
textarea {
	resize: none;
}

/* 에러 메시지 글자 : tr */
tr.err_msg {
	color:red;
	text-indent: 6em;
}
</style>

<script>
     // AngularJS
	 var app=angular.module('boardFormApp', []); 
     
	 app.controller('boardFormController', function($scope) {
			
		// 최종  폼점검 
		$scope.formFinalCheck = function($event) {
			
			alert("폼점검");
			
			if ($scope.boardForm.boardName.$valid == true &&
				$scope.boardForm.boardPass.$valid == true &&
				$scope.boardForm.boardSubject.$valid == true &&
				$scope.boardForm.boardContent.$valid == true) 
			{
				document.boardForm.action="${rootPath}/board/writeProc.do"; 
		  		document.boardForm.submit();
		  		
			} else {
				
				alert("폼점검 미완료")
				document.boardForm.boardName.focus(); 
			} //
			
		} //
	 });

	 // jQuery
	 /* $(function() {
		 
		   // jQueryUI tooltip(component help)
		   // $(document).tooltip();
		 
		   // bootstrap tooltip(component help) 
		   var tooltips=$("[title]").tooltip({
		     position: {
		       my: "left top",
		       at: "right+5 top-5",
		       collision: "none"
		     }
		   }); 
		   
	 }); // jQuery */
	 
	 $(function() {
		

		// "게시글 목록" 이동 버튼
		const rootPath = "/board_project";
		 
		$("#board_list_btn").click(function(e) {
			alert("게시글 목록");
			location.href = rootPath+"/board/viewAll.do";
		}); //
		
	 }); 
  </script>
</head>

<body ng-app="boardFormApp" ng-controller="boardFormController">

	<!-- 게시판 등록 시작 -->
	<section id="writeForm" class="container">

		<h3>게시판 글등록</h3>

		<form id="boardForm"
			  name="boardForm" 
			  method="post" 
			  enctype="multipart/form-data" 
	          ng-model="boardForm" 
	          ng-submit="formFinalCheck()">

			<!-- bootstrap table 적용 -->
			<table class="table table-striped">

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
							   ng-model="boardName" 
							   required="true"
							   class="form-control" 
							   title="게시글 작성자를  2~15자 이내로 입력하십시오"
							   ng-maxlength="15" 
							   ng-minlength="2" /></td>
				</tr>

				<!-- 글쓴이 에러 메시징 시작 -->
				<tr id="boardName_err" class="err_msg"
					ng-show="boardForm.boardName.$error.maxlegnth || 
							 boardForm.boardName.$error.minlength">
					<td colspan="2" class="col-xs-12">게시글 작성자를 2~15자 이내로 입력하십시오</td>
				</tr>
				<!-- 글쓴이 에러 메시징 끝 -->

				<tr>
					<td>
						<label for="boardPass" class="control-label"> 
							<span style="color:red">*</span>비밀번호
						</label>
					</td>
					<td><input type="password" 
							   id="boardPass" 
							   name="boardPass"
							   ng-model="boardPass" 
							   class="form-control"
							   title="게시글 패쓰워드를 8~20자 이내로 입력하십시오" 
							   ng-maxlength="20"
							   ng-minlength="8" 
							   required="true" /></td>
				</tr>

				<!-- 패쓰워드 에러 메시징 시작 -->
				<tr id="boardPass_err" class="err_msg"
					ng-show="boardForm.boardPass.$error.maxlength || 
							 boardForm.boardPass.$error.minlength">
					<td colspan="2" class="col-xs-12">게시글 패쓰워드를 8~20자 이내로 입력하십시오</td>
				</tr>
				<!-- 패쓰워드 에러 메시징 끝 -->

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
							   title="글제목은 2~50자이내로 적습니다" 
							   ng-model="boardSubject"
							   ng-maxlength="50" 
							   ng-minlength="2" 
							   required="true" /></td>
				</tr>

				<!-- 게시글 제목 에러 메시징 시작 -->
				<tr id="boardSubject_err" class="err_msg"
					ng-show="boardForm.boardSubject.$error.maxlength || 
							 boardForm.boardSubject.$error.minlength">
					<td colspan="2" class="col-xs-12">글제목은 2~50자이내로 적습니다</td>
				</tr>
				<!-- 게시글 제목 에러 메시징 끝 -->

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
								  title="글내용은 2~1000자이내로 적습니다" 
								  class="form-control"
								  ng-model="boardContent" 
								  ng-maxlength="1000" 
								  ng-minlength="2"
								  required="true"></textarea>
				  	</td>
				</tr>

				<!-- 게시글 내용 에러 메시징 시작 -->
				<tr id="boardContent_err" class="err_msg"
					ng-show="boardForm.boardContent.$error.maxlength || 
							 boardForm.boardContent.$error.minlength">
					<td colspan="2" class="col-xs-12">글내용은 2~1000자이내로 적습니다</td>
				</tr>
				<!-- 게시글 내용 에러 메시징 끝 -->

				<tr>
					<td>
						<label for="boardFile" class="control-label">파일 첨부</label>
					</td>
					<td>
						<input type="file" 
							   id="boardFile" 
							   name="boardFile"
							   class="form-control" 
							   title="첨부 파일을 입력하십시오" />
				    </td>
				</tr>

			</table>

			<section id="commandCell">

				<input type="submit" 
					   class="btn btn-info" 
					   value="등록"
					   ng-disabled="boardForm.$invalid">
					   &nbsp;&nbsp; 
			    <input type="reset" 
			    	   class="btn btn-info" 
			    	   value="다시쓰기" />
			    	   
	    	    <input type="button" id="board_list_btn" value="게시글 목록" 
	    	    	   class="btn btn-outline-primary ml-3" />

			</section>

		</form>

	</section>
	<!-- 게시판 등록 끝 -->

</body>
</html>