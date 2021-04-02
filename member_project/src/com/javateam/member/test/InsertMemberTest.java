/**
 * 
 */
package com.javateam.member.test;

import java.sql.Date;

import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;
import com.javateam.member.domain.MemberVO;
import com.javateam.member.domain.RoleVO;

/**
 * DAO InsertMember Unit Test(단위 테스트)
 * 
 * @author javateam
 *
 */
public class InsertMemberTest {

	public static void main(String[] args) throws Exception {
		
		// 1. DAO 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();
		// 2. 회원정보 준비
		MemberVO member = new MemberVO();
		member.setMemberId("java111111");
		member.setMemberPassword("#Abcd1234");
		member.setMemberNickname("오라클샘");
		member.setMemberName("홍길동");
		member.setMemberGender("m");
		member.setMemberEmail("abcd@abcd.com");
		member.setMemberPhone("010-1111-2121");
		member.setMemberBirth(Date.valueOf("2002-01-13"));
		member.setMemberZip("12345");
		member.setMemberAddressBasic("서울 구로구");		
		member.setMemberAddressDetail("OJ");
		
		System.out.println(member);
		
		// 3. 회원정보 삽입
		dao.insertMember(member);
		
		// 4. 롤(Role) 정보 저장
		RoleVO roleVO = new RoleVO();
		roleVO.setMemberId(member.getMemberId());
		roleVO.setMemberRole("guest");
		dao.insertRole(roleVO);
		
	}

}
