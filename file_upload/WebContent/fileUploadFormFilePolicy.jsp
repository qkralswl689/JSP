<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--@ page trimDirectiveWhitespaces="true" --%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>파일 업로드 폼(파일 정책 사용)</title>
<style>
#uploadFormArea{
	margin : auto;
	width : 350px;
	border : 0;
}
.td_title{
	font-size: 1em;
	text-align: center;
}
</style>
</head>
<body>
<div id="uploadFormArea">
<form action="fileUploadFilePolicy.jsp" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<th colspan="2"><h3>파일 업로드 폼(파일 정책 사용)</h3></th>
	<tr>
		<td><label for="name">올린 사람 : </label></td><td><input type="text" name="name" id="name"></td>
	</tr>
	<tr>
		<td><label for="subject">제목 : </label></td><td><input type="text" name="subject" id="subject"></td>
	</tr>
	<tr>
		<td><label for="fileName1">파일명1 : </label></td><td><input type="file" name="fileName1" id="fileName1"></td>
	</tr>
	<tr>
		<td><label for="fileName2">파일명2 : </label></td><td><input type="file" name="fileName2" id="fileName2"></td>
	</tr>
	<tr>
		<td colspan=2 align=center><input type="submit" value="전송"></td>
	</tr>
</table>
</form>
</div>
</body>
</html>