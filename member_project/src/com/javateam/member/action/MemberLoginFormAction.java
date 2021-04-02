package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;

public class MemberLoginFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("로그인 폼");
		
		// return "/member/member_login.jsp";
		// template 적용시
		return "/template.do?content_page=/member/member_login.jsp"; 
	} //

}
