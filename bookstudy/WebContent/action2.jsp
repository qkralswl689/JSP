<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.Enumeration" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>action2</title>
</head>
<body>

<%
	// Enumeration : 리턴타입 => 각각의 객체들을 한번에 하나씩 처리할 수 있는 메소드 제공
	// getParameterNames() 메소드 : 요청에 포함된 모든 파라미터 이름을 Enumeration 객체로 리턴한다
	
	Enumeration<String> keys = request.getParameterNames();

	// hasMoreElements() : 커서 바로 앞에 데이터가 들어있는지 체크
	while(keys.hasMoreElements()) {
		// nextElement() : 현재 커서가 가리키고 있는 데이터(객체)를 리턴해주고 커서의 위치를 다음칸으로 옮긴다
		String key = keys.nextElement();
		
		if(key.equals("hobby")){ // key의 값이 hobby와 같으면
			
			out.print("hobby = "); // hobby = 을 출력한다
			// s : values에 들어 있는것고 같은 타입으로 선언
			// 배열에 hobby가 입력된것만큼 반복하면서
			// hobby 이름으로 지정된 파라미터의 모든 값을 String 배열로 리턴한다
			for(String s : request.getParameterValues("hobby")){
				
				// s에 입력받은 배열의 값을 대입하여 출력한다
				out.print(s + " ");
			}
			// 한줄 띄기
			out.print("<br>");
		}else { // key의 값이 hobby와 같지않다면
			
			// key 와 입력받은 key값을 출력한다
			out.print(key + " = " + request.getParameter(key) + "<br>" );
			
		}
	}
%>

</body>
</html>