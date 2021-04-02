package com.javateam.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;
import com.javateam.member.domain.PageVO;
import com.javateam.member.domain.RoleVO;

public class MemberViewRoleAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("전체 회원 등급 조회(관리)");
		
		int page = request.getParameter("page")==null ? 1 
				 : new Integer(request.getParameter("page")); 
		int limit = 10; // 한 페이지에 표현될 수 있는 인원수 
		
		MemberDAO dao = MemberDAOImpl.getInstance();
		List<RoleVO> roles = dao.getRolesByPaging(page, limit);
		
		int rolesNum = dao.getRolesNumber();
		int listCount = roles.size();
		
		System.out.println("rolesNum : "+rolesNum);
		
		// 총 페이지 수
   		int maxPage = (int)((double)rolesNum/10+0.95); //0.95를 더해서 올림 처리
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
		
		System.out.println("PageVO : "+pageVO);
		
		// 전송 인자  
		request.setAttribute("pageVO", pageVO);
		request.setAttribute("roles", roles);
		request.setAttribute("limit", limit);
		
		return "/template.do?content_page=/member/viewRoles.jsp";
	} //

}
