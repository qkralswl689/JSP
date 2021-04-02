package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;

public class MemberPwSearchProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("패쓰워드 분실 검색 처리");
		
		String memberId = request.getParameter("memberId");
		String memberName = request.getParameter("memberName");
		String memberEmail = request.getParameter("memberEmail");
		String memberPhone = request.getParameter("memberPhone");
		
		MemberDAO dao = MemberDAOImpl.getInstance();
		
		String msg = dao.getMemberPwByMemberInfo(memberId, memberName, memberEmail, memberPhone);
		
		request.setAttribute("result_msg", msg);
		
		return "/template.do?content_page=/member/pw_lost_search.jsp";
	} //

}