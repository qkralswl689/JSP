/**
 * 게시판 모달 관련 javascript
 */

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

// 게시글 제목을 클릭하였을 때 모달 보여주기
$(function() {
	
	// 주의사항) 코드 단절 현상으로 인해 EL변수(가령 ${rootPath} 등) 적용 안됨 !
	// 대체 변수 -> javascript 변수
	// Context Path 변수 
	const rootPath = "/board_project";
	
	// 초기 상태 모달"들" 감추기 (통합)
	$("#board_modal").hide();
	$("#board_update_modal").hide();
	$("#board_reply_modal").hide();
	
	// 추가 : 게시글 삭제 모달
	$("#board_delete_modal").hide();
	
	// 삭제 패쓰워드 초기화
	$("#board_delete_modal #deleteForm [name=boardPass]").val("");
	
	/////////////////////////////////////////////////////////////
	
	// 게시글 보기
	$("a[id^=subject_]").click(function(e) {
		
		// 다른 모달"들" 닫기 : 추가
		$("#board_update_modal").hide();
		$("#board_reply_modal").hide(); 
        $("#board_delete_modal").hide();
	
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
				
			}, // success
			 
			error : function(xhr, status) {
				console.log(xhr+" : "+status); // 에러 코드
			} //

		}); // $.ajax
		
		$("#board_modal").show(); // 모달 보여주기
	});

	// 게시글 수정 모달
	$("[id^=modal_update_btn]").click(function(e) {
		
		// alert("게시글 수정");
		// 다른 모달"들" 닫기 : 추가
		$("#board_modal").hide(); 
		$("#board_reply_modal").hide();
        $("#board_delete_modal").hide();

	    // ex) 모달 버튼 아이디 : modal_update_btn_${boardVO.boardNum}
		var id = e.currentTarget.id;
		console.log("버튼 아이디 : "+id);
		id = id.substring("modal_update_btn_".length, id.length);
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
				var update_submit_btn_id = $("input[type=button][id^=update_submit_btn_]").attr("id");
				console.log("수정 전송 버튼 아이디 : " + update_submit_btn_id + ", " + json[0].boardNum);
				
				$("#"+update_submit_btn_id).attr("id", "update_submit_btn_"+json[0].boardNum);
				
				// 추가 : 게시글 답글(댓글) 작성 버튼 아이디(id)를  변경하여 게시글 번호(boardNum)을 전송할 수 있도록 조치
				var reply_btn_id = $("input[type=button][id^=modal_reply_btn_]").attr("id");
				console.log("아이디 : " + reply_btn_id + ", " + json[0].boardNum);
				
				$("#"+reply_btn_id).attr("id", "modal_reply_btn_"+json[0].boardNum);
				
			   
			}, // success
			 
			error : function(xhr, status) {
				console.log(xhr+" : "+status); // 에러 코드
			}

		}); // $.ajax
		
		// 게시글 수정 모달 보이기
		$("#board_update_modal").show();
	});	
	
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
			var form = $('form#boardUpdateForm')[0];
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
	
	// 답글(댓글) 작성
	$("[id^=modal_reply_btn]").click(function(e) {
		
		alert("답글(댓글) 작성");
		
		// ex) 모달 버튼 아이디 : modal_reply_btn_${boardVO.boardNum}
		var id = e.currentTarget.id;
		id = id.substring("modal_reply_btn_".length, id.length);
		console.log("게시글 id : "+ id);
		
		// 다른 모달"들" 닫기 : 추가
		$("#board_modal").hide(); 
		$("#board_update_modal").hide();
        $("#board_delete_modal").hide();
		
		// 답글(댓글) 모달 열기
		$("#board_reply_modal").show();
		
		// 원글 게시글 아이디 대입(설정)
		$("#replyForm #boardNum").val(id);
		
	}); // 
	
	// 답글 취소(초기화)
	$("#reply_reset_btn").click(function(e) {
		
		alert("댓글 초기화");
		
		$("#board_reply_modal [name=boardName]").val("");
		$("#board_reply_modal [name=boardPass]").val("");
		$("#board_reply_modal [name=boardSubject]").val("");
		$("#board_reply_modal [name=boardContent]").val("");
	});
	
	// 답글 전송 처리
	$("#reply_submit_btn").click(function(e) {
		
		alert("댓글 전송 처리");
		
		// 폼점검 : regex(정규 표현식)
		// 답글(댓글) 작성자 : /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{2,15}/ 
		// 게시글 비밀번호 : /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{8,20}/
		// 게시글 제목 : /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{2,50}/
		// 글내용 : /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{2,2000}/
		
		var flag = 0; // 누산 에러 플래그 
		// 에러 플래그 합계가 4이면 전송 : 점검할 항목 개수(작성자 + 패쓰워드 + 제목  + 글내용)
		
		// 답글(댓글) 작성자 필드 점검
		var regexName = /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{2,15}/;
		var nameFld = $("#board_reply_modal [name=boardName]").val();
		
		if (regexName.test(nameFld)) {
			$("#board_reply_modal #reply_boardName_err").text("");
			flag++;
		} else {
			var titleName = $("#board_reply_modal #boardName").attr('title');
			$("#board_reply_modal #reply_boardName_err").text(titleName);             
		} //
		
		// 패쓰워드 필드 점검
		var regexPw = /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{8,20}/;
		var passFld = $("#board_reply_modal [name=boardPass]").val();
		
		if (regexPw.test(passFld)) {
			$("#board_reply_modal #reply_boardPass_err").text("");
			flag++;
		} else {
			var titlePass = $("#board_reply_modal #boardPass").attr('title');
			$("#board_reply_modal #reply_boardPass_err").text(titlePass);             
		} //
		
		// 글제목 필드 점검
		var regexSubject = /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{2,50}/;
		var subjectFld = $("#board_reply_modal [name=boardSubject]").val();
		
		if (regexSubject.test(subjectFld)) {
			$("#board_reply_modal #reply_boardSubject_err").text("");
			flag++;
		} else {
			var titleSubject = $("#board_reply_modal #boardSubject").attr('title');
			$("#board_reply_modal #reply_boardSubject_err").text("게시글 제목을 2~50자 이내로  입력하십시오");             
		} //
		
		// 글내용 필드 점검
		var regexContent = /[\w | \W | 가-힣  | / | - |  (  |  ) | , | ]{2,2000}/;
		var contentFld = $("#board_reply_modal [name=boardContent]").val();
		
		if (regexContent.test(contentFld)) {
			$("#board_reply_modal #reply_boardContent_err").text("");
			flag++;
		} else {
			var titleContent = $("#board_reply_modal #boardContent").attr('title');
			$("#board_reply_modal #reply_boardContent_err").text(titleContent);             
		} //
						
		// 점검 플래그 인쇄
		console.log("flag : "+flag);
		
		// 전송 부분 : AJAX
		if (flag == 4) {
			
			console.log("전송");
		
			// 댓글 전송
			$.ajax ({
	
				 url : rootPath+'/board/writeReplyProc.do',
				 type : 'post',
				 dataType:'text',
				 data : {
					 boardNum : $("#replyForm #boardNum").val(),
					 boardName : $("#replyForm #boardName").val(),
					 boardPass : $("#replyForm #boardPass").val(),
					 boardSubject : $("#replyForm #boardSubject").val(),
					 boardContent : $("#replyForm #boardContent").val()
				 },
				 success : function(data) {
				   
					console.log("수신 성공 : " + data); 
					console.log(data);
	
					alert(data);
					
				 }, // success
				 error : function(xhr, status) {
					console.log(xhr+" : "+status); // 에러 코드
				 }
	
			}); // $.ajax
		
		} else { // 폼점검 미완료시
			
			alert("필수 입력란을 조건에 맞게 완성하십시오.")
		} //
		
	}); //
	
	////////////////////////////////////////////////////////////////////
	
	// 게시글 삭제 모달 
	$("[id^=modal_delete_btn_").click(function(e) {
		
		alert("게시글 삭제");
		
		// ex) 모달 버튼 아이디 : modal_delete_btn_${boardVO.boardNum}
		var id = e.currentTarget.id;
		id = id.substring("modal_delete_btn_".length, id.length);
		console.log("게시글 id : "+ id);
		
		// 모달 아이디 항목에  게시글 아이디 대입(할당) : 원글/답글 게시글 번호
		$("#board_delete_modal #deleteForm [name=boardNum]").val(id);
		
		// 다른 모달"들" 닫기 
		$("#board_modal").hide(); 
		$("#board_update_modal").hide();
		$("#board_reply_modal").hide();
		
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
	
	// 검색 필드 우측 "게시글 목록" 이동 버튼
	$("#board_search #board_list_btn").click(function(e) {
		alert("게시글 목록");
		location.href = rootPath+"/board/viewAll.do";
	}); //

	////////////////////////// 각 모달의 스크립트 통합  /////////////////////////////
	
	// 게시글 조회 모달 창닫기
	// modal 출력
	$("#board_modal [id^=modal_close_btn]").click(function(){ // 창닫기 버튼 2개
		$("#board_modal").hide();
		// 화면 닫은 후 최근 현황 반영 위해 강제 refresh
		location.reload();
	});
	
	// 수정 모달 창닫기
	// modal 출력
	$("#board_update_modal [id^=modal_close_btn]").click(function(){ // 창닫기 버튼 2개
		$("#board_update_modal").hide(); 
		// 화면 닫은 후 최근 현황 반영 위해 강제 refresh
		location.reload();
	});
	
	// 변형 파일(file) 필드 : boostrap4 custom file field
	// 참고) https://www.w3schools.com/bootstrap4/tryit.asp?filename=trybs_form_custom_file&stacked=h

    //////////////////////////////////////////////////////////////////////
    
	// 추가 : 기존과 다르게 지금은 "가상(동적)으로" 파일 필드가 추가되도록 조치하였으므로 이벤트 처리 부분 변경		
	// $(".custom-file-input").on("change", function() {
	$("form#boardUpdateForm table").on("change", ".custom-file-input", function() {	
		
		var fileName = $(this).val().split("\\").pop();
		$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
	});

	// 답글 모달 창닫기
	// modal 출력
	$("#board_reply_modal [id^=modal_close_btn]").click(function(){ // 창닫기 버튼 2개
		$("#board_reply_modal").hide();
		// 화면 닫은 후 최근 현황 반영 위해 강제 refresh
		location.reload();
	});
	
	// 삭제글 modal 출력
	$("#board_delete_modal [id^=modal_close_btn]").click(function(){ // 창닫기 버튼 2개
		$("#board_delete_modal").hide();
		// 화면 닫은 후 최근 현황 반영 위해 강제 refresh
		location.reload();
	});
	
});