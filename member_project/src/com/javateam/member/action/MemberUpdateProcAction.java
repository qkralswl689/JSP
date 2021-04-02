package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;
import com.javateam.member.domain.MemberVO;
import com.javateam.member.domain.RoleVO;

public class MemberUpdateProcAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("회원수정 처리");
		String msg = "";
		
		// Role(롤) 인자 : 관리자 송신 : 추가
		String memberRole = request.getParameter("memberRole")==null ? "없음" :
							request.getParameter("memberRole").trim();
		
		// 인자 점검
		MemberVO member = new MemberVO(request.getParameterMap());
		
		// 기존 데이터와 비교/선택적 수정(저장)
		// 기존 정보
		MemberDAO dao = MemberDAOImpl.getInstance();
		MemberVO defaultMember = dao.getMember(request.getParameter("memberId"));
		System.out.println("기존 회원 정보 : "+defaultMember);
		
		// 신규 패쓰워드는 신규 VO에 대입 : 만약 비어 있다면 기존 패쓰워드로 대입
		String newPassword = (request.getParameter("memberPassword1")==null || 
							  request.getParameter("memberPassword1").trim().equals("")) ?
				            defaultMember.getMemberPassword() 
					       : request.getParameter("memberPassword1").trim();
		
	    System.out.println("newPassword : " + newPassword);
						     
		member.setMemberPassword(newPassword);
		// 가입일은 입력이 되지 않으므로 동등 비교 위해 기존 정보에 대입
		member.setMemberJoinDate(defaultMember.getMemberJoinDate());
		
		// 신규 회원 정보
		System.out.println("신규 회원 정보 : "+member);
		System.out.println("동등 비교 : "+member.equals(defaultMember));
		
		// 회원 정보에 변화있을 경우에만 update
		if (member.equals(defaultMember)==false) {
			System.out.println("회원정보 변경");
			msg = dao.updateMember(member);
			
			// 롤(Role)정보 변경 : 추후 관리자 권한을 점검하여 변경 부분 삽입 가능 
			// dao.updateRole(roleVO);
			// 관리자 여부 점검
			if (dao.getRoleByMemberId(member.getMemberId()).contentEquals("admin") &&
				!memberRole.contentEquals("없음")) 
			{
				RoleVO roleVO = new RoleVO();
				roleVO.setMemberId(member.getMemberId());
				roleVO.setMemberRole(memberRole);
				dao.updateRole(roleVO);
			}
			
		} else {
			msg = "입력하신 정보는 기존 회원정보와 동일합니다.";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("move_page", "/member/update.do?memberId="+member.getMemberId());
		// String returnPath = "/error/result.jsp";
		
		// template 적용시
		String returnPath = "/template.do?content_page=/error/result.jsp";
		
		// 추후에 이동 경로 변경 !
		return returnPath;
	} //

}