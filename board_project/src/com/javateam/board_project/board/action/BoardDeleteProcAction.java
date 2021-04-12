package com.javateam.board_project.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.board_project.board.domain.BoardVO;
import com.javateam.board_project.board.service.BoardService;
import com.javateam.board_project.board.service.BoardServiceImpl;
import com.javateam.board_project.controller.CommandAction;

public class BoardDeleteProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("게시글 삭제 처리");
		String msg = ""; // 리턴 메시지
		
		int boardNum = new Integer(request.getParameter("boardNum"));
		String boardPass = request.getParameter("boardPass");
		
		BoardService boardService = BoardServiceImpl.getInstance();
		BoardVO boardVO = boardService.getBoard(new Integer(boardNum)); 
		
		// 패쓰워드 점검
		if (boardVO.getBoardPass().contentEquals(boardPass)) {
		
			// 삭제 가능한 원글/댓글 여부 판독
			// 삭제 가능한 게시글 : 댓글이 없는 원글/댓글
			// 댓글이 없는 원글 : board_re_ref가 게시판 테이블에서 1개만 존재
			
			if (boardService.isEnableDeleteBoard(boardNum)) {
				
				if (boardService.deleteBoard(boardNum)==true) {
					msg = "게시글 삭제에 성공하였습니다.";
				} else {
					msg = "게시글 삭제에 실패하였습니다.";
				} //
				
			} else {
				msg = "삭제가 불가능한 게시글입니다.";
			} //
		
		} else { // 패쓰워드가 틀렸을 경우
		
			msg = "게시글의 비밀번호가 일치하지 않습니다. 다시 입력하십시오.";
		} //
		
		request.setAttribute("json", msg);
		
		return "/board/json.jsp";
	} //

}
