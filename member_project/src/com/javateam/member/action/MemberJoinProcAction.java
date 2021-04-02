package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;
import com.javateam.member.domain.MemberVO;
import com.javateam.member.domain.RoleVO;

public class MemberJoinProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("회원가입 처리");
		
		MemberDAO dao = MemberDAOImpl.getInstance();
		String msg = "";
		
		// 인자 인쇄
		request.getParameterMap().forEach((k,v)->System.out.println(k+"="+v[0]));
		
		System.out.println("############################################");
		
		// TODO
		// 1) DAO 객체 생성(싱글턴 객체)
		// 2) 인자들 -> VO (MemberVO)
		// 3) DAO 메서드(insertMember)
		// 4) 성공/실패 -> 메시징 -> 페이지 이동 ex) error 폴더 생성 success.jsp / fail.jsp
		// request.setAttribute()
		try {
			 System.out.println("VO : " + new MemberVO(request.getParameterMap()));
			 // msg = dao.insertMember(new MemberVO(request.getParameterMap()));
			 
			 // Role 저장을 위해 코드 분리
			 MemberVO member = new MemberVO(request.getParameterMap());
			 msg = dao.insertMember(member);
			 
			// 롤(Role)정보 삽입 : 기본롤(role) = "guest"
			RoleVO roleVO = new RoleVO();
			roleVO.setMemberId(member.getMemberId());
			roleVO.setMemberRole("guest");
			dao.insertRole(roleVO);
			
		} catch (Exception e) {
			 e.printStackTrace();
			 msg = "회원 가입에 실패하였습니다."; 
		}
		
		request.setAttribute("msg", msg);
		
		// return "/error/result.jsp";
		
		// template 적용시
		request.setAttribute("msg", msg);
		request.setAttribute("move_page", "/template.do?content_page=/member/member_join.jsp");
		
		return "/template.do?content_page=/error/result.jsp";
	} //

}