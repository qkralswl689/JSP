package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;

public class MemberLogoutAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("로그 아웃 페이지");
		return "/member/logout.jsp";
	}

}
