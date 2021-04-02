package com.javateam.member.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/member/myPage.do")
public class MyPageAuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MyPageAuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		// String contextPath = req.getContextPath();
		
		if (session.getAttribute("LOGIN_SESSION")==null) {
			
			System.out.println("로그인 미인증");
			
			request.setAttribute("msg","먼저 로그인 하십시오");
			request.setAttribute("move_page", "/template.do?=/member/login.jsp");
			
			// template 적용시
			RequestDispatcher rd = req.getRequestDispatcher("/template.do?content_page=/error/result.jsp");
			rd.forward(req, res);	
			return;
			
		} else { // 인증시
			
			// template 적용시
			RequestDispatcher rd = req.getRequestDispatcher("/template.do?content_page=/member/myPage.jsp");
			rd.forward(req, res);
			return;
		} //
		
		//chain.doFilter(request, response);
		// 경우에 따라 위의 구문이 없으면 "페이지가 작동하지 않습니다. localhost에서 리디렉션한 횟수가 너무 많습니다." 에러 유발됨.
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
