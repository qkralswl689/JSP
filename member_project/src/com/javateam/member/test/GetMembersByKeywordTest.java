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
public class GetMembersByKeywordTest {

	public static void main(String[] args) throws Exception {

		MemberDAO dao = MemberDAOImpl.getInstance();
		
		// for (MemberVO m : dao.getMembersByField("member_name", "홍길동", false)) {
		// for (MemberVO m : dao.getMembersByField("member_name", "길동", true)) {
		// for (MemberVO m : dao.getMembersByField("member_email", "oj_3@abcd.com", false)) {
		// for (MemberVO m : dao.getMembersByField("member_email", "naver", true)) {			
		// for (MemberVO m : dao.getMembersByField("member_gender", "m", false)) {
		// for (MemberVO m : dao.getMembersByField("member_gender", "m", false)) {
		for (MemberVO m : dao.getMembersByField("member_address", "관악", true)) {
			System.out.println(m);
		} // 		
		
	}

}