package com.javateam.member.test;

import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;

public class DAOTest {

	public static void main(String[] args) throws Exception {

		MemberDAO dao = MemberDAOImpl.getInstance();
		// dao.getAllMembers(); // CRUD 메서드 호출
		
	}

}
