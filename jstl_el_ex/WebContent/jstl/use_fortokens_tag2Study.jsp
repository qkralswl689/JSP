<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>forTokens 태그</title>
</head>
<body>

콤마와 점을 구분자로 사용:<br>
<c:forTokens var="token"
             items="빨강색,주황색.노란색.초록색,파랑색,남색.보라색"
             delims=",.">

<!-- token 뒤에 <br> 삽입시 단어마다 br적용된다 -->             
${token}<br>
</c:forTokens>

</body>
</html>
