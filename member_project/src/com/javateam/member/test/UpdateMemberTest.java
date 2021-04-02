/**
 * 
 */
package com.javateam.member.test;

import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;
import com.javateam.member.domain.MemberVO;

/**
 * DAO UpdateMember Unit Test(단위 테스트)
 * 
 * @author javateam
 *
 */
public class UpdateMemberTest {

	public static void main(String[] args) throws Exception {
		
		// 1. DAO 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();
		
		// 2. 회원정보 준비
		MemberVO member = new MemberVO();
		member.setMemberId("java111111");
		member.setMemberPassword("#Abcd1234");
		member.setMemberNickname("오라클자바샘");
		member.setMemberName("임꺽정");
		member.setMemberEmail("oracleajva@abcd.com");
		member.setMemberPhone("010-3333-7979");
		member.setMemberZip("12345");
		member.setMemberAddressBasic("서울 구로구 코오롱빌란트");		
		member.setMemberAddressDetail("오라클자바");
		
		System.out.println(member);
		
		// 3. 회원정보 수정
		dao.updateMember(member);
	}

}