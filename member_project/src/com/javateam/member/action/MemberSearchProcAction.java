package com.javateam.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;
import com.javateam.member.domain.MemberVO;
import com.javateam.member.domain.PageVO;

public class MemberSearchProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("회원 정보 검색");
		String returnPath = "";
		
		// 검색 구분 및 검색어 점검
		if (request.getParameter("search_word")==null || 
			request.getParameter("search_word").trim().equals("")) {
			
			System.out.println("검색어 미입력");
			request.setAttribute("msg", "검색어를 입력하십시오.");
			request.setAttribute("move_page", "/member/viewAll.do");
			// returnPath = "/error/result.jsp";
			// template 적용시
			returnPath = "/template.do?content_page=/error/result.jsp";
			
		} else {

			String searchKind = request.getParameter("search_kind");
			String searchWord = request.getParameter("search_word").trim();
			
			System.out.println("검색 종류 : " + searchKind);
			System.out.println("검색어 : " + searchWord);
			
			boolean isLike = true; // 유사 검색 여부

			String fld = searchKind.contentEquals("아이디") ? "member_id" :
						 searchKind.contentEquals("별명") ? "member_nickname" :
						 searchKind.contentEquals("이름") ? "member_name" :
					     searchKind.contentEquals("기본 주소") ? "member_address" :
				    	 searchKind.contentEquals("상세 주소") ? "member_address" : "member_id";  
			
			// 유사 검색 설정
			if (searchKind.contentEquals("기본 주소") || searchKind.contentEquals("상세 주소"))
				isLike = true;
							
			int page = request.getParameter("page")==null ? 1 : new Integer(request.getParameter("page")); 
			int limit = 10; // 한 페이지에 표현될 수 있는 인원수 
			
			System.out.println("#### 검색 필드 : "+fld);
			System.out.println("#### 검색어 : "+searchWord);
			
			MemberDAO dao = MemberDAOImpl.getInstance();
			// List<MemberVO> members = dao.getMembersByPaging(page, limit);
			List<MemberVO> members = dao.getMembersByFieldAndPaging(fld, searchWord, isLike, page, limit);
			
			// int membersNum = dao.getAllMembers().size();
			// 교정
			int membersNum = dao.getMembersByField(fld, searchWord, isLike).size();
			int listCount = members.size();
			
			System.out.println("membersNum : " + membersNum);
			System.out.println("listCount : " + listCount);
			
			// 총 페이지 수
	   		int maxPage = (int)((double)membersNum/10+0.95); //0.95를 더해서 올림 처리
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
			request.setAttribute("members", members);
			request.setAttribute("limit", limit);
			
			//  검색 페이징 인자 : 검색 여부/검색필드/검색어 전송
			request.setAttribute("searchYN", "search");
			request.setAttribute("searchKind", searchKind);
			request.setAttribute("searchWord", searchWord);
			
			// return "/member/viewAll.jsp";
			// template 적용시
			return "/template.do?content_page=/member/viewAll.jsp";

		} // if
		
		return returnPath;
	} //

}
