package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;

public class MyPageAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("My Page");
		
		return "/template.do?content_page=/member/myPage.jsp";
	} //

}
