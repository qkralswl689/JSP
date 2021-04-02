package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;

public class TemplateAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("템플릿");
		
		// 초기 페이지 변경
		String contentPage = request.getParameter("content_page")==null ? 
							 // "../member/member_login.jsp"
							 "../startup.jsp"
						     : request.getParameter("content_page").trim();  
		
		request.setAttribute("content_page", contentPage);
		
		System.out.println("content_page : " + contentPage);
		
		return "/template/template.jsp";
	}

}