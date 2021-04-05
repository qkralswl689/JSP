package com.javateam.jdbc.member.test;

import com.javateam.jdbc.member.dao.MemberDao;
import com.javateam.jdbc.member.dao.impl.MemberDaoImpl;

/**
 * isEnablePhone(id, phone) 메서드 단위 테스트 케이스(Unit TestCase)<br><br>
 * : 연락처(전화번호) 중복 점검 테스트<br><br>
 * 
 * @author javateam
 *
 */
public class IsEnablePhoneTest2 {

	public static void main(String[] args) {

		MemberDao dao = MemberDaoImpl.getInstance();
		
		// 중복 연락처(전화번호) 점검 기대값 점검
		// 
		// 1) 회원 아이디/DB 현황에 없는 연락처(전화번호) 입력 : true
 		//    ex) goodee_1001/010-4321-9898
		// 2) 회원 아이디/DB 현황에 있는 기존 자신의 연락처(전화번호)(중복 허용 연락처(전화번호)) : true
		//    ex) goodee_1001/010-1234-1001
		// 3) 회원 아이디/DB 현황에 있는 다른 회원의 연락처(전화번호)(중복 연락처(전화번호)) : false
		//	  ex) goodee_1001/010-1234-1002
		
		System.out.println("회원 아이디/DB 현황에 없는 연락처(전화번호) 입력 : " 
					+ dao.isEnablePhone("goodee_1001", "010-4321-9898"));
		
		System.out.println("회원 아이디/DB 현황에 있는 기존 자신의 연락처(전화번호)(중복 허용 연락처(전화번호)) : "
					+ dao.isEnablePhone("goodee_1001", "010-1234-1001"));		
		
		System.out.println("회원 아이디/DB 현황에 있는 다른 회원의 연락처(전화번호)(중복 연락처(전화번호)) : "
					+ dao.isEnablePhone("goodee_1001", "010-1234-1002"));
		
	}

}
