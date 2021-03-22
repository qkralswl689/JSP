<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>action4</title>
</head>
<body>
<%
	// 인자명들 집계
	Enumeration<String> keys = request.getParameterNames();

while(keys.hasMoreElements()){
	
	String key = keys.nextElement();
	
	String values[] = request.getParameterValues(key);
	
	if(values.length == 1) {
		
		out.print(key + " = " + values[0] + "<br>");
		
	}else{
		
		out.print(key + " = ");
		for(String s : values) {
			out.print(s + " ");
		}
		out.print("<br>");
	}
	
	
	/*
	if(key.equals("hobby")) {
		
		out.println("hobby = ");
		for(String s : request.getParameterValues("hobby")) {
			out.print(s + " ");
		}
		out.println("<br>");
	}else{
		out.print(key + "=" + request.getParameter(key) + "<br>");
		
	}*/
	}

%>
</body>
</html>