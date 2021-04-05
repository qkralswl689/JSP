package com.javateam.jdbc.member.test;

import java.sql.Date;

import com.javateam.jdbc.member.dao.MemberDao;
import com.javateam.jdbc.member.dao.impl.MemberDaoImpl;
import com.javateam.jdbc.member.domain.MemberVo;

/**
 * UpdateMember 메서드 단위 테스트 케이스(Unit TestCase)<br>
 * : 회원정보 수정 테스트<br><br>
 * 
 * @author javateam
 */
public class UpdateMemberTest {

	public static void main(String[] args) {

		// DAO 객체 생성
		MemberDao dao = MemberDaoImpl.getInstance();
		
		// 생성할 회원정보 생성(MemberVo)
		MemberVo member = new MemberVo();
		
		member.setMemberPassword("5555");
		member.setMemberNickname("달팽이");
		member.setMemberZip("9999");
		member.setMemberId("java");
		
		// UpdateMember 테스트(true/false)
		String result = dao.updateMember(member) == true ? 
				"수정 성공" : "수정 실패";
		System.out.println("updateMember 테스트 결과 : " + result);
	} //

} //