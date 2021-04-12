package com.javateam.board_project.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.board_project.board.domain.BoardVO;
import com.javateam.board_project.board.service.BoardService;
import com.javateam.board_project.board.service.BoardServiceImpl;
import com.javateam.board_project.controller.CommandAction;

public class BoardReplyWriteProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("//////////////////////////////////////");
		System.out.println("게시글 답글 쓰기 처리");
		System.out.println("//////////////////////////////////////");
		String msg = "";
		
		// 인자 확인
		request.getParameterMap().forEach((k,v)->System.out.println(k+"="+v[0]));
		System.out.println("//////////////////////////////////////");
		
		// 답글(댓글) 객체 생성
		BoardService boardService = BoardServiceImpl.getInstance();
		BoardVO boardVO = new BoardVO();
		
		int boardNum = new Integer(request.getParameter("boardNum"));
		
		boardVO.setBoardName(request.getParameter("boardName"));
		boardVO.setBoardPass(request.getParameter("boardPass"));
		boardVO.setBoardSubject(request.getParameter("boardSubject"));
		boardVO.setBoardContent(request.getParameter("boardContent"));
		
		// 답글(댓글) 관련
		// 프론트엔드(front-end)에서 히든(hidden) 필드 인자로 전송받는 대신 기존 DB에서 읽어오도록 조치
		BoardVO legacyBoardVO = boardService.getBoard(boardNum);
		boardVO.setBoardReLev(legacyBoardVO.getBoardReLev());
		boardVO.setBoardReRef(legacyBoardVO.getBoardReRef());
		boardVO.setBoardReSeq(legacyBoardVO.getBoardReSeq());
		
		// 답글(댓글) 저장
		if (boardService.insertReplyBoard(boardVO)==true) {
			msg = "답글(댓글) 저장에 성공하였습니다.";
		} else {
			msg = "답글(댓글) 저장에 실패하였습니다.";
		}
		
		// 메시지 인자 생성
		request.setAttribute("json", msg);
		
		return "/board/json.jsp";
	} //

}