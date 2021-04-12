package com.javateam.board_project.board.service;

import java.util.List;

import com.javateam.board_project.board.domain.BoardVO;

public interface BoardService {

	/**
	 * 최근 게시글 번호 가져오기 서비스
	 * 
	 * @return 최근 계시글 번호
	 * @throws Exception 예외처리
	 */
	int getBoardMaxNum() throws Exception;
	
	/**
	 * 글쓰기 서비스
	 * 
	 * @param boardVO 게시판 객체
	 * @return 성공 여부
	 * @throws Exception 예외처리
	 */
	boolean insertBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * 개별 게시글 조회
	 * 
	 * @param boardNum 게시글 번호
	 * @return 게시판 객체
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
	 * 글수정 서비스
	 * 
	 * @param boardVO 게시판 객체
	 * @return 성공 여부
	 * @throws Exception 예외처리
	 */
	boolean updateBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * 답글(댓글) 쓰기
	 *  
	 * @param boardVO 게시판 객체
	 * @return 성공 여부
	 * @throws Exception 예외처리
	 */
	boolean insertReplyBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * 삭제가 가능한 게시글인지 점검
	 * @param boardNum 게시글 번호
	 * @return 삭제 가능 여부(삭제 가능 : true)
	 */
	boolean isEnableDeleteBoard(int boardNum) throws Exception;
	
	/**
	 * 글삭제 서비스
	 * 
	 * @param boardNum 게시글 번호
	 * @return 성공 여부
	 * @throws Exception 예외처리
	 */
	boolean deleteBoard(int boardNum) throws Exception;

	/**
	 * 검색/페이징을 이용한 게시글 조회 : 페이징 
	 *  
	 * @param searchKind 검색 종류(구분)
	 * @param searchWord 검색어
 	 * @param page 페이지
	 * @param limit 페이징당 게시글 출력수 제한  
	 * @return 검색 결과 게시글 객체
	 * @throws Exception 예외처리
	 */
	List<BoardVO> getBoardBySearchAndPaging(String searchKind, 
											String searchWord,
											int page, 
											int limit) throws Exception;

	/**
	 * 검색/페이징을 이용한 게시글 조회
	 * 
	 * @param searchKind 검색 종류(구분)
	 * @param searchWord 검색어
	 * @return
	 */
	List<BoardVO> getBoardBySearch(String searchKind, String searchWord) throws Exception;
}