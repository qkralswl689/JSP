<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>개별 게시글 삭제(AJAX)</title>

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

<!-- 추가/변환 : 게시판 자체 CSS -->
<link rel="stylesheet" href="${rootPath}/css/board.css">

<script>
//게시글 제목을 클릭하였을 때 모달 보여주기
$(function() {
	
	// 주의사항) 코드 단절 현상으로 인해 EL변수(가령 ${rootPath} 등) 적용 안됨 !
	// 대체 변수 -> javascript 변수
	// Context Path 변수 
	const rootPath = "/board_project";
	
	$("#board_delete_modal").hide();
	
	// 삭제글 modal 창닫기
	// 삭제글 modal 출력
	$("#board_delete_modal [id^=modal_close_btn]").click(function(){ // 창닫기 버튼 2개
		
		$("#board_delete_modal").hide();
		// 화면 닫은 후 최근 현황 반영 위해 강제 refresh
		location.reload();
	});
	
	// 게시글 삭제 모달 
	$("[id^=modal_delete_btn_").click(function(e) {
		
		// alert("게시글 삭제");
		
		// ex) 모달 버튼 아이디 : modal_delete_btn_${boardVO.boardNum}
		// var id = e.currentTarget.id;
		// id = id.substring("modal_delete_btn_".length, id.length);
		// console.log("게시글 id : "+ id);
		
		// 모달 아이디 항목에  게시글 아이디 대입(할당) : 원글/답글 게시글 번호
		// 직접 입력 위해 임시 생략
		// $("#board_delete_modal #deleteForm [name=boardNum]").val(id);
		
		// 다른 모달"들" 닫기 
		// $("#board_modal").hide(); 
		// $("#board_update_modal").hide();
		// $("#board_reply_modal").hide();
		
		// 게시글 삭제 모달 출력
		// 화면 "정중앙"에 띄우도록 조치
		
		console.log("가용(viewport) 화면 : "+$(window).width() + ", " + $(window).height());
		console.log("전체 스크린 화면 : "+ screen.width + ", " + screen.height);
		
		console.log("모달 크기 : "+$("#board_delete_modal").width() +", " + $("#board_delete_modal").height())
		
		// 정중앙 좌표 환산
		var modalX = $(window).width()/2 - $("#board_delete_modal").width()/2;
		var modalY = $(window).height()/2 - $("#board_delete_modal").height()/2;
		
		console.log("정중앙 모달 기준 좌표 : "+modalX + ", " +modalY);
		
		$("#board_delete_modal").offset({ left: modalX, top: modalY });
		$("#board_delete_modal").show();
		
		console.log("모달 좌표(x) : "+$("#board_delete_modal").offset().left);
		console.log("모달 좌표(y) : "+$("#board_delete_modal").offset().top);
		
	}); //
	
	// 게시글 삭제 처리
	$("#delete_submit_btn").click(function(e) {
		
		// 폼점검
		// 패쓰워드 필드 점검
		var regexPw = /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{8,20}/;
		var passFld = $("#board_delete_modal [name=boardPass]").val();
		
		var flag = false; // 폼점검 플래그
		
		if (regexPw.test(passFld)) {
			$("#board_delete_modal #delete_boardPass_err").text("");
			flag = true;			
		} else {
			var titlePass = $("#board_delete_modal [name=boardPass]").attr('title');
			$("#board_delete_modal #delete_boardPass_err").text(titlePass);
			flag = false;
		} //
		
		// 플래그 점검 전송
		if (flag == true) {
			
			alert("전송");
			
			// 삭제 아이디 전송
			$.ajax ({
	
				 url : rootPath+'/board/deleteProc.do',
				 type : 'post',
				 dataType:'text',
				 data : {
					 boardNum : $("#board_delete_modal [name=boardNum]").val(),
					 boardPass : $("#board_delete_modal [name=boardPass]").val()
				 },
				 success : function(data) {
				   
					console.log("수신 성공 : " + data); 
					console.log(data);
	
					alert(data);		
					
					// 패쓰워드 초기화
					$("#board_delete_modal #deleteForm [name=boardPass]").val("");

				 }, // success
				 error : function(xhr, status) {
					console.log(xhr+" : "+status); // 에러 코드
				 }
	
			}); // $.ajax
			
			
		} else {
			alert("필수 입력란을 조건에 맞게 완성하십시오.")
		} //
		
	}); //
	
}); // main	
</script>

</head>
<body>

	<a href="#" id="modal_delete_btn_">개별 게시글 삭제</a>

	<!-- 삭제 모달 박스 -->
	<div id="board_delete_modal">
	    
	    <!-- modal header -->
		<div id="modal_header">
		
			<!-- bullet -->
		    <div id="modal_header_icon">
				<span class="material-icons">
		        	web_asset
		        </span>		        
	        </div>
	        
	        <!-- title -->
	        <div id="modal_header_title">
				게시글 삭제
			</div>
			
			<!-- closing button icon -->
			<div id="modal_header_close">
			    <a href="#" id="modal_close_btn">
					<span class="material-icons" style="color:#333">
			        	clear
			        </span>
		        </a>
			</div>
		</div>
<!-- modal content -->
		<div id="modal_body">
	
			<!-- 게시글 삭제 작성 시작 -->
			<section id="deleteForm" class="container">
		
				<!-- bootstrap table 적용 -->
				<table class="table">
				
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
								   class="form-control rounded-sm bg-light"
								   /> <!-- readonly 임시 해제 -->
					    </td>
					</tr>
					
					<tr>
						<td>
							<label for="boardPass" class="control-label"> 
								<span style="color:red">*</span>비밀번호
							</label>
						</td>
						<td>
						    <!-- 변경 가능 -->
							<input type="text" 
								   id="boardPass" 
								   name="boardPass"
								   class="form-control rounded-sm bg-light" 
								   title="게시글 패쓰워드를 8~20자 이내로 입력하십시오" 
								   maxlength="20"								    
								   required />
							
							<!-- 패쓰워드 에러 메시징 시작 -->	   
						    <span id="delete_boardPass_err" class="err_msg"></span>
						    <!-- 패쓰워드 에러 메시징 끝 -->								   
					    </td>
					</tr> 
		
				</table>
		
			</section>
			<!-- 게시글 삭제 작성 끝 -->
			
		</div>
		<!--// modal content -->
		
		<!-- modal menu -->
		<div id="modal_footer" class="mb-3 mt-0">
			<input type="button" id="delete_submit_btn" class="btn btn-outline-primary" value="삭제 전송" />
			<input type="button" id="modal_close_btn2" class="btn btn-primary" value="닫기"/>
		</div>
		<!--// modal menu -->
		
	</div>	

</body>
</html>