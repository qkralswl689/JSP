package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;

public class MemberLoginProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("로그인 처리");
		
		String msg = ""; // 에러 메시지
		// String movePage = "/member/member_login.jsp"; // 이동 페이지
		
		String movePage = "/template.do?content_page=/member/member_login.jsp"; // 템플릿 적용시 이동 페이지
		
		HttpSession session = request.getSession(); // 세션 객체 생성
		String memberId = request.getParameter("memberId");
		String memberPassword = request.getParameter("memberPassword");
		
		System.out.println("아이디 : "+memberId);
		System.out.println("패쓰워드 : "+memberPassword);
		
		MemberDAO dao = MemberDAOImpl.getInstance();
		
		// 회원 여부 점검
		if (dao.isMember(memberId)==false) {
			msg = "회원이 아닙니다. 먼저 회원가입을 하십시오.";
		} else { // 회원일 경우
			
			msg = dao.checkLogin(memberId, memberPassword);
			
			System.out.println("메시지 : "+msg);
			
			if (msg.contains("님이 로그인 하셨습니다")==true) {
				
				// 세션 생성
				if (session.getAttribute("LOGIN_SESSION")==null) {
					session.setAttribute("LOGIN_SESSION", memberId);
				} else {
					msg = "이미 로그인 되었습니다.";
				} //
				
				// 로그인 페이지로 이동
				// movePage = "/member/member_logout.jsp";
				movePage = "/template.do?content_page=/member/member_logout.jsp"; // 템플릿 적용시 이동 페이지
			} //
			
		} // 회원 여부 점검
		
		System.out.println("movePage : "+movePage);
		
		request.setAttribute("err_msg", msg);
		
		return movePage;
	} //

}
