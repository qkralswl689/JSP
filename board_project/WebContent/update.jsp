<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>개별 게시글 보기(AJAX)</title>

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
// 게시글 제목을 클릭하였을 때 모달 보여주기
$(function() {
	
	// 주의사항) 코드 단절 현상으로 인해 EL변수(가령 ${rootPath} 등) 적용 안됨 !
	// 대체 변수 -> javascript 변수
	// Context Path 변수 
	const rootPath = "/board_project";
	
	// 초기 상태 모달"들" 감추기
	// $("#board_modal").hide();
	$("#board_update_modal").hide();	
	
	/////////////////////////////////////////////////////////////
	
	// 게시글 조회 모달 창닫기
	// modal 출력
	$("#board_modal [id^=modal_close_btn]").click(function(){ // 창닫기 버튼 2개
		$("#board_modal").hide();
		// 화면 닫은 후 최근 현황 반영 위해 강제 refresh
		location.reload();
	});
	
	// 게시글 수정 모달
	$("[id^=modal_update_btn]").click(function(e) {
		
		/////////////////////////////////
		//
		// 테스트 모듈용 : 수정 게시글 번호 입력
		//
		/////////////////////////////////
		var id = prompt("수정할 게시글을 입력");
		
		// alert("게시글 수정");
		// 다른 모달"들" 닫기 : 추가
		// $("#board_modal").hide(); 
		// $("#board_reply_modal").hide();
        // $("#board_delete_modal").hide();

	    // ex) 모달 버튼 아이디 : modal_update_btn_${boardVO.boardNum}
		// var id = e.currentTarget.id; // 테스트용 위해 임시 생략 (prompt로 게시글 번호 입력)
		// console.log("버튼 아이디 : "+id);
		// id = id.substring("modal_update_btn_".length, id.length);
		
		console.log("게시글 id : "+ id);
		
		// 기존 회원정보 로딩 (회원 조회와 동일한 방식)
		// AJAX
		$.ajax ({

			 // url : '${rootPath}/board/viewAjax.do',
			 url : rootPath+'/board/viewAjax.do',
			 
			 type : 'post',
			 dataType:'text',
			 data : {
				 boardNum : id
			 },
			 success : function(data) {
			   
				console.log("수신 성공"); 
				console.log(data);

				var json = JSON.parse(data); 
				console.log(json.boardNum);
				console.log(json.boardDate);
				
				// 각 컴포넌트에 표시
				// 게시글 번호
				$("#board_update_modal #boardNum").val(json[0].boardNum);
				
				// 게시글 작성일
				var boardDate = new Date(json[0].boardDate);				
				console.log(new Date(json[0].boardDate));
				
				// 시간 포맷 옵션
				// 참고) https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/DateTimeFormat
				var options = {
							  year: 'numeric', month: 'numeric', day: 'numeric',
							  hour: 'numeric', minute: 'numeric', second: 'numeric',
							  hour12: false,
							  timeZone: 'Asia/Seoul' 
						  };
				console.log(new Intl.DateTimeFormat('ko-KR', options).format(boardDate));
				
				var formatedDate = new Intl.DateTimeFormat('ko-KR', options).format(boardDate);
				$("#board_update_modal #boardDate").text(formatedDate);
				
				// 게시글 작성자
				$("#board_update_modal #boardName").val(json[0].boardName);
				
				// 게시글 비밀번호  생략
				
				// 게시글 제목
				$("#board_update_modal #boardSubject").val(json[0].boardSubject);
				
				// 게시글 내용
				$("#board_update_modal #boardContent").val(json[0].boardContent);
				
				////////////////////////////////////////////////////////////////
				
				// 댓글 관련 히든 필드 추가 및 인자 할당
				$("form#boardUpdateForm [name=boardReLev]").val(json[0].boardReLev);
				
				// 추가 : 원글/댓글 수정 여부에 따라 첨부 파일 표현 차별화
				// 구별 기준 : board_re_lev > 0 이면 댓글, board_re_lev == 0 이면 원글 
				if (json[0].boardReLev == 0) { // 원글이면 
					
					// 파일 필드 추가
					var fileFld = "<tr> "
								+ " <td>"
								+ " 	<label for=\"boardFile\" class=\"control-label\">파일 첨부</label>"
								+ " </td>"
								+ " <td>"
								+ " 	<span id=\"download_link\"></span>"		
								+ "	 		<div class=\"custom-file\"> "	   		
								+ "				<input type=\"file\" "
								+ "			   		id=\"boardFile\" "
								+ "			   		name=\"boardFile\" "
								+ "			   		class=\"custom-file-input\" " 
								+ "			   		title=\"첨부 파일을 입력하십시오\" />	"  
							    + "				<label class=\"custom-file-label\" for=\"customFile\">Choose file</label>"	   
								+ "			</div>"	   
							    + "	</td>"
							    + "</tr>"
					
				    // 파일 필드 추가
				    $("form#boardUpdateForm table").append(fileFld);
			
// 첨부 파일
					var originalFileName = json[1]; // 원본 파일명
					
					if (originalFileName == "") { // 첨부 파일 없을 경우
						
						$("#board_update_modal #download_link").text("첨부 파일 없음");
						
					} else { // 첨부 파일 있을 경우
						
						$("#board_update_modal #download_link").text(""); // 초기화
						//var hrefLink = "${rootPath}/board/download.do?uploadFile="+json[0].boardFile
					    //			 + "&boardNum="+json[0].boardNum;
						
						var hrefLink = rootPath +"/board/download.do?uploadFile="+json[0].boardFile
					    			 + "&boardNum="+json[0].boardNum;
						
						var downloadLink = "<a href='"+hrefLink+"'>" + originalFileName + "</a>"
						$("#board_update_modal #download_link").append(downloadLink);
					} //		
				
				} // 댓글이면 첨부 파일 내용을 미출력.
				
				////////////////////////////////////////////////////////////////
				
				// 수정 전송 버튼 아이디 변경
				// 추가 : 게시글 수정 버튼 아이디(id)를  변경하여 게시글 번호(boardNum)을 전송할 수 있도록 조치
				// 테스트용을 위해 임시 생략
				/* 
				var update_submit_btn_id = $("input[type=button][id^=update_submit_btn_]").attr("id");
				console.log("수정 전송 버튼 아이디 : " + update_submit_btn_id + ", " + json[0].boardNum);
				
				$("#"+update_submit_btn_id).attr("id", "update_submit_btn_"+json[0].boardNum);
				
				// 추가 : 게시글 답글(댓글) 작성 버튼 아이디(id)를  변경하여 게시글 번호(boardNum)을 전송할 수 있도록 조치
				var reply_btn_id = $("input[type=button][id^=modal_reply_btn_]").attr("id");
				console.log("아이디 : " + reply_btn_id + ", " + json[0].boardNum);
				
				$("#"+reply_btn_id).attr("id", "modal_reply_btn_"+json[0].boardNum);
				*/
			   
			}, // success
			 
			error : function(xhr, status) {
				console.log(xhr+" : "+status); // 에러 코드
			}

		}); // $.ajax
		
		// 게시글 수정 모달 보이기
		$("#board_update_modal").show();
	});	
	
	/////////////////////////////////////////////////////////////////////////
	
	// 게시글 수정 전송시
	$("[id^=update_submit_btn_]").click(function(e) {
		
		alert("수정 전송");
		
		 // ex) 모달 버튼 아이디 : update_submit_btn_${boardVO.boardNum}
		var id = e.currentTarget.id;
		console.log("수정 전송 버튼 아이디 : "+id);
		id = id.substring("update_submit_btn_".length, id.length);
		console.log("게시글 id : "+ id);
		
		// 폼점검 : regex(정규 표현식)
		// 게시글 비밀번호 : /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{8,20}/
		// 게시글 제목 : /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{2,50}/
		// 글내용 : /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{2,2000}/
		
		var flag = 0; // 누산 에러 플래그 
		// 에러 플래그 합계가 3이면 전송 : 점검할 항목 개수(패쓰워드 + 제목 + 글내용)
		
		// 패쓰워드 필드 점검
		var regexPw = /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{8,20}/;
		var passFld = $("#board_update_modal [name=boardPass]").val();
		
		if (regexPw.test(passFld)) {
			$("#board_update_modal #update_boardPass_err").text("");
			flag++;
		} else {
			var titlePass = $("#board_update_modal #boardPass").attr('title');
			$("#board_update_modal #update_boardPass_err").text(titlePass);             
		} //
		
		// 글제목 필드 점검
		var regexSubject = /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{2,50}/;
		var subjectFld = $("#board_update_modal [name=boardSubject]").val();
		
		if (regexSubject.test(subjectFld)) {
			$("#board_update_modal #update_boardSubject_err").text("");
			flag++;
		} else {
			var titleSubject = $("#board_update_modal #boardSubject").attr('title');
			$("#board_update_modal #update_boardSubject_err").text("게시글 제목을 2~50자 이내로  입력하십시오");             
		} //
		
		// 글내용 필드 점검
		var regexContent = /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{2,2000}/;
		var contentFld = $("#board_update_modal [name=boardContent]").val();
		
		if (regexContent.test(contentFld)) {
			$("#board_update_modal #update_boardContent_err").text("");
			flag++;
		} else {
			var titleContent = $("#board_update_modal #boardContent").attr('title');
			$("#board_update_modal #update_boardContent_err").text(titleContent);             
		} //
						
		// 점검 플래그 인쇄
		console.log("flag : "+flag);
		
		// 전송 부분 : AJAX
		if (flag == 3) {
			
			console.log("전송");
			
			// AJAX
			var form = $('form#boardUpdateForm')[0]; // 배열 첫번째 인덱스 출력
	        var formData = new FormData(form);
	        
	        $.ajax ({
	        	
				// url : "${rootPath}/board/updateProc.do",
	        	url : rootPath+"/board/updateProc.do",
				
				cache: false,	 
	            async: false,
	            contentType: false,
	            processData: false,
	            
				type : "POST",
				data : formData,
				
				success : function (result) {
				
					alert("결과 : "+result);
					
					if (result.trim() == '게시글 수정에 성공하였습니다.') {
						location.reload(); // 페이지 리프레시(재설정)
					} else {
						// 패쓰워드 초기화 & focus
						$("form#boardUpdateForm #boardPass").val("");
						$("form#boardUpdateForm #boardPass").focus();
					} //
				}, 
				
				error : function(xhr, status) {
	                 
	                console.log(xhr+" : "+status); // 에러 코드
	            } 
			}); // ajax
			
		} else { // 폼점검 미완료시
			
			alert("필수 입력란을 조건에 맞게 완성하십시오.")
		} //
		
	}); //
	
}); // main	
</script>

