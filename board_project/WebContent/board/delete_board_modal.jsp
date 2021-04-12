<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
							   readonly />
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