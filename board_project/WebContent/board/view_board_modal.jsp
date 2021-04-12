<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
	
				<%-- 
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
				</tr> --%>
	
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