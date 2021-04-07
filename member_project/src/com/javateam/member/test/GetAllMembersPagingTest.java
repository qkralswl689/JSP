/**
 * 
 */
package com.javateam.member.test;

import com.javateam.member.dao.MemberDAO;
import com.javateam.member.dao.MemberDAOImpl;
import com.javateam.member.domain.MemberVO;

/**
 * DAO getMember Unit Test
 * 
 * @author javateam
 */
public class GetAllMembersPagingTest {

	public static void main(String[] args) throws Exception {

		MemberDAO dao = MemberDAOImpl.getInstance();
		
		for (MemberVO m : dao.getMembersByPaging(1, 10)) {
			System.out.println(m);
		} // 		
		
	}

}
