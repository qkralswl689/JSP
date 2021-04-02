<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>패쓰워드 분실 점검</title>
<style>
/* 전체 레이아웃 */
div#wrap {
	width:100vw;
	/* 템플릿 적용시 전체 레이아웃 높이 조정 : 본문 가용화면 확보 : 
	     가용화면(viewport) - 상단메뉴 높이 - 하단 section 높이 - padding(높이)  */
	min-height:calc(100vh - 50px - 70px - 40px);
	display:flex;
	align-items:center;
	justify-content:center;
}

/* 패쓰워드 검색 박스 */
div#pw_search_box {
	width:520px;
/*  	background:yellow;  */
}

/* 패쓰워드 검색 박스 테이블 */
table#pw_search_tbl {
	width:100%;
}

table#pw_search_tbl td.fld_td {
	text-align:left;
	height:60px;
}

/* 메시지 */
#result_msg { 
	text-indent:2em;
}
</style>

<!-- google material -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- bootstrap CSS : 4.4.1 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery : 3.5.1 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- bootstrap JS : 4.4.1 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<script>
// template 방식 페이지 이동
window.onload = function() {
	
	id_search_btn.onclick = function() {
		location.href = "${pageContext.request.contextPath}/template.do?content_page=/member/id_lost_search.jsp";
	} //
	
} //	
</script>
</head>
<body>

	<div id="wrap" class="mx-auto">
	
		<div id="pw_search_box" class="container">
		
			<div>
				<!-- 크롬 자동 완성 기능 삭제 : autocomplete="off"-->
				<form class="form-inline"
				 	  autocomplete="off" 
					  action="${pageContext.request.contextPath}/member/pwSearchProc.do" 
					  method="POST">

					<table id="pw_search_tbl">
						<tr>
					 		<td class="fld_td">
					 			<label for="memberId" class="float-left mr-1">
								 	<span class="material-icons mt-1" style="color:#60C5F1">
							        	trip_origin
							        </span>&nbsp;		 
								 	<b>회원 아이디 :</b>
							 	</label>
							</td>	
							<td class="fld_td"> 
					  			<input type="text" 
					  				   id="memberId" 
			                		   name="memberId"  
					                   maxlength="20" 
									   pattern="[a-zA-Z]{1}\w{7,19}"
									   title="아이디는 영문으로 시작하며 영문/숫자 조합하여 8~20자로 입력하십시오"
								   	   placeholder="아이디를 입력하십시오."
					                   class="form-control ml-0 mr-3">					                   
		                    </td>
			             </tr>
					 	<tr>
					 		<td class="fld_td">
					 			<label for="memberName" class="float-left mr-1">
								 	<span class="material-icons mt-1" style="color:#60C5F1">
							        	trip_origin
							        </span>&nbsp;		 
								 	<b>회원 이름 :</b>
							 	</label>
							</td>	
							<td class="fld_td"> 
					  			<input type="text" 
					  				   id="memberName" 
		                		   	   name="memberName"  
				                   	   maxlength="25" 
				                   	   size="25"
				                   	   pattern="[가-힣]{2,25}"  
				                   	   required
								   	   title="이름은 한글로 입력하십시오"
								   	   placeholder="이름을 입력하십시오."
					                   class="form-control ml-0 mr-3">					                   
		                    </td>
			             </tr>
			             
			             <tr> 
						 	<td class="fld_td">
						 		<label for="memberEmail" class="float-left mr-1">
								 	<span class="material-icons mt-1" style="color:#60C5F1">
							        	trip_origin
							        </span>&nbsp;	
								 	<b>회원 이메일 :</b> 
					 			</label>
							</td>	
							<td class="fld_td">						 	
							 	<input type="text" 
		                		   id="memberEmail" 
		                		   name="memberEmail"  
				                   pattern="[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}"  
				                   size="25"
				                   maxlength="50"
				                   required
								   title="이메일을 입력하십시오"
								   placeholder="이메일을 입력하십시오."
				                   class="form-control ml-0 mr-3">
						 	</td>
						 </tr>
						 
						 <tr> 
						 	<td class="fld_td">
						 		<label for="memberPhone" class="float-left mr-1">
								 	<span class="material-icons mt-1" style="color:#60C5F1">
							        	trip_origin
							        </span>&nbsp;	
								 	<b>회원 연락처 :</b> 
					 			</label>
							</td>	
							<td class="fld_td">						 	
							 	<input type="text" 
			                		   id="memberPhone" 
			                		   name="memberPhone"  
			                    	   maxlength="13" 
			                    	   pattern="01\d{1}-\d{3,4}-\d{4}"
			                    	   required
			                    	   size="20"
									   title="전화번호는 우측 예시와 같이 입력하십시오" 
					                   class="form-control ml-0 mr-3"
					                   placeholder="연락처를 입력하십시오.">
				                  ex) 010-1234-5678
						 	</td>
						 </tr>
						 
						 <tr>
						 	<td colspan="2" style="text-align:center; height:70px">
			                	<input type="submit" class="btn btn-primary" value="패쓰워드 검색" />&nbsp;
			                	<input type="reset" class="btn btn-primary" value="취소" />&nbsp;
			                	<input type="button" class="btn btn-outline-primary" id="id_search_btn" value="아이디 검색">
		              		</td>
	              		 </tr>
	              		 
	              		 <!-- 메시지 -->
	              		 <tr>
						 	<td colspan="2" id="result_msg">${result_msg}</td>
						 </tr>
						 
              		 </table>
              		 
				</form>
			</div>	
			
		</div>
		
	</div>

</body>
</html>