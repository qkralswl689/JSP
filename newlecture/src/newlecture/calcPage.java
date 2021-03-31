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
@WebServlet("/calcPage")
public class calcPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public calcPage() {
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
		
		<!DOCTYPE html>
		<html>
		<head>
		<meta charset="UTF-8">
		<title>calc</title>
		<style>
		input{
			width : 50px;
			height : 50px;
		}
		.output{
			height : 50px;
			background : #e9e9e9;
			font-size:24px;
			font-weight : bold;
			text-align : right;
			padding : 0px, 5px;
		}
		</style>
		</head>
		<body>
			<form action="calc3" method="post">
				<table>
					<tr>
						<td class="output"colspan="4">0</td>
					</tr>
					<tr>
						<td><input type="submit" name="operator" value="CE" /></td>
						<td><input type="submit" name="operator" value="C" /></td>
						<td><input type="submit" name="operator" value="BS" /></td>
						<td><input type="submit" name="operator" value="¡À" /></td>
					</tr>
					<tr>
						<td><input type="submit" name="value" value="7" /></td>
						<td><input type="submit" name="value" value="8" /></td>
						<td><input type="submit" name="value" value="9" /></td>
						<td><input type="submit" name="operator" value="*" /></td>
					</tr>
					<tr>
						<td><input type="submit" name="value" value="4" /></td>
						<td><input type="submit" name="value" value="5" /></td>
						<td><input type="submit" name="value" value="6" /></td>
						<td><input type="submit" name="operator" value="-" /></td>
					</tr>
					<tr>
						<td><input type="submit" name="value" value="1" /></td>
						<td><input type="submit" name="value" value="2" /></td>
						<td><input type="submit" name="value" value="3" /></td>
						<td><input type="submit" name="operator" value="+" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" name="value" value="0" /></td>
						<td><input type="submit" name="dot" value="." /></td>
						<td><input type="submit" name="operator" value="=" /></td>
					</tr>
				</table>
				
			
			</form>
		</body>
		</html>
		
	}

}
