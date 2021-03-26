<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>  
<fmt:requestEncoding value="UTF-8" />
<%
		/* List 생성  */
       ArrayList<String> list = new ArrayList<String>();
        list.add("java");
        list.add("spring");
        list.add("struts");
        list.add("김태희");
        list.add("장동건");
		
        /* 인자 전송 */
        request.setAttribute("list", list);

%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>demo1</title>
</head>
<body>
<%
	List<String> members = (List<String>)request.getAttribute("list");
	
	for (int i= 0; i<members.size(); i++){
		out.print(i+1 + " ");
		out.print(members.get(i) + " <br>");
	}
	out.print("<hr>");
	for(String s : members){
		out.print(s + "<br>");
	}
%>
		<!-- 리스트 인자 전송받아 사용 -->
        <c:forEach var="listFor" items="${list}" varStatus="status">
               ${status.count} 
               <!-- 특별히 forEach문에서 begin, end 속성이 없다면 아래의 4가지는 동일한 표현 -->
               ${list.get(status.index)} 
               ${list.get(status.count-1)} 
               <!-- ${listFor} :가장 간결하고 좋은방법 -->
               ${listFor} 
               ${status.current}<br>
        </c:forEach>
</body>
</html>