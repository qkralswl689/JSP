package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javateam.member.controller.CommandAction;

public class MemberLogoutProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("로그아웃 처리");
		HttpSession session = request.getSession();
		
		if (session.getAttribute("LOGIN_SESSION")!=null) {
			// 세션 종료
			session.invalidate();
			
			// 방법-1 : 바로 앞 페이지(로그인 페이지)로 이동
			request.setAttribute("err_msg", "로그아웃 되었습니다");
			
			// 방법-2 : /error/result.jsp로 이동
			// request.setAttribute("msg", "로그아웃 되었습니다");
			// request.setAttribute("move_page", "/member/login.do");
			// return "/error/result.jsp";
		}
		
		// return "/member/login.do";
		
		// 템플릿 적용시
		return "/template.do?content_page=/member/member_login.jsp";
	}

}
