<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원정보 삭제</title>
<style>
/* 전체 레이아웃 */
div#wrap {
	width:100vw;
	height:100vh;
	display:flex;
	align-items:center;
	justify-content:center;
}

/* 아이디 입력란 */
div#id_box {
	width:520px;
}

/* 에러 메시지 */
span#err_msg { color:red; }
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
</head>
<body>

	<div id="wrap" class="mx-auto">
	
		<div id="id_box" class="container">
		
			<div>
				<form class="form-inline" action="${pageContext.request.contextPath}/member/deleteProc.do" method="POST">
				
					 <label for="memberId">
					 	<span class="material-icons mt-1" style="color:#60C5F1">
				        	trip_origin
				        </span>		
					 	&nbsp; <b>삭제할 회원 아이디 :</b> 
				 	 </label>
				 	 
				 	 <!-- 이 부분에서는  현재 로그인 중에 있는 아이디만을 삭제해야 되므로 다른 아이디가 입력되지 않게 하려면 아래와 같이 
				 	 	  조치하면 됩니다. -->
				 	 	  
				 	 <input type="text" 
		            		id="memberId" 
		              		name="memberId"  
		                    required
		                    readonly
		                    class="form-control ml-3 mr-3"  
		                    value="${sessionScope.LOGIN_SESSION}">
		             
		             <!-- 로그인 없이 일반적인 입력시 삭제할 경우 -->       
		  			 <%-- <input type="text" 
		            		id="memberId" 
		              		name="memberId"  
		                    maxlength="20" 
						    pattern="[a-zA-Z]{1}\w{7,19}"
						    title="아이디는 영문으로 시작하며 영문/숫자 조합하여 8~20자로 입력하십시오"
		                    required
		                    class="form-control ml-3 mr-3"  
		                    placeholder="아이디를 입력하십시오"
		                    value="${param.memberId}"> --%>
		                    <!-- 이 부분에서 아이디를 다시 입력하지 않으려면 value="${param.memberId}" 와 같이 조치 -->
		              
		              <input type="submit" class="btn btn-primary" value="전송" />
		              
				</form>
			</div>	
			
			<div class="mt-2 pl-3">
				<span id="err_msg">${err_msg}</span>
			</div>
			
		</div>
		
	</div>

</body>
</html>