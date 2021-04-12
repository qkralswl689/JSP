package com.javateam.board_project.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.board_project.board.domain.BoardVO;
import com.javateam.board_project.board.domain.PageVO;
import com.javateam.board_project.board.service.BoardService;
import com.javateam.board_project.board.service.BoardServiceImpl;
import com.javateam.board_project.controller.CommandAction;

public class BoardSearchProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("########## 게시글 검색 처리");
		
		String searchKind = request.getParameter("search_kind");
		String searchWord = request.getParameter("search_word").trim();
		int page = request.getParameter("page")==null || 
				   request.getParameter("page").trim().contentEquals("") ? 1 : 
				   new Integer(request.getParameter("page"));   	
		int limit = 10;
		
		System.out.println("page : "+page);
		
		// 검색 구분 변환
		String searchKindToFld = searchKind.contentEquals("작성자") ? "board_name" : "board_subject";
		
		System.out.println("# 검색 구분 : "+searchKind);
		System.out.println("# 검색어 : "+searchWord);
		
		BoardService boardService = BoardServiceImpl.getInstance();
		List<BoardVO> list = boardService.getBoardBySearchAndPaging(searchKindToFld, searchWord, page, 10);
		
		// 추가
		if (list == null) {
			
			System.out.println("검색 결과 없음");
			request.setAttribute("msg", "검색 결과 없음");
			request.setAttribute("move_page", "/board/viewAll.do");
			return "/error/result.jsp";
			
		} else {
			
			int boardsCount = boardService.getBoardBySearch(searchKindToFld, searchWord).size();
			int listCount = list.size();
		
			// 총 페이지 수
	        int maxPage = (int)((double)boardsCount/10+0.95); //0.95를 더해서 올림 처리
	        // 현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21,...)
	        int startPage = (((int) ((double)page/10 + 0.9)) - 1) * 10 + 1;
	        // 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30, ...)
	        int endPage = startPage + 10 - 1;
	       
	        System.out.println("startPage : "+startPage);
	        System.out.println("endPage : "+endPage);
	       
	        if (endPage > maxPage) endPage = maxPage;
	       
	        PageVO pageVO = new PageVO();
	        pageVO.setMaxPage(maxPage);
	        pageVO.setPage(page);
	        pageVO.setStartPage(startPage);
	        pageVO.setEndPage(endPage);
	        pageVO.setListCount(listCount);
	       
	        // 전송 인자  
	        request.setAttribute("pageVO", pageVO);
	        request.setAttribute("boardList", list);
	        request.setAttribute("limit", limit);
	       
	        //  검색 페이징 인자 : 검색 여부/검색필드/검색어 전송
	        request.setAttribute("searchYN", "search");
	        request.setAttribute("searchKind", searchKind);
	        request.setAttribute("searchWord", searchWord);
	        
	        System.out.println("### 게시글 인자 생성");
			
			return "/board/board_list.jsp";
		} //
		
	} //

}
