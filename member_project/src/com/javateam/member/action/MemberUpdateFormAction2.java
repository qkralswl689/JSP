package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;
import com.javateam.member.domain.MemberVO;

public class MemberUpdateFormAction2 implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("회원수정폼");
		String returnPath = "";
		// 세션 
		HttpSession session = request.getSession();
		
		if (request.getParameter("memberId")==null ||
			request.getParameter("memberId").trim().equals("")) // ||
			//request.getParameter("movePage")==null || 
			//request.getParameter("movePage").trim().equals("")) {
		{		
			System.out.println("아이디 혹은 이동 페이지 미입력");
			request.setAttribute("msg", "회원 아이디를 입력하십시오.");
			// request.setAttribute("msg", "회원 아이디/이동 페이지를 입력하십시오.");
			
			// String movePage = request.getParameter("movePage")==null ? "" 
			//	 	        : request.getParameter("movePage").trim();
			
			// request.setAttribute("move_page", movePage);
			
			// returnPath = "/error/result.jsp";
			// template 적용시
			returnPath = "/template.do?content_page=/error/result.jsp";
			
		} else {
			
			String memberId = request.getParameter("memberId").trim();
			
			// 이 부분에서 기존 회원정보에 대한 세션여부를 점검하여 미생성 되었을 경우는
			// 기존 회원정보의 세션을 생성하여 페이지 이동시에도 정보가 보존되도록 조치할 수 있다
			
			if(session.getAttribute("LEGACY_MEMBER_SESSION") == null) {
			
				MemberDAO dao = MemberDAOImpl.getInstance();
				MemberVO member = dao.getMember(memberId);
				
				System.out.println("member : "+member);
				
				session.setAttribute("LEGACY_MEMBER_SESSION", member);
				
				// request.setAttribute("member", member);
			}
			// returnPath = "/member/member_update.jsp";
			// template 적용시
			returnPath = "/template.do?content_page=/member/member_update.jsp";
		}
		
		return returnPath;
	} //

}