package com.javateam.board_project.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.board_project.board.domain.BoardVO;
import com.javateam.board_project.board.service.BoardService;
import com.javateam.board_project.board.service.BoardServiceImpl;
import com.javateam.board_project.board.util.BoardFileUtil;
import com.javateam.board_project.controller.CommandAction;

public class BoardViewAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("개별 게시글 조회");
		
		int boardNum;
		
		// 인자 수신 점검
		if (request.getParameter("boardNum")==null || 
			request.getParameter("boardNum").trim().contentEquals("")) {
			
			request.setAttribute("msg", "조회할 게시글 아이디를 입력하십시오.");
			request.setAttribute("move_page", "/board/index.do");
			return "/error/result.jsp";
			
		} else {
			
			boardNum = Integer.parseInt(request.getParameter("boardNum").trim());
			BoardService boardService = BoardServiceImpl.getInstance();
			BoardVO boardVO = boardService.getBoard(boardNum);
			
			// 추가 조회수 증가
			boardService.updateReadCount(boardNum);
			
			// 게시글이 없을 경우
			if (boardVO==null) {
			
				request.setAttribute("msg", "조회할 게시글이 없습니다.");
				request.setAttribute("move_page", "/board/index.do");
				return "/error/result.jsp";
			
			} else {
				
				request.setAttribute("boardVO", boardVO);
				// 업로드 파일 정책 변경 후 적용(별도의 파일 변수 전송)
				String originalFileName = BoardFileUtil.getOriginalFileName(boardVO.getBoardFile());
				request.setAttribute("originalUploadFile", originalFileName);
			} //
			
		} //
		
		return "/board/view_board.jsp";
	} //

}