package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;

public class MemberJoinFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("회원가입폼");
		
		return "/member/member_join.jsp";
	}

}
