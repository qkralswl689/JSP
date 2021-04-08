package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Notice;

public class NoticeService {

	public List<Notice> getNoticeList(){
		
		return getNoticeList("title","",1);
	}
	public List<Notice> getNoticeList(int page){
		
		return getNoticeList("title","",page);
	}
	public List<Notice> getNoticeList(String field/*TITLE, WRITER_ID*/, String query/*A*/, int page){
		
		List<Notice> list = new ArrayList<>();
		
		/** JDBC URL */	
		 String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		

		String sql = "SELECT * FROM( " + 
				"    select rownum num, n.* " + 
				"    from (select * from notice WHERE "+field+" LIKE ? order by regdate desc) n" + 
				") " + 
				"where num between ? and ?";
		
		// 1,11,21,31 -> an = 1+(page-1)*10
		// 10,20,30, 40 -> page*10
				
				
				/** JDBC 드라이버(driver) */
				try {
					Class.forName("oracle.jdbc.OracleDriver");
					Connection con = DriverManager.getConnection(URL, "mingki", "1234");
		
					PreparedStatement st = con.prepareStatement(sql);
					st.setString(1, "%" + query + "%");
					st.setInt(2,1+(page-1)*10);
					st.setInt(3,page*10);
					
					ResultSet rs = st.executeQuery();
					
					while(rs.next()){
						int id = rs.getInt("ID");
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
						list.add(notice);
					}
		
		
						rs.close();
					    st.close();
					    con.close();           		
		
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		return list;
	}
	public int getNoticeCount() {
		return getNoticeCount("title","" );
	}
	public int getNoticeCount(String field, String query) {
		/** JDBC URL */	
		 String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		 
		int count = 0;
		String sql = "SELECT * FROM( " + 
					"    select rownum num, n.* " + 
					"    from (select * from notice WHERE "+field+" LIKE ? order by regdate desc) n" + 
					") ";
		
		/** JDBC 드라이버(driver) */
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(URL, "mingki", "1234");

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + query + "%");

			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("ID");
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
				list.add(notice);
			}


				rs.close();
			    st.close();
			    con.close();           		

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public Notice getNotice(int id) {
		String sql = "SELECT * FROM NOTICE WHERE ID =?";
		return null;
	}
	public Notice getNextNotice(int id) {
	
		String sql = "SELECT * FROM NOTICE " + 
					"WHERE ID = " + 
					"    SELECT ID FROM NOTICE " + 
					"    WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE ID=3) " + 
					"    AND ROWNUM =1 " + 
					")";
		return null;
	}
	public Notice getPrevNotice(int id) {
		String sql = "SELECT ID FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC) " + 
					" WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE ID=3 ) " + 
					" AND ROWNUM = 1";
		return null;
	}
}
