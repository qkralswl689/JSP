package newlecture;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hi")
public class Nana extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		// 입력값이 입력 됐을 경우
		// cnt : 입력값 -> 무조건 문자열로 전달된다  => Integer로 형변환
//		int cnt = Integer.parseInt(req.getParameter("cnt"));
//		
//		for(int i=0; i<cnt; i++) {
//			out.println((i+1) + ": 안녕 Servlet!!<br >");
		
		// 입력값이 입력 안 됐을 경우
		
		String cnt_ = req.getParameter("cnt"); // 임시변수 설정
		
		int cnt = 10; // 기본값 설정
		if(cnt_ != null && !cnt_.equals("")) { // cnt_의 값이  null이 아니거나 빈문자열이 아닐경우
			cnt = Integer.parseInt(cnt_); // cnt_의 값을 cnt(기본값)으로 저장한다
		}
		
		for(int i=0; i<cnt; i++) {
			out.println((i+1) + ": 안녕 Servlet!!<br >");
		}
	
	}
}

