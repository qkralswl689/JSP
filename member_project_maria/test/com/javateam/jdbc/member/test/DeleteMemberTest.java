package com.javateam.jdbc.member.test;

import com.javateam.jdbc.member.dao.MemberDao;
import com.javateam.jdbc.member.dao.impl.MemberDaoImpl;

/**
 * deleteMember 단위 테스트 케이스<br><br>
 * : 회원정보 삭제 테스트<br>
 * 
 * @author javateam
 */
public class DeleteMemberTest {

	public static void main(String[] args) {
		
		MemberDao dao = MemberDaoImpl.getInstance();
		dao.deleteMember("java");
		
	}

}
