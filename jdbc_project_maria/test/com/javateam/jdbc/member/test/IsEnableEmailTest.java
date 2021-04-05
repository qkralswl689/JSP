package com.javateam.jdbc.member.test;

import com.javateam.jdbc.member.dao.MemberDao;
import com.javateam.jdbc.member.dao.impl.MemberDaoImpl;

/**
 * isEnableEmail 메서드 단위 테스트 케이스(Unit TestCase)<br><br>
 * : 이메일 중복 점검 테스트<br><br>
 * 
 * @author javateam
 *
 */
public class IsEnableEmailTest {

	public static void main(String[] args) {

		MemberDao dao = MemberDaoImpl.getInstance();
		
		// 중복 이메일 점검 기대값 점검
		// 
		// 1) DB 현황에 없는 이메일 입력 : true
 		//    ex) abcdabcd@abcd.com
		// 2) DB 현황에 있는 이메일(중복 이메일) : false
		//    ex) abcd1@abcd.com
		
		System.out.println("DB 현황에 없는 이메일 입력 : " 
					+ dao.isEnableEmail("abcdabcd@abcd.com"));
		
		System.out.println("DB 현황에 있는 이메일(중복 이메일) : "
					+ dao.isEnableEmail("goodee_1@abcd.com"));		
		
	}

}
