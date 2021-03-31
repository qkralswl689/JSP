package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class DemoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("##################");
		System.out.println("demo.bo");
		
		// TODO
		// Svc call
		// 인자 생성 : request.setAttribute("인자", 인자값);
		
		request.setAttribute("java", 16);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/board/demo.jsp");
		
		return forward;
	}

}
