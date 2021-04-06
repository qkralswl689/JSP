package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));

		/** JDBC URL */	
		 String URL = "jdbc:oracle:thin:@localhost:1521:xe";
			
		/** DB account(ID) */
		 String USER_ID = "mingki";

		/** DB account(PW) */
		 String USER_PW = "1234";

		String sql = "SELECT * FROM NOTICE WHERE ID=? ";
		
		/** JDBC 드라이버(driver) */
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(URL, USER_ID, USER_PW);

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			rs.next();
			String title = rs.getString("TITLE") ;
			Date regdate = rs.getDate("REGDATE") ;
			String writerId = rs.getString("WRITER_ID");
			String hit = rs.getString("HIT") ;
			String files = rs.getString("FILES") ;
			String content = rs.getString("CONTENT") ;
			
			Notice notice  = new Notice(
								id,
								title,
								regdate,
								writerId,
								hit,
								files,
								content
					); 
			
			request.setAttribute("n", notice);
			/*
			request.setAttribute("title",title );
			request.setAttribute("regdate",regdate );
			request.setAttribute("writerId",writerId );
			request.setAttribute("hit",hit );
			request.setAttribute("files",files );
			request.setAttribute("content",content );
			*/
			rs.close();
			st.close();
			con.close(); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		// forward
		request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp")
		.forward(request, response);
	}

}