</head>
<body>

	<a href="#" id="modal_update_btn">개별 게시글 수정</a>

	<!-- 모달 박스 -->
	<div id="board_update_modal">
	    
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
				게시글 수정
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
	
			<!-- 개별 게시글 수정 시작 -->
			<section id="viewForm" class="container">
		
				<!-- 추가 : 폼단위 전송 위한 조치  -->
				<form id="boardUpdateForm" name="boardUpdateForm">
				
					<!-- //////////////////////////////////////////////// -->
					<!-- 히든 필드 : 댓글 관련 필드-->
				    <input type="text" name="boardReLev" /><br>
				    <!-- //////////////////////////////////////////////// -->
				    		
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
									   readonly 
									   value="${boardVO.boardNum}"/>
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
								<span id="boardDate"></span>			
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
									   class="form-control rounded-sm bg-light"
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
							    <!-- 변경 가능 -->
								<input type="text" 
									   id="boardPass" 
									   name="boardPass"
									   class="form-control rounded-sm bg-light" 
									   title="게시글 패쓰워드를 8~20자 이내로 입력하십시오" 
									   maxlength="20"								    
									   required />
								
								<!-- 패쓰워드 에러 메시징 시작 -->	   
							    <span id="update_boardPass_err" class="err_msg"></span>
							    <!-- 패쓰워드 에러 메시징 끝 -->								   
						    </td>
						</tr> 
			
						<tr>
							<td>
								<label for="boardSubject" class="control-label">
									<span style="color:red">*</span>제 목
								</label>
							</td>
							<td>
								<!-- 변경 가능 -->
								<input type="text" 
									   id="boardSubject" 
									   name="boardSubject"
									   class="form-control rounded-sm bg-light"
									   value="${boardVO.boardSubject}"/>
									   
								<!-- 글제목 에러 메시징 시작 -->	   
							    <span id="update_boardSubject_err" class="err_msg"></span>
							    <!-- 글제목 에러 메시징 끝 -->	   
						    </td>
						</tr>
			
						<tr>
							<td>
								<label for="boardContent" class="control-label">
									<span style="color:red">*</span>내 용
								</label>
							</td>
							<td>
								<!-- 변경 가능 -->
								<textarea id="boardContent"
										  name="boardContent"
										  cols="40" 
										  rows="5" 
										  class="form-control rounded-sm bg-light"
										  title="글내용은 2~1000자이내로 적습니다" 
									  	  class="form-control"
									  	  maxlength="1000" 								  	  
										  required>${boardVO.boardContent}</textarea>
								
								<!-- 글내용 에러 메시징 시작 -->	   
							    <span id="update_boardContent_err" class="err_msg"></span>
							    <!-- 글내용 에러 메시징 끝 -->			  
						  	</td>
						</tr>
						
					</table>
				
				</form> <!--// 추가 : 폼단위 전송 위한 조치  -->
		
			</section>
			<!-- 개별 게시글 수정 끝 -->
			
		</div>
		<!--// modal content -->
		
		<!-- modal menu -->
		<div id="modal_footer" class="mb-3 mt-0">
			<input type="button" id="update_submit_btn_" class="btn btn-outline-primary" value="수정 전송" />
			<input type="button" id="modal_reply_btn_" class="btn btn-outline-primary" value="답글쓰기" />
			<input type="button" class="btn btn-outline-primary" value="게시글 삭제" />
			<input type="button" id="modal_close_btn2" class="btn btn-primary" value="닫기"/>
		</div>
		<!--// modal menu -->
	</div>
</body>
</html>