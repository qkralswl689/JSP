package newlecture;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
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
		//쿠키 배열변수 선언
		Cookie[] cookies = request.getCookies();	
		
		// 사용자가 입력한 값을 가지고
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		// 쿠키를 만들어 exp로 전달한다
		String exp ="";
		// 쿠키 읽어오는연산식
		if(cookies !=null) { // 쿠키가 null이 아닐경우
			for(Cookie c : cookies) { // 연산식 실행
				if(c.getName().equals("exp")) {
					exp =c.getValue();
					break;
					}
				}
		}
		if(operator !=null && operator.equals("=")) {
			
			// 계산
			// javaScript 구문 실행 객체 생성
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(operator !=null && operator.equals("C")) { // 쿠키 삭제 : C버튼클릭 시 초기화
			exp = "";
		}
		else {
			// 입력값 누적
			exp += (value == null) ? "" : value;
			exp += (operator == null) ? "" : operator;
			exp += (dot == null) ? "" : dot;
		}

		
		
		// 쿠키생성
		Cookie expCookie = new Cookie("exp",exp);
		if(operator !=null && operator.equals("C")) { // 조건에 해당 될 때만
			expCookie.setMaxAge(0); // 쿠키삭제(브라우저에서)
		}
		
		//expCookie.setPath("/");
		
		// 쿠키로 저장
		response.addCookie(expCookie);
		
		// redirection 기능 : 페이지 이동기능 => +,-연산시 빈페이지 전달받지않음
		response.sendRedirect("calcpage");
		}
	}


