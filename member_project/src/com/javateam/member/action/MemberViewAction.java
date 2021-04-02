package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;
import com.javateam.member.domain.MemberVO;

public class MemberViewAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("개별 회원 조회");
		String returnPage = "";
		
		// 아이디 인자 점검
		if (request.getParameter("memberId")==null) {
			
			request.setAttribute("msg", "회원 아이디를 입력하십시오.");
			// returnPage = "/error/result.jsp";
			// template 적용시
			returnPage = "/template.do?content_page=/error/result.jsp";
			
		} else {
		
			String memberId = request.getParameter("memberId").trim();
			MemberDAO dao = MemberDAOImpl.getInstance();
			
			// 회원인지 여부 점검
			if (dao.isMember(memberId) == false) {
				
				request.setAttribute("msg", "조회하고자 하는 회원이 존재하지 않습니다.");
				// returnPage = "/error/result.jsp";
				// template 적용시
				returnPage = "/template.do?content_page=/error/result.jsp";
				
			} else {
				
				MemberVO member = dao.getMember(memberId);
				
				// 인자 생성 및 전송
				request.setAttribute("member", member);
				// returnPage = "/member/member_view.jsp";
				// template 적용시
				returnPage = "/template.do?content_page=/member/member_view.jsp";
			} //
			
		}
		
		return returnPage;
	} //

}
