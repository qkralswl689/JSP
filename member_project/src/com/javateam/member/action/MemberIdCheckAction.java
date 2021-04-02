package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;

public class MemberIdCheckAction implements CommandAction {
	
	// DAO
	MemberDAO dao = MemberDAOImpl.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("아이디 중복 점검");
		
		String memberId = request.getParameter("memberId");
		
		String msg = (dao.isMember(memberId.trim()) == true) ? 
					"아이디가 이미 존재합니다." : "사용할 수 있는 아이디입니다.";
		
		request.setAttribute("result", msg);
		
		return "/member/check_result.jsp";
	}

}
