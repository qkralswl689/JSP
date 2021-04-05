package com.javateam.jdbc.member.test;

import java.util.ArrayList;
import java.util.List;

import com.javateam.jdbc.member.dao.MemberDao;
import com.javateam.jdbc.member.dao.impl.MemberDaoImpl;
import com.javateam.jdbc.member.domain.MemberVo;

/**
 * getAllMembers 메서드 단위 테스트 케이스(Unit TestCase)<br>
 * : 전체 회원정보 조회 테스트<br><br>
 * 
 * @author javateam
 */
public class AllMembersTest {
	
	public static int getCountByWord(List<String> list, String id) {
		
		int count = 0;

		for (int i=0; i<list.size(); i++) {
			
			if (list.get(i).contentEquals(id)) {
				count++;
			}
		}
		
		return count;
	}

	public static void main(String[] args) {

		MemberDao dao = MemberDaoImpl.getInstance();
		
		System.out.println("-------------------");
		
		// 인원수 확인
		System.out.println("인원수 확인 : " 
					+ (dao.getAllMembers().size() == 4 ? true : false));
		
		// 테스트 케이스 점검 사항
		// 중복행 출력 여부 점검
		// 각 행에 대한 회원 아이디를 중복성 점검
		List<String> ids = new ArrayList<>();
		
		// 전체 현황 확인
		for (MemberVo m : dao.getAllMembers()) {
			System.out.println(m.getMemberId());
			ids.add(m.getMemberId());			
		} // 
		
		List<MemberVo> list = dao.getAllMembers();
		
		for (int i=0; i<list.size(); i++) {
			
			String id = list.get(i).getMemberId();
			
			System.out.println(id + ":" + getCountByWord(ids, id));
		}
		
	} //

} //