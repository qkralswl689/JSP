package com.javateam.jdbc.member.test;

import java.sql.Date;

import com.javateam.jdbc.member.dao.MemberDao;
import com.javateam.jdbc.member.dao.impl.MemberDaoImpl;
import com.javateam.jdbc.member.domain.MemberVo;

/**
 * insertMember 메서드 단위 테스트 케이스(Unit TestCase)<br>
 * : 회원정보 생성 테스트<br><br>
 * 
 * @author javateam
 */
public class InsertMemberTest {

	public static void main(String[] args) {

		// DAO 객체 생성
		MemberDao dao = MemberDaoImpl.getInstance();
		
		// 생성할 회원정보 생성(MemberVo)
		MemberVo member = new MemberVo();
		member.setMemberId("java");
		member.setMemberPassword("1234");
		member.setMemberNickname("구디샘");
		member.setMemberName("홍길동");
		member.setMemberGender('m');
		member.setMemberEmail("abcd@abcd.com");
		member.setMemberPhone("010-1234-5678");
		member.setMemberBirth(Date.valueOf("2016-02-05"));
		member.setMemberZip("12345");
		member.setMemberAddressBasic("서울 금천 가산");
		member.setMemberAddressDetail("구디아카데미");
		
		// InsertMember 테스트(true/false)
		String result = dao.insertMember(member) == true ? 
				         "저장 성공" : "저장 실패";
		System.out.println("insertMember 테스트 결과 : " + result);
	} //

} //