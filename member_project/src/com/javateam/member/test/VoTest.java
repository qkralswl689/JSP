package com.javateam.member.test;

import com.javateam.member.domain.MemberVO;

/**
 * 회원정보 객체 단위 테스트
 * 
 * @author javateam
 *
 */
public class VoTest {

	public static void main(String[] args) {

		MemberVO member = new MemberVO();
		member.setMemberId("javajava");
		member.setMemberAddressBasic("구로디지털단지");
		
		MemberVO member2 = new MemberVO();
		member2.setMemberId("javajava");
		member2.setMemberAddressBasic("구로디지털단지");
		
		System.out.println(member);
		
		System.out.println(member.equals(member2));
	}

}
