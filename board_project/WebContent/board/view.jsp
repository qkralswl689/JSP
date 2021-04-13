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

<script>
//게시글 제목을 클릭하였을 때 모달 보여주기
$(function() {
	
	// 주의사항) 코드 단절 현상으로 인해 EL변수(가령 ${rootPath} 등) 적용 안됨 !
	// 대체 변수 -> javascript 변수
	// Context Path 변수 
	const rootPath = "/board_project";
	
	// 초기 상태 모달"들" 감추기
	$("#board_modal").hide();
	
	/////////////////////////////////////////////////////////////
	
	// 게시글 조회 모달 창닫기
	// modal 출력
	$("#board_modal [id^=modal_close_btn]").click(function(){ // 창닫기 버튼 2개
		$("#board_modal").hide();
		// 화면 닫은 후 최근 현황 반영 위해 강제 refresh
		location.reload();
	});
	
	// 게시글 보기
	$("a[id^=subject_]").click(function(e) {
		
		// 다른 모달"들" 닫기 : 추가
		// $("#board_update_modal").hide();
		// $("#board_reply_modal").hide(); 
        // $("#board_delete_modal").hide();
	
	    // ex) 모달 버튼 아이디 : modal_update_btn_${boardVO.boardNum}
		var id = e.currentTarget.id;
		id = id.substring("subject_".length, id.length);
		console.log("게시글 id : "+ id);
		
		// AJAX
		$.ajax ({

   			 // 코드 단절 현상으로 인해 EL변수 부적용 ! 
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
				console.log(json[0].boardNum);
				console.log(json[0].boardDate);
				
				// 각 컴포넌트에 표시
				// 게시글 번호
				$("#board_modal #boardNum").val(json[0].boardNum);
				
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
				$("#board_modal #boardDate").text(formatedDate);
				
				// 게시글 작성자
				$("#board_modal #boardName").val(json[0].boardName);
				
				// 게시글 비밀번호  생략
				
				// 게시글 제목
				$("#board_modal #boardSubject").val(json[0].boardSubject);
				
				// 게시글 내용
				$("#board_modal #boardContent").val(json[0].boardContent);
				
				// 첨부 파일
				var originalFileName = json[1]; // 원본 파일명
				
				///////////////////////////////////////////////////////////////////////
				
				// 댓글 관련 히든 필드 추가 및 인자 할당
				$("form#boardUpdateForm [name=boardReLev]").val(json[0].boardReLev);
				
				// 추가 : 원글/댓글 수정 여부에 따라 첨부 파일 표현 차별화
				// 구별 기준 : board_re_lev > 0 이면 댓글, board_re_lev == 0 이면 원글 
				if (json[0].boardReLev == 0) { // 원글이면 
				
					if (originalFileName == "") { // 첨부 파일 없을 경우
						
						$("#board_modal #download_link").text("첨부 파일 없음");
						
					} else { // 첨부 파일 있을 경우
						
						$("#board_modal #download_link").text(""); // 초기화
						
						var hrefLink = rootPath+"/board/download.do?uploadFile="+json[0].boardFile
					    			 + "&boardNum="+json[0].boardNum;
						
						var downloadLink = "<a href='"+hrefLink+"'>" + originalFileName + "</a>"
						$("#board_modal #download_link").append(downloadLink);
					} //				
				
				} else { // 댓글일 경우 : 첨부 파일 없음 !
					
					// 마지막 파일 첨부 출력부(tr) 제거
					$("#board_modal #viewForm table tr:last").remove();
				} //
					
				// 추가 : 게시글 수정 버튼 아이디(id)를  변경하여 게시글 번호(boardNum)을 전송할 수 있도록 조치
				/*
				var update_btn_id = $("input[type=button][id^=modal_update_btn_]").attr("id");
				console.log("아이디 : " + update_btn_id + ", " + json[0].boardNum);
				
				$("#"+update_btn_id).attr("id", "modal_update_btn_"+json[0].boardNum);
				
				// 추가 : 게시글 답글(댓글) 작성 버튼 아이디(id)를  변경하여 게시글 번호(boardNum)을 전송할 수 있도록 조치
				var reply_btn_id = $("input[type=button][id^=modal_reply_btn_]").attr("id");
				console.log("아이디 : " + reply_btn_id + ", " + json[0].boardNum);
				
				$("#"+reply_btn_id).attr("id", "modal_reply_btn_"+json[0].boardNum);
				
				///////////////////////////////////////////////////////////////////////////////
				
				// 추가 : 게시글 삭제 버튼 아이디(id)를  변경하여 게시글 번호(boardNum)을 전송할 수 있도록 조치
				var delete_btn_id = $("input[type=button][id^=modal_delete_btn_]").attr("id");
				console.log("아이디 : " + delete_btn_id + ", " + json[0].boardNum);
				
				$("#"+delete_btn_id).attr("id", "modal_delete_btn_"+json[0].boardNum);
				*/
				
			}, // success
			 
			error : function(xhr, status) {
				console.log(xhr+" : "+status); // 에러 코드
			} //

		}); // $.ajax
		
		$("#board_modal").show(); // 모달 보여주기
	});
	
}); // main	
</script>

</head>
<body>

	<a href="#" id="subject_2">개별 게시글 보기</a>

	<!-- 모달 박스 -->
	<div id="board_modal">
	    
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
				개별 게시글 조회
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
	
			<!-- 개별 게시글 조회 시작 -->
			<section id="viewForm" class="container">
			
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
							<!-- 날짜 출력부 추가 -->
							<span id="boardDate"></span>
							<%-- <fmt:formatDate value="${boardVO.boardDate}" 
											    pattern="yyyy년  M월 d일 hh:mm:ss" /> --%>			
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
							<label for="boardSubject" class="control-label">
								<span style="color:red">*</span>제 목
							</label>
						</td>
						<td>
							<input type="text" 
								   id="boardSubject" 
								   name="boardSubject" 
								   class="form-control rounded-sm bg-light"
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
									  rows="5"
									  class="form-control rounded-sm bg-light"
									  readonly>${boardVO.boardContent}</textarea>
					  	</td>
					</tr>
		
					<tr>
						<td>
							<label for="boardFile" class="control-label">파일 첨부</label>
						</td>
						<td>
						    <!-- AJAX 버전 -->
							<span id="download_link"></span>							
					    </td>
					</tr>
		
				</table>
		
			</section>
			<!-- 개별 게시글 조회 끝 -->
			
		</div>
		<!--// modal content -->
		
		<!-- modal menu -->
		<div id="modal_footer" class="mb-3 mt-0">
			<input type="button" id="modal_update_btn_" class="btn btn-outline-primary" value="게시글 수정" />
			<input type="button" id="modal_reply_btn_" class="btn btn-outline-primary" value="답글쓰기" />
			<input type="button" id="modal_delete_btn_" class="btn btn-outline-primary" value="게시글 삭제" />
			<input type="button" id="modal_close_btn2" class="btn btn-primary" value="닫기"/>
		</div>
		<!--// modal menu -->
	</div>	

</body>
</html>