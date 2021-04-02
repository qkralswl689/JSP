<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인</title>
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

/* 로그인 박스(아이디/패쓰워드 입력란) */
div#login_box {
	width:500px;
}

/* 로그인 박스 테이블*/
table#login_tbl {
	width:100%;
/* 	padding:20px; */
}

table#login_tbl td.fld_td {
	text-align:left;
	height:60px;
}

/* 에러 메시지 */
.err_msg { 
	color:red;
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
	
	join_btn.onclick = function() {
		location.href = "${pageContext.request.contextPath}/template.do?content_page=/member/member_join.jsp";
	} //
	
	// 아이디/비밀번호 검색
	id_pw_search_btn.onclick = function() {
		location.href = "${pageContext.request.contextPath}/template.do?content_page=/member/id_lost_search.jsp";
	} //
	
} //	
</script>
</head>
<body>

   <!-- 에러 메시지 -->
   <c:if test="${not empty err_msg}">
   		<script>
   			alert("${err_msg}");
   		</script>
   </c:if>
 
	<div id="wrap" class="mx-auto">
	
		<div id="login_box" class="container">
		
			<div>
				<form class="form-inline" action="${pageContext.request.contextPath}/member/loginProc.do" method="POST">

					<table id="login_tbl">
					 	<tr>
					 		<td class="fld_td">
					 			<label for="memberId">
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
					                   size="30"
									   pattern="[a-zA-Z]{1}\w{7,19}"
									   title="아이디는 영문으로 시작하며 영문/숫자 조합하여 8~20자로 입력하십시오"
					                   required
					                   class="form-control ml-0 mr-3" 
					                   placeholder="아이디를 입력하십시오">					                   
		                    </td>
			             </tr>
						 <tr> 
						 	<td class="fld_td">
						 		<label for="memberPassword">
								 	<span class="material-icons mt-1" style="color:#60C5F1">
							        	trip_origin
							        </span>&nbsp;	
								 	<b>회원 패쓰워드 :</b> 
					 			</label>
							</td>	
							<td class="fld_td">						 	
							 	<input type="password" 
			                	       id="memberPassword"  
			                    	   name="memberPassword" 
			                    	   maxlength="20" 
			                    	   size="30"
			                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,20}"  
			                           required
			                           class="form-control ml-0 mr-3" 
									   title="패쓰워드는 영문대소문자/특수문자/숫자 조합하여 8~20자로 입력하십시오"
									   placeholder="패쓰워드를 입력하십시오">
						 	</td>
						 </tr>
						 
						 <!-- 에러 메시지 -->
			             <%--  <tr>
			             	<td class="err_msg" colspan="2">${err_msg}</td>
			             </tr> --%>
						 
						 <tr>
						 	<td colspan="2" style="text-align:center; height:70px">
			                	<input type="submit" class="btn btn-primary" value="로그인" />&nbsp;
			                	<input type="reset" class="btn btn-primary" value="취소" />&nbsp;
			                	<input type="button" class="btn btn-outline-primary" id="join_btn" value="회원 가입">&nbsp;
			                	<input type="button" class="btn btn-outline-primary" id="id_pw_search_btn" value="아이디/비밀번호 검색">
		              		</td>
	              		 </tr>
              		 </table>
              		 
				</form>
			</div>	
			
		</div>
		
	</div>

</body>
</html>