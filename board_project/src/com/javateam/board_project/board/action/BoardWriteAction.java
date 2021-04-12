package com.javateam.board_project.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.board_project.controller.CommandAction;

public class BoardWriteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("게시글 쓰기");
		
		return "/board/write_board_form.jsp";
	} //

}
