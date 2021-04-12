package com.javateam.board_project.board.dao;

import java.sql.Connection;
import java.util.List;

import com.javateam.board_project.board.domain.BoardVO;

public interface BoardDAO {
	
	/**
	 * DB 연결 객체 설정 (연결 객체를 멤버 필드로 공유)
	 * 
	 * @param con DB 연결 객체
	 */
	void setConnection(Connection con);
	
	/**
	 * 현재 최근 게시글 번호 획득
	 * 
	 * @return 최근 게시글 번호
	 * @throws Exception 예외처리
	 */
	int getBoardMaxNum() throws Exception;
	
	/**
	 * 게시글 쓰기
	 *  
	 * @param boardVO 게시판 객체
	 * @return 삽입 레코드(record, row) 개수
	 * @throws Exception 예외처리
	 */
	int insertBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * 개별 게시글 조회
	 * 
	 * @param boardNum 게시글 번호
	 * @return 게시판 객체
	 * @throws Exception 예외처리
	 */
	BoardVO getBoard(int boardNum) throws Exception;
	
	/**
	 * 게시글 목록 수 조회
	 * 
	 * @return 게시글 목록 수
	 * @throws Exception 예외처리
	 */
	int getListCount() throws Exception;
	
	/**
	 * 전체 게시글 조회 : 페이징
	 * 
	 * @param page 현재 페이지
	 * @param limit 페이지당 표시 게시글 수
	 * @return 게시글 목록
	 */
	List<BoardVO> getBoardByPaging(int page, int limit) throws Exception;
	
	/**
	 * 게시글 조회수 증가
	 * 
	 * @param boardNum 게시글 번호
	 * @return 업데이트 성공 여부
	 * @throws Exception 예외처리
	 */
	boolean updateReadCount(int boardNum) throws Exception;
	

	/**
	 * 게시글 수정
	 *  
	 * @param boardVO 게시판 객체
	 * @return 수정 레코드(record, row) 개수
	 * @throws Exception 예외처리
	 */
	int updateBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * 답글(댓글) 쓰기
	 *  
	 * @param boardVO 게시판 객체
	 * @return 삽입 레코드(record, row) 개수
	 * @throws Exception 예외처리
	 */
	int insertReplyBoard(BoardVO boardVO) throws Exception;

	/**
	 * 삭제가 가능한 게시글인지 점검
	 * @param boardNum 게시글 번호
	 * @return 삭제 가능 여부(삭제 가능 : true)
	 */
	boolean isEnableDeleteBoard(int boardNum) throws Exception;

	/**
	 * 게시글 삭제
	 * 
	 * @param boardNum 게시글 번호
	 * @return 삭제 레코드(record, row) 개수
	 */
	int deleteBoard(int boardNum) throws Exception;

	/**
	 * 검색/페이징을 이용한 게시글 조회 : 페이징
	 *  
	 * @param fld 검색할 필드
	 * @param value 필드 값
	 * @param isLike 유사 검색(like) 여부  usage) 유사 검색 : true, 동등 검색 : false
	 * @param page 페이지
	 * @param limit 페이징당 게시글 출력수 제한  
	 * @return 검색 결과 게시글 객체
	 * @throws Exception 예외처리
	 */
	List<BoardVO> getBoardBySearchPaging(String fld, 
										 String value,
										 boolean isLike,
										 int page,
										 int limit) throws Exception;

	/**
	 * 검색/페이징을 이용한 게시글 조회
	 * 
	 * @param fld 검색할 필드
	 * @param value 필드 값
	 * @param isLike 유사 검색(like) 여부  usage) 유사 검색 : true, 동등 검색 : false
	 * @return 검색 결과 게시글 객체
	 * @throws Exception 예외처리
	 */
	List<BoardVO> getBoardBySearch(String fld, String value, boolean isLike) throws Exception;
} //