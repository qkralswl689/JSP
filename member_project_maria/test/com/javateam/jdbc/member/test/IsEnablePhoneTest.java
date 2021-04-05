package com.javateam.jdbc.member.test;

import com.javateam.jdbc.member.dao.MemberDao;
import com.javateam.jdbc.member.dao.impl.MemberDaoImpl;

/**
 * isEnablePhone 메서드 단위 테스트 케이스(Unit TestCase)<br><br>
 * : 연락처(전화번호) 중복 점검 테스트<br><br>
 * 
 * @author javateam
 *
 */
public class IsEnablePhoneTest {

	public static void main(String[] args) {

		MemberDao dao = MemberDaoImpl.getInstance();
		
		// 중복 이메일 점검 기대값 점검
		// 
		// 1) DB 현황에 없는 연락처(전화번호) 입력 : true
 		//    ex) 010-7878-8989
		// 2) DB 현황에 있는 연락처(중복 전화번호) : false
		//    ex) 010-1234-1001
		
		System.out.println("DB 현황에 없는 연락처(전화번호) 입력 : " 
					+ dao.isEnablePhone("010-7878-8989"));
		
		System.out.println("DB 현황에 있는 연락처(중복 전화번호) : "
					+ dao.isEnablePhone("010-1234-1001"));		
		
	} //

}