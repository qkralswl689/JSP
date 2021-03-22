<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Map import --%>
<%@ page import="java.util.Map, java.util.Set, java.util.Iterator" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>action2</title>
</head>
<body>
<%
	// 배열 생성
	Map<String, String[]> map = request.getParameterMap();
	Set<String> keys = map.keySet();
	Iterator<String> it = keys.iterator();
	
	while(it.hasNext()){
		String key = it.next();
		
		// 값을 받는다 => 배열
		// map.get(key) : key를 통해 인자를 가져온다
		String values[] = map.get(key);
		
		// 배열의 길이가 1이라면
		if (values.length == 1) {
			
			out.println(key + "=" + values[0] + "<br>");
			/* jsp 표현식 : key + "=" + values[0] + "<br>" => 스파게티 코드가되므로 사용 X*/
		}else { // checkbox(hobby)
		
			String hobbies[] = request.getParameterValues("hobby");
			//values = request.getParameterValues("hobby");
			
			out.println(key + "=");
			
			for(String s : hobbies) {
			 	out.println(s + " " );
			}
			out.println("<br>");
		}
	}
	
%>

</body>
</html>