package com.javateam.jdbc.member.test;

import java.sql.Date;

import com.javateam.jdbc.member.domain.MemberVo;

/**
 * VO 단위 테스트
 * 
 * @author javateam
 *
 */
public class VoTest {
	
	public static void main(String[] args) {
		
		MemberVo member = new MemberVo();
		member.setMemberId("java");
		member.setMemberPassword("1234");
		member.setMemberNickname("구디샘");
		member.setMemberName("홍길동");
		member.setMemberGender('m');
		member.setMemberEmail("abcd@abcd.com");
		member.setMemberPhone("010-1234-5678");
		// 생일) 0000-00-00
		member.setMemberBirth(Date.valueOf("2000-05-22"));
		member.setMemberZip("12345");
		member.setMemberAddressBasic("서울 금천구 가산");
		member.setMemberAddressDetail("구디아카데미");
		// 금일 날짜 => 가입일
		member.setMemberJoindate(new Date(System.currentTimeMillis()));
		
		// 전체 내용 확인
		System.out.println(member);

	} //

}
