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

import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/member/viewAll.do")
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
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
		String contextPath = req.getContextPath();
		boolean adminYn = false;
		// 세션 생성여부 점검
		if (session.getAttribute("LOGIN_SESSION")==null) {
			
			System.out.println("로그인 미인증");
			
			request.setAttribute("msg","로그인 미인증");
			request.setAttribute("move_page", "/template.do?=/member/login.jsp");
			
			// template 적용시
			RequestDispatcher rd = req.getRequestDispatcher("/template.do?content_page=/error/result.jsp");
			rd.forward(req, res);	
			return;
			
		} else {
			
			// role 점검
			MemberDAO dao = MemberDAOImpl.getInstance();
			String memberId = (String)session.getAttribute("LOGIN_SESSION");
			String role = "";
			
			try {
				role = dao.getRoleByMemberId(memberId); // 사용자 롤(role)정보 확보
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (role.contentEquals("admin")) { // admin 권한 점검
				
				adminYn = true; 
				// res.sendRedirect(contextPath+"/member/viewAll.do");
				// java.lang.IllegalStateException : 응답이 이미 커밋된 후에는, sendRedirect()를 호출할 수 없습니다. 에러 패치
				// return; 
				
			} else {
				
				System.out.println("권한 없음");
				
				request.setAttribute("msg","권한 없음");
				request.setAttribute("move_page", "/index.do");
				
				// template 적용시
				RequestDispatcher rd = req.getRequestDispatcher("/template.do?content_page=/error/result.jsp");
				rd.forward(req, res);	
				return;
			} //
			
			chain.doFilter(request, response); 
			// 경우에 따라 위의 구문이 없으면 "페이지가 작동하지 않습니다. localhost에서 리디렉션한 횟수가 너무 많습니다." 에러 유발됨.
			
			if (adminYn==true) {
				
				// java.lang.IllegalStateException : 응답이 이미 커밋된 후에는, sendRedirect()를 호출할 수 없습니다. 에러 패치
				try {
					res.sendRedirect(contextPath+"/member/viewAll.do");
				} catch (IllegalStateException e) {
					return;
				}
				
			} else {
				
				try {
					res.sendRedirect(contextPath+"/member/login.do");
				} catch (IllegalStateException e) {
					return; 
				}
			} //
						
		} //
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
