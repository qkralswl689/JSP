package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;
import com.javateam.member.domain.RoleVO;

public class MemberUpdateRoleAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("회원 등급(Role) 정보 변경");
		
		String page = request.getParameter("page").trim();
		String memberId = request.getParameter("memberId").trim();
		String memberRole = request.getParameter("memberRole").trim();
		
		MemberDAO dao = MemberDAOImpl.getInstance();
		RoleVO roleVO = new RoleVO();
		roleVO.setMemberId(memberId);
		roleVO.setMemberRole(memberRole);
		dao.updateRole(roleVO);
		
		request.setAttribute("msg", "회원 등급정보가 변경되었습니다.");
		request.setAttribute("move_page", "/member/viewRoles.do?page="+page);
		
		// template 적용시
		return "/template.do?content_page=/error/result.jsp";
	} //

}
