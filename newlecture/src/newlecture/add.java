package newlecture;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class add
 */
@WebServlet("/add")
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add() {
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
		
		/* 내가 작성한 코드
		 * PrintWriter out = response.getWriter();
		 * 
		 * int x = Integer.parseInt(request.getParameter("x")); int y =
		 * Integer.parseInt(request.getParameter("y"));
		 * 
		 * out.print("덧셈 결과 : " + (x+y));
		 */
		
		String x_ = request.getParameter("x");
		String y_ = request.getParameter("y");
		
		// 입력값이 없을경우 대비
		int x = 0;
		int y = 0;
		// 형변환
		if(!x_.equals("")) x = Integer.parseInt(x_);
		if(!y_.equals("")) y = Integer.parseInt(y_);
		
		int result = x + y;
		
		response.getWriter().printf("result : %d\n" , result);
	
	}

}
