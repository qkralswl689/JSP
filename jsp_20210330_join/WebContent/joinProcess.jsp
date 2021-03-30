<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%

	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	int age = Integer.parseInt(request.getParameter("age"));
	String gender = request.getParameter("gender");
	String email = request.getParameter("email");
	
	Connection con = null;
	PreparedStatement pstmt = null;
	
	try{
		
		Context init = new InitialContext();
		
		DataSource ds = (DataSource) init.lookup("java:/comp/env/jdbc/scott");
		con = ds.getConnection();
		
		pstmt= con.prepareStatement("INSERT INTO member VALUES (?,?,?,?,?,?)");
		pstmt.setString(1,id);
  		pstmt.setString(2,pass);
  		pstmt.setString(3,name);
  		pstmt.setInt(4,age);
  		pstmt.setString(5,gender);
  		pstmt.setString(6,email);
  		
  		int result = pstmt.executeUpdate(); // 데이터 삽입 성공여부 1, 0 으로 전달
		
  		if(result !=0){ // 성공시
  			out.println("<script>");
  		  	out.println("location.href='loginForm.jsp'"); // loginForm.jsp로 이동
  		  	out.println("</script>");
  		}else{ // 실패시
  			out.println("<script>");
  	  		out.println("location.href='joinForm.jsp'");// joinForm.jsp로 이동
  	  		out.println("</script>");	
  		}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
 		try{// 자원반납
 			pstmt.close();
 			con.close();
 		}
 		catch(Exception e){
 			e.printStackTrace();
 		}
 	}
	
%>