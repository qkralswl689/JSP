package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;

public class MemberDeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("회원정보 삭제");
		
		// return "/member/member_delete.jsp";
		// template 적용시
		return "/template.do?content_page=/member/member_delete.jsp";
	}

}