package newlecture;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class add
 */
@WebServlet("/calc3")
public class calc3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public calc3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		
		// Session 객체 생성
		HttpSession session = request.getSession();
				
		//쿠키 변수 선언
		Cookie[] cookies = request.getCookies();	
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		// 입력값이 없을경우 대비
		int v = 0;
		
		// 형변환
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		// 계산
		if(op.equals("=")) {
			
				//int x = (Integer)application.getAttribute("value");
				//int x = (Integer)session.getAttribute("value");
				
				// 쿠키 찾기
				int x = 0;
				for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x =Integer.parseInt(c.getValue());
					break;
					}
				}
					
					
				int y = v;
				//String operator = (String)application.getAttribute("op");
				//String operator = (String)session.getAttribute("op");
				
				String operator="";
				for(Cookie c : cookies) {
					if(c.getName().equals("op")) {
						operator =c.getValue();
						break;
						}
					}
				
				
				int result = 0;
						
			if(operator.equals("+")) {
				 result = x + y;
			}else {
				 result = x - y;
			}response.getWriter().printf("result : %d\n" , result);
			
		// 값을 저장
		}else {
		// ServletContext : applicaiont 저장소
		
		//application.setAttribute("value", v);
		//application.setAttribute("op", op);
			
//		session.setAttribute("value", v);
//		session.setAttribute("op", op);
		
		// 쿠키 생성
		// cookie : 문자열만 저장가능
		Cookie valueCookie = new Cookie("value",String.valueOf(v));
		Cookie opCookie = new Cookie("op",op);
		
		// 쿠키가 어떤 경우에 클라이언트로 전달되어야 하는지 설정
		//valueCookie.setPath("/"); // ("/") : 모든페이지를 요청할 때 마다 valueCookie를 가져오라는 설정
		//opCookie.setPath("/");
		valueCookie.setPath("/calc2"); // calc2에서만 쿠키 전달하는 설정
		valueCookie.setMaxAge(24*60*60); // 쿠키 만료시간 설정 ,(24*60*60) : 24시간 => 설정안하면 브라우저 닫히면 쿠키가 삭제된다
		opCookie.setPath("/calc2");
		
		// 쿠키 클라이언트로 보내기
		response.addCookie(valueCookie);
		response.addCookie(opCookie);
		
		// redirection 기능 : 페이지 이동기능 => +,-연산시 빈페이지 전달받지않음
		response.sendRedirect("calc2.html");
		}
	}

}
