package com.javateam.jdbc.member.test;

import java.sql.Date;

import com.javateam.jdbc.member.dao.MemberDao;
import com.javateam.jdbc.member.dao.impl.MemberDaoImpl;
import com.javateam.jdbc.member.domain.MemberVo;

/**
 * getMember 메서드 단위 테스트 케이스<br>
 * : 개별 회원정보 조회<br><br>
 * 
 * @author javateam
 */
public class MemberTest {

	public static void main(String[] args) {

		MemberDao dao = MemberDaoImpl.getInstance();
		
		// DB 입력된(저장된) 값들과 VO 값을 비교
		// 비교 대상 VO 준비
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
		// 날짜 입력
		member.setMemberJoindate(Date.valueOf("2021-04-02"));
		
		System.out.println(member);
		System.out.println(dao.getMember("java"));
		
		String result = member.equals(dao.getMember("java")) ?
				"회원정보 조회에 성공하였습니다." : "회원정보 조회에 실패하였습니다.";
		System.out.println(result);
	}

}
