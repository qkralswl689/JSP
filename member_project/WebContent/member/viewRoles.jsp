<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>전체 회원 등급(Role) 정보 조회</title>

<!-- bootstrap CSS : 4.4.1 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery : 3.5.1 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- bootstrap JS : 4.4.1 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<style>
/* 화면 크기 오버할 경우 가로 스크롤바 기능 추가 */
div#view_role_wrap {
	overflow-x:auto;
}

table#view_role_table {
	width:600px;
	text-align:center;
	margin:auto;
	background:#94D8F6;
	font-size:9pt;
}	

table#view_role_table td, table#view_role_table th {
	padding:5px;
	margin:1px;	
}

table#view_role_table td {
	background:#fff;
}

table#view_role_table thead {
	background:#007AAE;
	color:#fff;
}

table#paging_tbl {
	margin:auto;
	text-align:center;
	height:150px;
}
</style>

<script>
// 회원등급 자동 선택 : select
function selectRole(selectId, role) {
    
	console.log("selectRole : "+role);
	
    var len = document.getElementById(selectId).length;
    var selectedIdx = 0; // 선택된 항목의 index
   
    // 항목에 해당하는 순번(index)값 검색
    for (i=0; i<len; i++) {
       if (document.getElementById(selectId)[i].value == role)
           selectedIdx = i;
   } // for
   
   // 선택항목 재설정(re-select)
   document.getElementById(selectId).selectedIndex = selectedIdx;
}


window.onload = function() {
	
	/*
	view_role_move_btn.onclick = function() {
		
		location.href='${pageContext.request.contextPath}/member/viewRoles.do';
	} */
	
	// 회원 등급 정보 수정 버튼 클릭시
	var update_btns = document.querySelectorAll("[id^=role_update_btn]");
	
	for (const update_btn of update_btns) {
		
		update_btn.onclick = function(e) {
			alert("수정");
			// 아이디 파악
			var memberId = e.currentTarget.id.substring("role_update_btn_".length,update_btn.length);
			console.log("memberId : "+memberId);
			
			var memberRole = document.getElementById("member_role_"+memberId).value;
			var page = "${pageVO.page}"; // 현재 페이지
			
			console.log("변경 memberRole : "+memberRole);
			location.href = "${pageContext.request.contextPath}/member/updateRole.do?"
					  +"page="+page+"&memberId="+memberId+"&memberRole="+memberRole;
		} //
		
	} // for
	
	// 회원 등급 정보 삭제 버튼 클릭시
	// 주의사항) 회원정보도 같이 삭제할 것인지 여부 결정할 것 !!!
	/*
	var delete_btns = document.querySelectorAll("[id^=member_delete_btn]");
	
	for (var delete_btn of delete_btns) {
		
		delete_btn.onclick = function(e) {
			
			// 정말 삭제할 지 다시 한번 점검
			if (confirm("정말 삭제하시겠습니까?")) {
				
				// 아이디 확보
				var memberId = e.currentTarget.id.substring("role_delete_btn_".length,delete_btn.length);
				console.log("memberId : "+memberId);
				location.href = "${pageContext.request.contextPath}/member/deleteRoleProc.do?memberId="+memberId;
			} //
			
		} //
		
	} //for	
	*/
	
} //	
</script>
</head>
<body>

	<!-- 인자들 -->
<%-- 	<div>
		pageVO : ${pageVO}<br>
		roles : ${roles}<br>
	</div> --%>
	
	<!-- 전체 레이아웃 -->
	<div id="view_role_wrap">
		
		<br>
		<!-- 회원 정보 테이블(grid) -->
		<table id="view_role_table">
			<thead>
				<tr>
					<th>번호</th>					
					<th>회원아이디</th>
					<th>회원등급(Role)</th>
					<th>메뉴</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:set var="page" value="${empty page ? 1 : page}" />
				<c:set var="limit" value="${empty limit ? 1 : limit}" /> 
				
				<c:forEach items="${roles}" var="role" varStatus="st">
				<tr>
					<td>${st.count+((pageVO.page-1)*limit)}</td>
					<td>${role.memberId}</td>
					<%-- <td>${role.memberRole}</td> --%>
					<td>
						<select id="member_role_${role.memberId}" name="member_role">
							<option value="guest">객원 회원</option>
							<option value="member">정규 회원</option>
							<option value="admin">관리자</option>
						</select>		
						
						<script>
							// 회원등급 자동 선택 : select
							selectRole("member_role_${role.memberId}", "${role.memberRole}");
						</script>				
					</td>
					<td>
						<!-- 버튼의 고유성(아이디)과 인자(memberId) 전달을 위해 버튼의 id를 변경함 -->						
						<button id="role_update_btn_${role.memberId}" class="btn btn-sm btn-primary">수정</button>
						<button id="role_delete_btn_${role.memberId}" class="btn btn-sm btn-primary">삭제</button>
					</td>
				</tr>
				</c:forEach>				
			</tbody>
			
		</table>
		
		<!-- 페이징/검색 -->
		<table id="paging_tbl">
			<tr>
				<td height="80" valign="bottom">
					<%@ include file="paging_role.jspf" %>
				    <%--
				      <!-- 검색 여부에 따라 페이징을 구분 -->
				    <c:choose>
					    <c:when test="${searchYN=='search'}">
    						<%@ include file="paging_role_search.jspf" %>
					    </c:when>
					    <c:otherwise>
							<%@ include file="paging_role.jspf" %>
						</c:otherwise>
					</c:choose>
					 --%>
				</td>
			</tr>
			<tr>
			    <!-- 폼 추가 -->
			    <%--
				<form name="searchForm" method="post" action="${pageContext.request.contextPath}/member/roleSearchProc.do">
					<td class="row"> 
						
						<!-- 검색 -->
						<label id="search_kind" class="mt-2">검색 : </label>&nbsp;
						
						<select id="search_kind" name="search_kind" class="form-control mt-1" style="width:150px">
							<option>아이디</option>
							<option>회원등급</option>
						</select>&nbsp;
						
						<input type="text" class="form-control mt-1" style="width:200px" id="search_word" name="search_word" />
						&nbsp;
						<input type="submit" value="검색" class="btn btn-sm btn-primary" /> <!-- 전송 버튼으로 변경 -->
						&nbsp;
						<!-- 전체 회원 등급 목록 이동 버튼 -->
						<input type="button" id="view_role_move_btn" class="btn btn-sm btn-primary" value="전체 회원목록" />
					</td>
				</form>
			</tr>
			--%>
		</table>
		
	</div>
	<!--// 전체 레이아웃 -->
</body>
</html>