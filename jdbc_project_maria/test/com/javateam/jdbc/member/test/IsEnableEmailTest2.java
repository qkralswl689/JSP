package com.javateam.jdbc.member.test;

import com.javateam.jdbc.member.dao.MemberDao;
import com.javateam.jdbc.member.dao.impl.MemberDaoImpl;

/**
 * isEnableEmail(id, email) 메서드 단위 테스트 케이스(Unit TestCase)<br><br>
 * : 이메일 중복 점검 테스트<br><br>
 * 
 * @author javateam
 *
 */
public class IsEnableEmailTest2 {

	public static void main(String[] args) {

		MemberDao dao = MemberDaoImpl.getInstance();
		
		// 중복 이메일 점검 기대값 점검
		// 
		// 1) 회원 아이디/DB 현황에 없는 이메일 입력 : true
 		//    ex) javacan/abcdabcd@abcd.com
		// 2) 회원 아이디/DB 현황에 있는 기존 자신의 이메일(중복 허용 이메일) : true
		//    ex) javacan/abcd1@abcd.com
		// 3) 회원 아이디/DB 현황에 있는 다른 회원의 이메일(중복 이메일) : false
		//	  ex) javacan/abcd2@abcd.com
		
		System.out.println("회원 아이디/DB 현황에 없는 이메일 입력 : " 
					+ dao.isEnableEmail("goodee_1001", "abcdabcd@abcd.com"));
		
		System.out.println("회원 아이디/DB 현황에 있는 기존 자신의 이메일(중복 허용 이메일) : "
					+ dao.isEnableEmail("goodee_1001", "goodee_1@abcd.com"));		
		
		System.out.println("회원 아이디/DB 현황에 있는 다른 회원의 이메일(중복 이메일) : "
					+ dao.isEnableEmail("goodee_1001", "goodee_2@abcd.com"));
		
	}

}
