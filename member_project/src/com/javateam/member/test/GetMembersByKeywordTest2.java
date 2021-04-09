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
public class GetMembersByKeywordTest2 {

	public static void main(String[] args) throws Exception {

		MemberDAO dao = MemberDAOImpl.getInstance();
		
		String searchKind = "이름";
		
		String fld = searchKind.contentEquals("아이디") ? "member_id" :
			 searchKind.contentEquals("별명") ? "member_nickname" :
			 searchKind.contentEquals("이름") ? "member_name" :
		     searchKind.contentEquals("기본 주소") ? "member_address" :
	    	 searchKind.contentEquals("상세 주소") ? "member_address" : "member_id";
		
		for (MemberVO m : dao.getMembersByFieldAndPaging(fld, "이순희", false, 1, 10)) {
		// for (MemberVO m : dao.getMembersByField("member_name", "순희", true)) {
		// for (MemberVO m : dao.getMembersByField("member_name", "길동", true)) {
		// for (MemberVO m : dao.getMembersByField("member_email", "oj_3@abcd.com", false)) {
		// for (MemberVO m : dao.getMembersByField("member_email", "naver", true)) {			
		// for (MemberVO m : dao.getMembersByField("member_gender", "m", false)) {
		// for (MemberVO m : dao.getMembersByField("member_gender", "m", false)) {
		// for (MemberVO m : dao.getMembersByField("member_address", "관악", true)) {
			System.out.println(m);
		} // 		
		
	}

}