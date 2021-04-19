package com.javateam.board_project.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.board_project.board.domain.BoardVO;
import com.javateam.board_project.board.domain.PageVO;
import com.javateam.board_project.board.service.BoardService;
import com.javateam.board_project.board.service.BoardServiceImpl;
import com.javateam.board_project.controller.CommandAction;

public class BoardViewAllPagingAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("전체 게시글 조회 : 페이징");
		
		int page = request.getParameter("page")==null ? 1 :
			     new Integer(request.getParameter("page").trim());
		int limit = 10; // 한페이지당 10개씩 글
		int pagingStep = 3;
		
		BoardService boardService = BoardServiceImpl.getInstance();
		int listCount = boardService.getListCount();
		List<BoardVO> list = boardService.getBoardByPaging(page, limit);
		
		// 페이지 계산
		// 총 페이지 수.
   		int maxPage=(int)((double)listCount/limit + 0.95); // 0.95를 더해서 올림 처리.
   		
   		// 현재 페이지에 보여줄 시작 페이지 수(pagingStep=10일 경우 : 51, 11, 21 등등...)
   		int startPage = (((int) ((double)page/pagingStep + 0.9)) - 1) * pagingStep + 1;
   		
   		// 현재 페이지에 보여줄 마지막 페이지 수.(pagingStep=10일 경우 : 10, 20, 30 등등...)
   		int endPage = startPage + pagingStep - 1;

   		if (endPage> maxPage) endPage = maxPage;

   		// 페이징 정보(VO) 생성 -> 인자가 많을 때 사용
   		PageVO pageVO = new PageVO();
   		pageVO.setEndPage(endPage);
   		pageVO.setListCount(listCount);
		pageVO.setMaxPage(maxPage);
		pageVO.setPage(page);
		pageVO.setStartPage(startPage);	
		
		// 인자 전송
		request.setAttribute("limit", limit);
		request.setAttribute("pageVO", pageVO);
		request.setAttribute("boardList", list);
		
		return "/board/board_list.jsp";
	} //

}