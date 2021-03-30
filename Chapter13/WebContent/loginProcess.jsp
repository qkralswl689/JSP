<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%
	/* loginForm.jsp 페이지에서 파라미터로 전송된 아이디 값을 받는 부분 */
	String id=request.getParameter("id");
	/* loginForm.jsp 페이지에서 파라미터로 전송된 비밀번호 값을 받는 부분 */
	String pass=request.getParameter("pass");
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try {
  		Context init = new InitialContext();
  		DataSource ds = (DataSource) init.lookup("java:/comp/env/jdbc/scott");
  		conn = ds.getConnection();
  		
  		/* 사용자가 입력한 아이디를 가지고 있는 회원의 정보를 조회하는 SQL문으로  prepareStatement 객체를 생성하는 부분*/
  		pstmt=conn.prepareStatement("SELECT * FROM member WHERE id=?");
  		pstmt.setString(1,id);
  		rs=pstmt.executeQuery();
  		
  		/* 사용자가 입력한 ID를 가진 회원 정보가 존재하고 입력한 비밀번호와 DB에서 가져온 비밀번호 까지 일치하면 
  			session영역에 id 라는 이름의 속성 값을 생성하고 main.jsp페이지로 이동하도록 처리  */
  		if(rs.next()){
  			if(pass.equals(rs.getString("pass"))){/* DB에서 가져온 비밀번호와 사용자가 입력한 비밀번호 pass 변수에 저장된 값을 비교 */
  				session.setAttribute("id",id);
  				out.println("<script>");
  		  		out.println("location.href='main.jsp'");
  		  		out.println("</script>");
  			}
  		}
  		
  		/* 로그인 실패시 loginForm.jsp페이지로 이동하도록 처리 */
  		out.println("<script>");
  		out.println("location.href='joinForm.jsp'");
  		out.println("</script>");
	}catch(Exception e){
		e.printStackTrace();
 	}finally{
 		try{
 			rs.close();
 			pstmt.close();
 			conn.close();
 		}
 		catch(Exception e){
 			e.printStackTrace();
 		}
 	}
%>
