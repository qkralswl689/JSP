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
public class GetMemberTest {

	public static void main(String[] args) throws Exception {

		MemberDAO dao = MemberDAOImpl.getInstance();
		System.out.println(dao.getMember("java111111"));
	}

}
