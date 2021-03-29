<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>단일 파일 업로드 테스트</title>
</head>
<body>
<h1>단일 파일 업로드 테스트</h1>

<form action="partUploadPro1" method="POST" enctype="multipart/form-data">
	<label for="writer">작성자 : </label><input type="text" name="writer" id="writer"><br>
	<label for="partFile1">업로드 파일 : </label><input type="file" name="partFile1" id="partFile1"><br>
	<input type="submit" value="단일업로드"/>
</form>

</body>
</html>