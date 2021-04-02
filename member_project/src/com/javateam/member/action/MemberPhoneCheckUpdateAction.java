package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;

public class MemberPhoneCheckUpdateAction implements CommandAction {
	
	// DAO
	MemberDAO dao = MemberDAOImpl.getInstance();

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("연락처 중복 점검");
		
		String memberId = request.getParameter("memberId");
		String memberPhone = request.getParameter("memberPhone");
		
		String msg = (dao.isEnablePhone(memberId, memberPhone) == false) ? 
					"중복 연락처가 이미 존재합니다." : "사용할 수 있는 연락처입니다.";
		
		request.setAttribute("result", msg);
		
		return "/member/check_result.jsp";  // 메시지 처리는 공유하도록 합니다.
	}

}
