<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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