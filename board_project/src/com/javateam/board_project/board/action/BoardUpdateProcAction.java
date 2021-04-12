package com.javateam.board_project.board.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.board_project.board.domain.BoardVO;
import com.javateam.board_project.board.service.BoardService;
import com.javateam.board_project.board.service.BoardServiceImpl;
import com.javateam.board_project.board.util.BoardFileReNamePolicy;
import com.javateam.board_project.controller.CommandAction;
import com.oreilly.servlet.MultipartRequest;

public class BoardUpdateProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("#### 게시판 수정 처리");
		String msg = ""; // 리턴 메시지
		
		// 인자 인쇄
		String realFolder = "d:\\upload"; // 업로드 폴더 절대 경로 변경
		// String saveFolder = "upload";
		int fileSize = 5*1024*1024;
		// realFolder = request.getServletContext().getRealPath(saveFolder);
		
		// 업로드 파일 중복 방지 : 고유 파일 정책 으로 변경
		// file format) 파일 형식 : 원본 파일명 + "_" + 날짜 포맷 + 해쉬코드 + 확장자
		MultipartRequest multi 
			= new MultipartRequest(request,
								   realFolder,
								   fileSize,
								   "UTF-8",
								   new BoardFileReNamePolicy());
		
		/*
		 * @SuppressWarnings("unchecked") Enumeration<String> params =
		 * multi.getParameterNames();
		 * 
		 * while (params.hasMoreElements()) {
		 * 
		 * String key = params.nextElement(); String value = multi.getParameter(key);
		 * 
		 * System.out.println("인자 : "+key + " = " + value); }
		 */
		
        System.out.println("//////////////////////////////////////////");
		
		BoardService boardService = BoardServiceImpl.getInstance();
		BoardVO boardVO = boardService.getBoard(Integer.parseInt(multi.getParameter("boardNum")));
		System.out.println("기존 정보 boardVO : "+boardVO);
		
		String boardPass = multi.getParameter("boardPass").trim();
		
		// 게시글 비밀번호 점검
		if (boardVO.getBoardPass().contentEquals(boardPass)) {
			
			System.out.println("### 게시글 비밀번호 일치");
			
			boardVO.setBoardSubject(multi.getParameter("boardSubject"));
			boardVO.setBoardContent(multi.getParameter("boardContent"));
			
			System.out.println("첨부 원본 파일명  :  " + multi.getOriginalFileName("boardFile"));
			System.out.println("실제 저장 파일명  :  " + multi.getFilesystemName("boardFile"));
			
			// 첨부 파일이 없을 경우 기존의 첨부 파일 유지
			System.out.println("### 첨부 파일 여부 : "+ (multi.getOriginalFileName("boardFile")!=null ? "있음" : "없음"));
			
			// 파일 첨부가 있으면 저장
			if (multi.getOriginalFileName("boardFile")!=null) {
				
				System.out.println("#### 실제 저장 파일명  :  " + multi.getFilesystemName("boardFile"));
				boardVO.setBoardFile(multi.getFilesystemName("boardFile"));
			} //
			
			// 갱신 정보
			System.out.println("갱신 파일 : "+boardVO.getBoardFile());
			System.out.println("갱신 boardVO : "+boardVO);
			
			// 갱신
			if (boardService.updateBoard(boardVO)==true) {
				msg = "게시글 수정에 성공하였습니다.";
			} else {
				msg = "게시글 수정에 실패하였습니다.";
			}
			
			request.setAttribute("json", msg);
			
		} else { // 비밀번호 불일치시
			
			msg = "게시글의 비밀번호가 일치하지 않습니다. 다시 입력하십시오.";
			System.out.println("msg : "+msg);			
			request.setAttribute("json", msg);
		} //
		
		return "/board/json.jsp";
	} //

}
