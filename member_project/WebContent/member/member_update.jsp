<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원 수정</title>

<!-- google material -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- CSS 외장화 -->
<!--  modal -->
<link rel="stylesheet" href="/member_project/css/modal.css">

<!-- 회원수정 CSS -->
<link rel="stylesheet" href="/member_project/css/member_update.css">

<!-- jquery -->  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- jqueryui -->  
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
<!-- daum 주소 검색 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<!-- javascript/jquery 외장화 -->
<script>
const rootPath = '${pageContext.request.contextPath}';
</script>
<script src="/member_project/js/member_update.js" charset="UTF-8"></script>

<script>
// modal 출력
$(function() {
	$("#modal_btn").click(function(){
		$("#msg_modal").show();
	});
});
</script>
</head>  
<body>  

	<!-- demo : 창열기 버튼 -->
	<!-- <a id="modal_btn" href="#">  
        <span class="material-icons" style="color:#333; font-size:2em">
          touch_app
        </span>
    </a> -->

	<!-- modal -->
	<div id="msg_modal">
	    
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
				폼점검 오류 정보
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
		 	 <!-- 
			 	 글 내용입니다.<br>
			 	 글 내용입니다.<br> 
		 	 -->
		</div>
		
		<!-- modal footer -->
		<div id="modal_footer">
		</div>
		
	</div>
    
    <!-- ---------------------------------------------------------- -->

	<div id="wrap">
	  
	    <div id="updateFormPnl">
		
		    <form id="updateForm" 
		    	  name="updateForm" 
		          method="post"
		          action="/member_project/member/updateProc.do">
		  
		        <table id="updateFormTbl">  
		  
		            <!-- 아이디 -->  
		            <tr>  
		                <td><span class="requiredFld">*</span> 아이디</td>  
		                <td>
		                	${member.memberId}
		                	<input type="hidden" id="memberId" name="memberId" value="${member.memberId}">
		                </td>  
		            </tr>  
					<!--// 아이디 -->  
		  
		            <!-- 패쓰워드  -->  
		            <tr>  
		                <td><span class="requiredFld">*</span> 신규 패쓰워드</td>  
		                <td>
		                	<input type="hidden" id="memberPassword" name="memberPassword" value="${member.memberPassword}">
		                	
		                	<input type="password" 
		                		   id="memberPassword1"  
		                    	   name="memberPassword1" 
		                    	   maxlength="20" 
		                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,20}"  
								   title="패쓰워드는 영문대소문자/특수문자/숫자 조합하여 8~20자로 입력하십시오">
		                </td>  
		            </tr>  
					<!--// 패쓰워드  -->  
					
					<!-- 패쓰워드 확인  -->  
		            <tr>  
		                <td><span class="requiredFld">*</span> 패쓰워드 확인</td>  
		                <td>
		                	<input type="text" 
		                		   id="memberPassword2"  
		                    	   name="memberPassword2" 
		                    	   maxlength="20" 
		                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,20}"  
								   title="패쓰워드는 영문대소문자/특수문자/숫자 조합하여 8~20자로 입력하십시오">
		                </td>  
		            </tr>  
					<!--// 패쓰워드  확인 -->  
		            
		            <!-- 별명 -->  
		            <tr>  
		                <td><span class="requiredFld">*</span> 별명</td>  
		                <td>
		                	${member.memberNickname}
		                	<input type="hidden" id="memberNickname" name="memberNickname" value="${member.memberNickname}" /> 
		             	</td>  
		            </tr>  
					<!--// 별명 -->  
					
		            <!-- 이름  -->  
		            <tr>  
		                <td><span class="requiredFld">*</span> 이름</td>  
		                <td>
		                	${member.memberName}
		                	<input type="hidden" id="memberName" name="memberName" value="${member.memberName}" />
		                </td>  
		            </tr>  
					<!--// 이름  -->  
					
		            <!-- 성별  -->  
		            <tr>  
		                <td><span class="requiredFld">*</span> 성별</td>  
		                <td>
		                    ${member.memberGender=='m' ? "남" : "여"}
		                    <input type="hidden" id="memberGender" name="memberGender" value="${member.memberGender}" />
		                </td>  
		            </tr>  
					<!--// 성별  -->  
					
		            <!-- 이메일 -->  
		            <tr>  
		                <td><span class="requiredFld">*</span> 이메일</td>  
		                <td>
		                	<input type="text" 
		                		   id="memberEmail" 
		                		   name="memberEmail"  
				                   pattern="[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}"  
				                   size="25"
				                   maxlength="50"
								   title="이메일을 입력하십시오"
								   value="${member.memberEmail}">
							<span id="memberEmail_err" class="err_msg"></span>    	   
		                </td>  
		            </tr> 
					<!--// 이메일 -->  
		              
		            <!-- 핸드폰번호  -->  
		            <tr>  
		                <td><span class="requiredFld">*</span> 연락처</td>  
		                <td>
							<input type="text" 
		                		   id="memberPhone" 
		                		   name="memberPhone"  
		                    	   maxlength="13" 
		                    	   pattern="01\d{1}-\d{3,4}-\d{4}"
		                    	   size="15"
								   title="전화번호는 우측 예시와 같이 입력하십시오"
								   value="${member.memberPhone}"> 
		                    ex) 010-1234-5678
		                    <div id="memberPhone_err" class="err_msg"></div>
		            	</td>  
		            </tr>  
					<!--// 핸드폰번호  -->  
					
		            <!-- 생년월일 -->  
		            <tr>  
		                <td><span class="requiredFld">*</span> 생년월일</td>  
		                <td>
		                	${member.memberBirth}
		                 	<input type="hidden" id="memberBirth" name="memberBirth" value="${member.memberBirth}" />
		                </td>  
		            </tr>  
					<!--// 생년월일 -->  
		            					
		            <!-- 우편번호  -->  
		            <tr>  
		                <td>우편번호</td>  
		                <td>
		               		<input type="text" 
		                		   id="memberZip" 
		                		   name="memberZip"   
		                    	   maxlength="5" 		                    	   
		                    	   pattern="\d{5}"
		                    	   readonly
		                    	   value="${member.memberZip}">
		                    	      
		                    <input type="button" 
		                    	   id="updateAddressSearchBtn" 
								   name="updateAddressSearchBtn" 
		                    	   value="주소검색"
		                    	   onClick="searchPost()"><br>  
						</td>  
		            </tr>  
					<!--// 우편번호  -->  
					
		            <!-- 기본주소 -->  
		            <tr>  
		                <td>기본주소</td>  
		                <td>
		                	<input type="text" 
		                		   id="memberAddressBasic"  
				                   name="memberAddressBasic" 
				                   pattern="[\w | \W | 가-힣 | / | - | (  |  ) | ,]{2,200}" 
				                   maxlength="200"  
				                   size="50"				                   
								   title="기본주소를 입력하십시오"
								   readonly
								   value="${member.memberAddressBasic}">
		                </td>  
		            </tr>  
					<!--// 기본주소 -->  
							            
		            <!-- 상세주소 -->  
		            <tr>  
		                <td>상세주소</td>
		                <td>
		                	<input type="text" 
								   id="memberAddressDetail"  
				                   name="memberAddressDetail"  
				                   pattern="[\w | \W | 가-힣 | / | -]{2,100}" 
				                   maxlength="100"  
				                   size="50"
								   title="상세주소를 입력하십시오"
								   value="${member.memberAddressDetail}" />
		                </td>  
		            </tr>  
				</table>  
				<!--// 상세주소 -->  
					
				<table id="updateFormMenu">	
		            
					<!-- 회원수정/취소 버튼 -->
		            <tr>  
		                <td>
		                	<input type="submit" 
		                		   id="updateSubmitBtn"
								   name="updateSubmitBtn"
				                   value="회원수정">  
				                   
		                    <input type="reset" 
								   id="updateResetBtn"
								   name="updateResetBtn"
								   value="수정취소">
		               	</td>  
		            </tr>  
					<!--// 회원수정/취소 버튼 -->
					
		        </table>  
		    </form>  
			
	    </div>
    
    </div>
</body>  
</html>