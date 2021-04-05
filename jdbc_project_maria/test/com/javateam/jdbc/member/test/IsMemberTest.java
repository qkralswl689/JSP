package com.javateam.jdbc.member.test;

import com.javateam.jdbc.member.dao.MemberDao;
import com.javateam.jdbc.member.dao.impl.MemberDaoImpl;

/**
 * isMember 단위 테스트 케이스<br>
 * : 회원 여부 점검 테스트<br><br>
 * 
 * @author javateam
 */
public class IsMemberTest {

	public static void main(String[] args) {
		
		MemberDao dao = MemberDaoImpl.getInstance();

		// 존재하는 아이디와 존재하지 않는 아이디 두가지 경우를 테스트
		// 1) 존재하는 아이디로 점검
		System.out.println("회원 아이디 존재 여부(java) : " + dao.isMember("java"));
		// 2) 존재하지 않는 아이디로 점검
		System.out.println("회원 아이디 존재여부(java2) : " 
						+ dao.isMember("java2"));
	}

}
