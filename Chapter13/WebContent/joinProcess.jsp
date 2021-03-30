<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%
	/* joinForm.jsp에서 파마리터로 전송된 데이터를 얻어올 때 한글 데이터가 깨지지 않도록 처리 */
	request.setCharacterEncoding("UTF-8");
	/* 회원가입 폼에서 파라미터로 전송된 데이터들을 얻어온다 (id,pass,name,age,gender,email)*/
	String id=request.getParameter("id");
	String pass=request.getParameter("pass");
	String name=request.getParameter("name");
	int age=Integer.parseInt(request.getParameter("age"));
	String gender=request.getParameter("gender");
	String email=request.getParameter("email");
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	
	try {
  		Context init = new InitialContext();
  		/* DB 연결(mariadb로) */
  		DataSource ds = (DataSource) init.lookup("java:/comp/env/jdbc/scott");
  		conn = ds.getConnection();
  		
  		/* SQL 삽입 */
  		pstmt=conn.prepareStatement("INSERT INTO member VALUES (?,?,?,?,?,?)");
  		pstmt.setString(1,id);
  		pstmt.setString(2,pass);
  		pstmt.setString(3,name);
  		pstmt.setInt(4,age);
  		pstmt.setString(5,gender);
  		pstmt.setString(6,email);
  		int result=pstmt.executeUpdate();
  		
  		if(result!=0){  /* 성공적으로 레코드를 삽입하면 로그인 폼으로 이동하도록 설정 */			
  			out.println("<script>");
  		  	out.println("location.href='loginForm.jsp'");
  		  	out.println("</script>");
  		}else{ /* 레코드 삽입 실패시 회원가입 폼으로 이동 */
  			out.println("<script>");
  	  		out.println("location.href='joinForm.jsp'");
  	  		out.println("</script>");	
  		}
	}catch(Exception e){
		System.out.println(e.getMessage());
		e.printStackTrace();
 	}finally{
 		try{
 			pstmt.close();
 			conn.close();
 		}
 		catch(Exception e){
 			e.printStackTrace();
 		}
 	}
%>
