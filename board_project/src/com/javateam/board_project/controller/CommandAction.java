package com.javateam.board_project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandAction {
	
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable;
	
}
