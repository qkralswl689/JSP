package com.javateam.board_project.board.service;

import java.sql.Connection;
import java.util.List;

import com.javateam.board_project.board.dao.BoardDAO;
import com.javateam.board_project.board.dao.BoardDAOImpl;
import com.javateam.board_project.board.domain.BoardVO;
import com.javateam.board_project.board.util.DbUtil;

public class BoardServiceImpl implements BoardService {
	
	// DAO 객체
	BoardDAO dao = BoardDAOImpl.getInstance();
	
	// 싱글턴 객체
	private static BoardServiceImpl INSTANCE = null;
	
	private BoardServiceImpl() {}
	
	public static final BoardServiceImpl getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new BoardServiceImpl();
		}
		return INSTANCE;
	}
	
	@Override
	public int getBoardMaxNum() throws Exception {

		System.out.println("Service : 최근 게시글 번호 가져오기");
		Connection con = DbUtil.connect();
		dao.setConnection(con);
		int result = dao.getBoardMaxNum();
		
		if (result>0) {
			DbUtil.commit(con);
		} else {
			DbUtil.rollback(con);
			result = -1; // 비정상 상황
		}
		
		return result;
	} //

	@Override
	public boolean insertBoard(BoardVO boardVO) throws Exception {

		System.out.println("Service : 게시글 생성");
		Connection con = DbUtil.connect();
		dao.setConnection(con);
		boolean flag = false; // 저장 성공 여부
		int result = dao.insertBoard(boardVO); 
		
		if (result==1) {
			DbUtil.commit(con);
			flag = true;
		} else {
			DbUtil.rollback(con);
		}
		
		DbUtil.close(con);
		return flag;
	} //

	@Override
	public BoardVO getBoard(int boardNum) throws Exception {

		System.out.println("Service : 개별 게시글 조회");
		Connection con = DbUtil.connect();
		dao.setConnection(con);
		BoardVO boardVO = dao.getBoard(boardNum);
		
		if (boardVO != null) {
			DbUtil.commit(con);
		} else {
			DbUtil.rollback(con);
		}
		
		DbUtil.close(con);
		return boardVO;
	} //

	@Override
	public int getListCount() throws Exception {

		System.out.println("게시글 총 목록수");
		Connection con = DbUtil.connect();
		dao.setConnection(con);
		int result = dao.getListCount();
		
		if (result > 0) {
			DbUtil.commit(con);
		} else {
			DbUtil.rollback(con);
		}
		
		DbUtil.close(con);
		return result;
	}

	@Override
	public List<BoardVO> getBoardByPaging(int page, int limit) throws Exception {

		System.out.println("전체 게시글 페이징 처리");
		
		Connection con = DbUtil.connect();
		dao.setConnection(con);
		List<BoardVO> list = dao.getBoardByPaging(page, limit);
		
		if (list != null) {
			DbUtil.commit(con);
		} else {
			DbUtil.rollback(con);
		}
		
		DbUtil.close(con);
		return list;
	}

	@Override
	public boolean updateReadCount(int boardNum) throws Exception {
		
		System.out.println("게시글 조회수 증가");
		
		Connection con = DbUtil.connect();
		dao.setConnection(con);
		boolean result = dao.updateReadCount(boardNum);
		
		if (result == true) {
			DbUtil.commit(con);
		} else {
			DbUtil.rollback(con);
		}
		
		DbUtil.close(con);
		return result;
	} //

	@Override
	public boolean updateBoard(BoardVO boardVO) throws Exception {
		
		System.out.println("Service : 게시글 수정");
		Connection con = DbUtil.connect();
		dao.setConnection(con);
		boolean flag = false; // 수정 성공 여부
		int result = dao.updateBoard(boardVO); 
		
		if (result==1) {
			DbUtil.commit(con);
			flag = true;
		} else {
			DbUtil.rollback(con);
		}
		
		DbUtil.close(con);
		return flag;
	} //

	@Override
	public boolean insertReplyBoard(BoardVO boardVO) throws Exception {
		
		System.out.println("Service : 답글(댓글) 생성");
		Connection con = DbUtil.connect();
		dao.setConnection(con);
		boolean flag = false; // 저장 성공 여부
		int result = dao.insertReplyBoard(boardVO); 
		
		if (result==1) {
			DbUtil.commit(con);
			flag = true;
		} else {
			DbUtil.rollback(con);
		}
		
		DbUtil.close(con);
		return flag;
	}

	@Override
	public boolean isEnableDeleteBoard(int boardNum) throws Exception {

		System.out.println("Service : 게시글 삭제 가능 여부 점검");
		Connection con = DbUtil.connect();
		dao.setConnection(con);
		boolean flag = false;
		
		flag = dao.isEnableDeleteBoard(boardNum);
		
		if (flag==true) { // 삭제 가능한 게시글
			DbUtil.commit(con);
		} else {
			DbUtil.rollback(con);
		}
		
		return flag;
	} //

	@Override
	public boolean deleteBoard(int boardNum) throws Exception {

		System.out.println("Service : 게시글 삭제");
		Connection con = DbUtil.connect();
		dao.setConnection(con);
		boolean flag = false;
		
		if (dao.deleteBoard(boardNum)==1) {
			DbUtil.commit(con);
			flag = true;
		} else {
			DbUtil.rollback(con);
		}
		
		return flag;
	} //

	@Override
	public List<BoardVO> getBoardBySearchAndPaging(String searchKind, String searchWord, int page, int limit)
			throws Exception {

		System.out.println("Service : 게시글 검색어 조회 : 페이징");
		Connection con = DbUtil.connect();
		dao.setConnection(con);
		
		System.out.println("DB 연결");
		List<BoardVO> list = dao.getBoardBySearchPaging(searchKind, searchWord, true, page, limit);
		
		/////////////////////////////////////////////////////////
		
		for (BoardVO v : list) { System.out.println(v); }
		
		if (list.size() > 0) {
			DbUtil.commit(con);
		} else {
			System.out.println("결과 없음");
			DbUtil.rollback(con);
		}
		
		return list;
	}

	@Override
	public List<BoardVO> getBoardBySearch(String searchKind, String searchWord) throws Exception {

		System.out.println("Service : 게시글 검색어 조회");
		Connection con = DbUtil.connect();
		dao.setConnection(con);
		List<BoardVO> list = dao.getBoardBySearch(searchKind, searchWord, true);
		
		if (list.size() > 0) {
			DbUtil.commit(con);
		} else {
			System.out.println("결과 없음");
			DbUtil.rollback(con);
		}
		
		return list;
	}

} //