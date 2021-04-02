/**
 * 
 */
package com.javateam.member.test;

import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;

/**
 * DAO InsertMember Unit Test(단위 테스트)
 * 
 * @author javateam
 *
 */
public class DeleteMemberTest {

	/**
	 * @param args 인자
	 * @throws Exception 예외처리 
	 */
	public static void main(String[] args) throws Exception {
		
		// 1. DAO 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();
		// 2. 회원정보(아이디) 준비
		
		// 3. 회원정보 삭제
		dao.deleteMember("java111111");
		
	}

}
