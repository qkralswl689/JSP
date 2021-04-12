<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 모달 박스 -->
<div id="board_reply_modal">
    
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
			답글(댓글) 작성
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

		<!-- 답글(댓글) 작성 시작 -->
		<section id="replyForm" class="container">
	
			<!-- bootstrap table 적용 -->
			<table class="table">
			
				<tr class="form-group">
					<td class="col-xs-2">
						<label for="boardNum" class="control-label"> 
							<span style="color:red">*</span>(원글) 게시글 번호
						</label>
					</td>
					<td class="col-xs-10">
						<input type="text" 
							   id="boardNum"
							   name="boardNum" 
							   class="form-control rounded-sm bg-light"
							   readonly />
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
							   title="게시글 작성자를  2~15자 이내로 입력하십시오"
							   required />
						
						<!-- 작성자 에러 메시징 시작 -->	   
					    <span id="reply_boardName_err" class="err_msg"></span>
					    <!-- 작성자 에러 메시징 끝 -->		   
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
					    <span id="reply_boardPass_err" class="err_msg"></span>
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
						<input type="text" 
							   id="boardSubject" 
							   name="boardSubject" 
							   class="form-control rounded-sm bg-light"
							   title="글제목은 2~50자이내로 적습니다" 
							   required />
							   
						<!-- 글제목 에러 메시징 시작 -->	   
					    <span id="reply_boardSubject_err" class="err_msg"></span>
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
						<textarea id="boardContent"
								  name="boardContent"
								  cols="40" 
								  rows="5"
								  class="form-control rounded-sm bg-light"
								  title="글내용은 2~1000자이내로 적습니다" 
								  required></textarea>
								  
					    <!-- 글내용 에러 메시징 시작 -->	   
					    <span id="reply_boardContent_err" class="err_msg"></span>
					    <!-- 글내용 에러 메시징 끝 -->		
				  	</td>
				</tr>
	
				<!-- 주의) 댓글은 파일 첨부 없음 -->
	
			</table>
	
		</section>
		<!-- 답글(댓글) 작성 끝 -->
		
	</div>
	<!--// modal content -->
	
	<!-- modal menu -->
	<div id="modal_footer" class="mb-3 mt-0">
		
		<!-- 추가  및 메뉴 조정 : 댓글 전송 버튼 -->
		<input type="button" id="reply_submit_btn" class="btn btn-outline-primary" value="답글 전송" />
				
		<input type="button" id="reply_reset_btn" class="btn btn-outline-primary" value="작성 취소" />
		<input type="button" id="modal_close_btn2" class="btn btn-primary" value="닫기"/>
	</div>
	<!--// modal menu -->
</div>	