<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Map,Set,Iterator import --%>
<%@page import="java.util.Map , java.util.Set, java.util.Iterator" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>action</title>
</head>
<body>
<%
	// UTF-8로 무조건 인코딩
	request.setCharacterEncoding("UTF-8");

	// id : 파라미터 id 를 얻어 값이 null이면 없음출력하고 값이 있으면 id를 출력한다
	String id = request.getParameter("id") == null ? "없음" : request.getParameter("id");
	String pw = request.getParameter("pw") == null ? "없음" : request.getParameter("pw");
	String name = request.getParameter("name") == null ? "없음" : request.getParameter("name");
	String address = request.getParameter("address") == null ? "없음" : request.getParameter("address");

	// hobby 값 저장할 배열 생성
	// map은 key와 value로 구성되어있다
	Map<String, String[]> map = request.getParameterMap();
	// set은 저장순서가 고정되어있지 않다
	// keySet() : 모든 key를 set객체에 담아서 리턴한다
	Set<String> keys = map.keySet();
	// Iterator : 반복자 => 순서가 없는 것에 순서를 부여하는 클래스
	//  반복자로 key를 하나씩 얻어서 get() 메소드로 값을 얻는다
	Iterator<String> it = keys.iterator();
	while(it.hasNext()){
		String key = it.next(); // key를 뽑아온다
		String values[] = map.get(key);// key의 값을 리턴하는 배열 values
		
		if(values.length == 1){ // 배열의 값이 1이라면
			
			// Key 와 배열의 0번째 있는 값을 출력한다 
			out.println(key + " = " + values[0] + "<br>");
			
		}else{ // 1이 아니라면
			
			// key 만 출력한다
			out.print(key + " = ");
			
		// s : values에 들어있는 것과 같은 타입으로 선언
		// values 의 길이만큼 반복하면서
			for(String s : values) { 
				
				// s에 배열의 값을 대입하여 출력한다
				out.print(s + " ") ; 
			}
	 		// 한줄 띄기
		} out.print("<br>");
	}
%>
</body>
</html>