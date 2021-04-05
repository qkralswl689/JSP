package newlecture;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class calculator extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//쿠키 배열변수 선언
		Cookie[] cookies = request.getCookies();	
				
		// 쿠키 읽어오는 연산식
		String exp="0";	
		if(cookies !=null) { // 쿠키가 null이 아닐경우
			for(Cookie c : cookies) { // 연산식 실행
				if(c.getName().equals("exp")) {
					exp =c.getValue();
					break;
					}
				}
		}
		

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 동적으로 만들었다
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\"> ");
		out.write("<title>calc</title>");
		out.write("<style>");
		out.write("input{");
		out.write("	width : 50px;");
		out.write("	height : 50px;");
		out.write("}");
		out.write(".output{");
		out.write("height : 50px;");
		out.write("background : #e9e9e9;");
		out.write("font-size:24px;");
		out.write("font-weight : bold;");
		out.write("text-align : right;");
		out.write("padding : 0px, 5px;");
		out.write("}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("<form action=\"calc3\" method=\"post\">");
		out.write("<table>");
		out.write("<tr>");
		out.printf(		"<td class=\"output\" colspan=\"4\">%s</td>",exp); // exp 출력
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"CE\" /></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"C\" /></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"BS\" /></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"/\" /></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"7\" /></td>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"8\" /></td>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"9\" /></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"*\" /></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"4\" /></td>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"5\" /></td>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"6\" /></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"-\" /></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"1\" /></td>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"2\" /></td>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"3\" /></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"+\" /></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td></td>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"0\" /></td>");
		out.write("<td><input type=\"submit\" name=\"dot\" value=\".\" /></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"=\" /></td>");
		out.write("</tr>");
		out.write("</table>");
		out.write("</form>");
		out.write("</body>");
		out.write("</html>");
	}
	
	@Override
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
		
		expCookie.setPath("/calculator");
		
		// 쿠키로 저장
		response.addCookie(expCookie);
		
		// redirection 기능 : 페이지 이동기능 => +,-연산시 빈페이지 전달받지않음
		response.sendRedirect("calculator");
	}
}
