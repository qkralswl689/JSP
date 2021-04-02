/**
 * Demo
 */
package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;

/**
 * Demo 
 * @author javateam
 *
 */
public class DemoAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable {

		System.out.println("demo");
		
		request.setAttribute("java", "한글");
		
		return "result.jsp";
	}

}
