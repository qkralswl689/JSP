<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>

<%
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	
	Connection con = null; // DB연결
	PreparedStatement pstmt =null; // SQL처리 객체 
	ResultSet rs = null; // 결과셋 객체
	
	try{
	 Context init = new InitialContext(); // JNDI 네이밍 방식 : 외부객체를 이용하기위한 기술
	 DataSource ds = (DataSource) init.lookup("java:/comp/env/jdbc/scott"); // lookup :참고
	 con = ds.getConnection();//lookup한 db연결
	 
	 pstmt=con.prepareStatement("SELECT * FROM member WHERE id=?"); //SQL 문 작성
	 pstmt.setString(1,id); // ? 에 들어갈 인자 id로 지정
 	 rs=pstmt.executeQuery();
	 
	 if(rs.next()){ // db 순서대로 탐색
		 if(pass.equals(rs.getString("pass"))){//db에서 가져온 비밀번호가 입력받은 비밀번호와 일치하다면
			 session.setAttribute("id",id); // session영역에 입력받은 id 이름의 속성 값을 생성
			
			out.println("<script>");
	  		out.println("location.href='main.jsp'");//main.jsp페이지로 이동한다
	  		out.println("</script>");
		 }
	 }else{ // 일치하지 않다면
		out.println("<script>"); 
  		out.println("location.href='loginForm.jsp'"); // loginForm.jsp 페이지도 이동한다
  		out.println("</script>");
	 }
		 
	 }catch(Exception e){ // 예외처리
		 e.printStackTrace();
 	 }finally{
 		try{ // 자원반납
 			rs.close();
 			pstmt.close();
 			con.close();
 		}
 		catch(Exception e){ // 자원반납시 예외처리
 			e.printStackTrace();
 		}
 	 }
%>