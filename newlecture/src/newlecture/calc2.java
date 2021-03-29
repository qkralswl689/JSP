package newlecture;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class add
 */
@WebServlet("/calc2")
public class calc2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public calc2() {
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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		// 입력값이 없을경우 대비
		int v = 0;
		
		// 형변환
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		if(op.equals("=")) {
			
			int result = 0;
			
			if(op.equals("덧셈")) {
				 result = x + y;
			}else {
				 result = x - y;
			}
			
		}else {
		// ServletContext : applicaiont 저장소
		ServletContext application = request.getServletContext();
		
		application.setAttribute("value", v);
		application.setAttribute("op", op);
		
		
		response.getWriter().printf("result : %d\n" , result);
	
	}

}
