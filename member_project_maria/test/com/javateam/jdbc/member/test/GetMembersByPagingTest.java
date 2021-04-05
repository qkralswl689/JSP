package com.javateam.jdbc.member.test;

import java.util.List;

import com.javateam.jdbc.member.dao.MemberDao;
import com.javateam.jdbc.member.dao.impl.MemberDaoImpl;
import com.javateam.jdbc.member.domain.MemberVo;

/**
 * getMembersByPaging 단위 테스트 케이스<br><br>
 * : 페이지 단위로 전체 회원정보 조회 테스트<br><br>
 * 
 * @author javateam
 *
 */
public class GetMembersByPagingTest {

	public static void main(String[] args) {

		MemberDao dao = MemberDaoImpl.getInstance();
		
		// 페이징에 따른 테스트 기대값
		//
		// 1) 특정 페이지 입력시 인원수. 한 페이지당 인원수 10명 제한
		// 2) 처음/마지막 인원 회원 아이디 정보로 점검
		// ex) 1 페이지 첫 아이디 = goodee_1001, 마지막 아이디 = goodee_1010
		
		List<MemberVo> members = dao.getMembersByPaging(1, 10);
		
		// 1) 특정 페이지 입력시 인원수. 한 페이지당 인원수 10명 제한
		System.out.println("특정 페이지 입력시 인원수 : "+members.size());
		
		// 2) 처음/마지막 인원 회원 아이디 정보로 점검
		// ex) 1 페이지 첫 아이디 = goodee_1001, 마지막 아이디 = goodee_1010
		System.out.println("처음 인원 회원 아이디 정보로 점검 : " 
				+ members.get(0).getMemberId().equals("goodee_1001"));
		
		System.out.println("마지막 인원 회원 아이디 정보로 점검 : " 
				+ members.get(9).getMemberId().equals("goodee_1010"));
		
		// 특정 페이지 전체 인원 출력
		for (MemberVo m : members) {
			System.out.println(m);
		}
				
	} //

} //