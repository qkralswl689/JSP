package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;

public class MemberDeleteProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("회원정보 삭제 처리");
		String msg = "";
		// String movePage = "";
		
		String memberId = request.getParameter("memberId");
		MemberDAO dao = MemberDAOImpl.getInstance();
		
		if (dao.isMember(memberId)==false) {
			
			msg = memberId +" 님은 회원이 아닙니다. 회원가입을 먼저 하십시오.";
			request.setAttribute("err_msg", msg);
			
			// movePage = "/member/join.do";
			// request.setAttribute("msg", msg);
			// request.setAttribute("move_page", "/member/join.do");
			// return "/error/result.jsp";
		} else {
			
			msg = dao.deleteMember(memberId);
			
			// 롤(Role)정보 삭제
			dao.deleteRoleByMemberId(memberId);
			
			request.setAttribute("err_msg", msg);
		} //
		
		// return "/member/member_delete.jsp";
		
		// template 적용시
		return "/template.do?content_page=/member/member_delete.jsp";
	} //

}