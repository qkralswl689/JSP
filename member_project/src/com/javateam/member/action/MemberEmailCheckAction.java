package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;

public class MemberEmailCheckAction implements CommandAction {
	
	// DAO
	MemberDAO dao = MemberDAOImpl.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("이메일 중복 점검");
		
		String memberEmail = request.getParameter("memberEmail");
		
		String msg = (dao.isEnableEmail(memberEmail) == false) ? 
					"중복 이메일이 이미 존재합니다." : "사용할 수 있는 이메일입니다.";
		
		request.setAttribute("result", msg);
		
		return "/member/check_result.jsp";  // 메시지 처리는 공유하도록 합니다.
	}

}
