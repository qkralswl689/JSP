package com.javateam.board_project.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javateam.board_project.board.domain.BoardVO;
import com.javateam.board_project.board.util.DbUtil;
import com.javateam.board_project.board.util.ExceptionMetadata;

public class BoardDAOImpl implements BoardDAO {
	
	// DB Connection 객체(공유)
	Connection con;
	
	// 싱글턴 객체
	private static BoardDAOImpl INSTANCE = null;
	
	private BoardDAOImpl() {}
	
	public static final BoardDAOImpl getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new BoardDAOImpl();
		}
		return INSTANCE;
	}
	
	// 추가
	@Override
	public void setConnection(Connection con){
		this.con = con;
	}

	@Override
	public int getBoardMaxNum() throws Exception {

		System.out.println("최근 게시글 번호 가져오기");

		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		String sql = "SELECT max(board_num) FROM board"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) { 
				result = rs.getInt(1);
				if (result==0) ++result;
			} else {
				System.out.println("해당되는 정보가 없습니다.");
			}
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(pstmt, rs);
		}
		
		return result;
	} //

	@Override
	public int insertBoard(BoardVO boardVO) throws Exception {

		System.out.println("게시글 쓰기");
		
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		String sql = "INSERT INTO board "
				   + "(BOARD_NUM,"
				   + " BOARD_NAME,"
				   + " BOARD_PASS,"
				   + " BOARD_SUBJECT,"  
			   	   + " BOARD_CONTENT, "
			   	   + " BOARD_FILE, "
			   	   + " BOARD_RE_REF, "  
				   + " BOARD_RE_LEV,"
				   + " BOARD_RE_SEQ,"
				   + " BOARD_READCOUNT,"  
				   + " BOARD_DATE) "
				   + "VALUES (?,?,?,?,?,?,?,?,?,?,sysdate)"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardVO.getBoardNum());
			pstmt.setString(2, boardVO.getBoardName());
			pstmt.setString(3, boardVO.getBoardPass());
			pstmt.setString(4,  boardVO.getBoardSubject());
			pstmt.setString(5, boardVO.getBoardContent());
			pstmt.setString(6,  boardVO.getBoardFile());
			pstmt.setInt(7, boardVO.getBoardNum());
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(pstmt, rs);
		}
		
		return result;
	} //

	@Override
	public BoardVO getBoard(int boardNum) throws Exception {

		System.out.println("개별 게시글 번호 조회");
		
		BoardVO boardVO = null; // null 리턴 => 해당되는 정보없음 통보 조치
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		String sql = "SELECT * FROM board WHERE board_num=?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			
			if (rs.next()) { 
				
				boardVO = new BoardVO();
				boardVO.setBoardNum(boardNum);
				boardVO.setBoardName(rs.getString("board_name"));
				boardVO.setBoardPass(rs.getString("board_pass"));
				boardVO.setBoardSubject(rs.getString("board_subject"));
				boardVO.setBoardContent(rs.getString("board_content"));
				boardVO.setBoardFile(rs.getString("board_file"));
				boardVO.setBoardReRef(rs.getInt("board_re_ref"));
				boardVO.setBoardReLev(rs.getInt("board_re_lev"));
				boardVO.setBoardReSeq(rs.getInt("board_re_seq"));
				boardVO.setBoardReadCount(rs.getInt("board_readcount"));
				// boardVO.setBoardDate(rs.getDate("board_date"));
				
				// 날짜 : 시분초 출력
				// Date date = rs.getDate("board_date"); // 시분초가 말소됨(00:00:00)
				boardVO.setBoardDateByString(rs.getString("board_date"));
				
			} else {
				System.out.println("해당되는 정보가 없습니다.");
			}
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(pstmt, rs);
		}
		
		return boardVO;
	} //
	
	// 특정 SQL 레코드수 조회
	private int getRowCount(String sql) throws Exception {
		
		System.out.println("개별 게시글 번호 조회");
		
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) { 
				result = rs.getInt(1);
			} else {
				System.out.println("해당되는 정보가 없습니다.");
			}
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(pstmt, rs);
		}
		return result;
	} //

	@Override
	public int getListCount() throws Exception {

		System.out.println("게시글 총 목록수");
		return this.getRowCount("SELECT count(*) FROM board");
	}

	@Override
	public List<BoardVO> getBoardByPaging(int page, int limit) throws Exception {

		System.out.println("전체 게시글 페이징 처리");
		
		List<BoardVO> list = new ArrayList<>();
		BoardVO boardVO = null;
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		String sql = "SELECT * FROM ( " + 
				     "   SELECT " + 
					 "        m.*," +
					 "		  FLOOR((ROWNUM - 1)/? + 1) page " + 
					 "   FROM (" + 
					 "          SELECT * FROM board " + 
					 "             ORDER BY BOARD_RE_REF DESC, BOARD_RE_SEQ ASC " + 
					 "        ) m" + 
					 " ) " + 
					 "WHERE page = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, limit);
			pstmt.setInt(2, page);
			rs = pstmt.executeQuery();
			
			while (rs.next()) { 
				
				boardVO = new BoardVO();
				boardVO.setBoardNum(rs.getInt("board_num"));
				boardVO.setBoardName(rs.getString("board_name"));
				boardVO.setBoardPass(rs.getString("board_pass"));
				boardVO.setBoardSubject(rs.getString("board_subject"));
				boardVO.setBoardContent(rs.getString("board_content"));
				boardVO.setBoardFile(rs.getString("board_file"));
				boardVO.setBoardReRef(rs.getInt("board_re_ref"));
				boardVO.setBoardReLev(rs.getInt("board_re_lev"));
				boardVO.setBoardReSeq(rs.getInt("board_re_seq"));
				boardVO.setBoardReadCount(rs.getInt("board_readcount"));
				
				// 날짜 : 시분초 출력
				// Date date = rs.getDate("board_date"); // 시분초가 말소됨(00:00:00)
				boardVO.setBoardDateByString(rs.getString("board_date"));
				
				list.add(boardVO);
			} // 
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(pstmt, rs);
		}
		
		return list;
	} //

	@Override
	public boolean updateReadCount(int boardNum) throws Exception {

		System.out.println("게시글 조회수 증가");
		
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		String sql = "UPDATE board SET "
				   + "board_readcount=board_readcount+1 "
				   + "WHERE board_num=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			if (pstmt.executeUpdate()==1) {
				result = true;
			}
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(pstmt, rs);
		}
		return result;
	} //

	@Override
	public int updateBoard(BoardVO boardVO) throws Exception {

		System.out.println("게시글 수정");
		
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		String sql = "UPDATE board SET "
				   + " BOARD_SUBJECT=?,"  
			   	   + " BOARD_CONTENT=?, "
			   	   + " BOARD_FILE=?, "
				   + " BOARD_DATE=sysdate "
				   + "WHERE board_num=?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardVO.getBoardSubject());
			pstmt.setString(2, boardVO.getBoardContent());
			pstmt.setString(3, boardVO.getBoardFile());
			pstmt.setInt(4, boardVO.getBoardNum());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(pstmt, rs);
		}
		
		return result;
	} //

	@Override
	public int insertReplyBoard(BoardVO boardVO) throws Exception {

		System.out.println("답글(댓글) 쓰기");
		
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		// 댓글 관련 변수
		int replyBoardNum = 0; // 게시글 댓글 아이디
		int reRef = boardVO.getBoardReRef();
		int reLev = boardVO.getBoardReLev();
		int reSeq = boardVO.getBoardReSeq();
		
		// 게시글 댓글 아이디 증가
		replyBoardNum = this.getBoardMaxNum() + 1;
		
		String sql = "UPDATE board SET "
			       + "BOARD_RE_SEQ = BOARD_RE_SEQ + 1 "
				   + "WHERE BOARD_RE_REF = ? "
				   + "AND BOARD_RE_SEQ > ?";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,reRef);
		pstmt.setInt(2,reSeq);
		int updateCount=pstmt.executeUpdate();

		if(updateCount > 0) {
			DbUtil.commit(con);
		}
		
		// 댓글 인자 연산
		reSeq = reSeq + 1;
		reLev = reLev + 1;
		
		/////////////////////////////////////////////////
		
		sql = "INSERT INTO board "
		    + "(BOARD_NUM,"
		    + " BOARD_NAME,"
		    + " BOARD_PASS,"
		    + " BOARD_SUBJECT,"  
	   	    + " BOARD_CONTENT, "
	   	    + " BOARD_FILE, "
	   	    + " BOARD_RE_REF, "  
		    + " BOARD_RE_LEV,"
		    + " BOARD_RE_SEQ,"
		    + " BOARD_READCOUNT,"  
		    + " BOARD_DATE) "
		    + "VALUES (?,?,?,?,?,?,?,?,?,?,sysdate)"; 
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, replyBoardNum);
			pstmt.setString(2, boardVO.getBoardName());
			pstmt.setString(3, boardVO.getBoardPass());
			pstmt.setString(4,  boardVO.getBoardSubject());
			pstmt.setString(5, boardVO.getBoardContent());
			pstmt.setString(6,  " ");
			pstmt.setInt(7, reRef);
			pstmt.setInt(8, reLev);
			pstmt.setInt(9, reSeq);
			pstmt.setInt(10, 0);
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(pstmt, rs);
		}
		
		return result;
	} //	

	@Override
	public boolean isEnableDeleteBoard(int boardNum) throws Exception {

		System.out.println("삭제가 가능한 게시글인지 점검");
		boolean flag = false;
		
		String sql = "SELECT count(board_re_ref) "
				   + "FROM board WHERE board_re_ref="+boardNum;
		
		/*
		-- 1 : 삭제 가능한 원글
		-- 0 : 댓글
		-- 2 이상 : 댓글있는 원글
		*/
		if (this.getRowCount(sql)==1) { // 삭제 가능한 원글
			
			flag = true;
		
		} else {
			
			BoardVO boardVO = this.getBoard(boardNum);

			// 삭제 가능한 답글(댓글) 
			// : 삭제하고자 하는 게시글의 레벨이 원글의 답글중 상대적으로 최고 트리 레벨(level)이면 삭제 가능 아니면 불가 
			sql = "SELECT max(board_re_lev) "
				+ "FROM board WHERE board_re_ref="+boardVO.getBoardReRef();
			
			int maxLevel = this.getRowCount(sql);
			int boardLevel = boardVO.getBoardReLev();
			
			System.out.println("### 최고 레벨 : "+maxLevel);
			System.out.println("### 게시글 레벨 : "+boardLevel);
			
			if (boardLevel == maxLevel) {
				System.out.println("삭제 가능한 댓글");
				flag = true;				
			} else {
				System.out.println("삭제 불가한 댓글");
				flag = false;
			} //
			
		} //
		
		return flag;
	} //

	@Override
	public int deleteBoard(int boardNum) {

		System.out.println("게시글 삭제");
		
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		String sql = "DELETE board WHERE board_num=?"; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			result = pstmt.executeUpdate();
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(pstmt, rs);
		}
		
		return result;
	}

	@Override
	public List<BoardVO> getBoardBySearchPaging(String fld, 
											    String value, 
											    boolean isLike, 
											    int page, 
											    int limit) throws Exception {

		System.out.println("게시글 검색 조회 : 페이징");
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		List<BoardVO> list = new ArrayList<>();
		BoardVO boardVO = null;
		
		// 유사 검색 여부에 따른 연산자 및 필드값 부분 처리, SQL 구문
        String expr = isLike == true ? "like '%"+value+"%'" : "= '"+value+"'"; 
       
        String sql = "SELECT * "  
                   + "FROM (SELECT ROWNUM, "  
                   + "             m.*,  "  
                   + "             FLOOR((ROWNUM - 1) / ? + 1) page "  
                   + "      FROM ( "
                   + "             SELECT * FROM board "  
                   + "             WHERE " + fld + " " + expr
                   + "             ORDER BY board_num ASC"  
                   + "           ) m  "  
                   + "      )   "  
                   + "WHERE page = ?";
                
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, limit);
			pstmt.setInt(2, page);
			rs = pstmt.executeQuery();
			
			while (rs.next()) { 
				
				boardVO = new BoardVO();
				boardVO.setBoardNum(rs.getInt("board_num"));
				boardVO.setBoardName(rs.getString("board_name"));
				boardVO.setBoardPass(rs.getString("board_pass"));
				boardVO.setBoardSubject(rs.getString("board_subject"));
				boardVO.setBoardContent(rs.getString("board_content"));
				boardVO.setBoardFile(rs.getString("board_file"));
				boardVO.setBoardReRef(rs.getInt("board_re_ref"));
				boardVO.setBoardReLev(rs.getInt("board_re_lev"));
				boardVO.setBoardReSeq(rs.getInt("board_re_seq"));
				boardVO.setBoardReadCount(rs.getInt("board_readcount"));
				
				// 날짜 : 시분초 출력
				// Date date = rs.getDate("board_date"); // 시분초가 말소됨(00:00:00)
				boardVO.setBoardDateByString(rs.getString("board_date"));
				
				list.add(boardVO);
			} // 
			
			System.out.println(2);
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(pstmt, rs);
		}
		
		System.out.println(3);
		
		return list;
	} //

	@Override
	public List<BoardVO> getBoardBySearch(String fld, String value, boolean isLike) throws Exception {

		System.out.println("게시글 검색 조회");
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		List<BoardVO> list = new ArrayList<>();
		BoardVO boardVO = null;
		
		// 유사 검색 여부에 따른 연산자 및 필드값 부분 처리, SQL 구문
        String expr = isLike == true ? "like '%"+value+"%'" : "= '"+value+"'"; 
       
        String sql = "SELECT * "  
                   + "FROM (SELECT ROWNUM, "  
                   + "             m.*  "  
                   + "      FROM ( "
                   + "             SELECT * FROM board "  
                   + "             WHERE " + fld + " " + expr
                   + "             ORDER BY board_num ASC"  
                   + "           ) m  "  
                   + "      )";
                
        PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) { 
				
				boardVO = new BoardVO();
				boardVO.setBoardNum(rs.getInt("board_num"));
				boardVO.setBoardName(rs.getString("board_name"));
				boardVO.setBoardPass(rs.getString("board_pass"));
				boardVO.setBoardSubject(rs.getString("board_subject"));
				boardVO.setBoardContent(rs.getString("board_content"));
				boardVO.setBoardFile(rs.getString("board_file"));
				boardVO.setBoardReRef(rs.getInt("board_re_ref"));
				boardVO.setBoardReLev(rs.getInt("board_re_lev"));
				boardVO.setBoardReSeq(rs.getInt("board_re_seq"));
				boardVO.setBoardReadCount(rs.getInt("board_readcount"));
				
				// 날짜 : 시분초 출력
				// Date date = rs.getDate("board_date"); // 시분초가 말소됨(00:00:00)
				boardVO.setBoardDateByString(rs.getString("board_date"));
				
				list.add(boardVO);
			} // 
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(pstmt, rs);
		}
		
		return list;
	} //

} //