package com.javateam.member.test;

import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;

public class LoginTest {

	public static void main(String[] args) throws Exception {

		MemberDAO dao = MemberDAOImpl.getInstance();
		System.out.println(dao.checkLogin("java123456", "1231231412"));
		System.out.println(dao.checkLogin("java111111", "1231231412"));
		System.out.println(dao.checkLogin("java111111", "#Abcd1234"));
				
	}

}
