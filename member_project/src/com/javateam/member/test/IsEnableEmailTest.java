/**
 * 
 */
package com.javateam.member.test;

import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;

/**
 * DAO getMember Unit Test
 * 
 * @author javateam
 */
public class IsEnableEmailTest {

	public static void main(String[] args) throws Exception {

		MemberDAO dao = MemberDAOImpl.getInstance();
		System.out.println(dao.isEnableEmail("java111111", "abcd1111@abcd.com"));
	}

}