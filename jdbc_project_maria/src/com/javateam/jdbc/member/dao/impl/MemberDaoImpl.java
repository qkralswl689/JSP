package com.javateam.jdbc.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javateam.jdbc.member.dao.MemberDao;
import com.javateam.jdbc.member.domain.MemberVo;
import com.javateam.jdbc.member.util.DbUtil;
import com.javateam.jdbc.member.util.ExceptionMetadata;

public class MemberDaoImpl implements MemberDao {

	@Override
	public boolean insertMember(MemberVo member) {
		// 리턴(반환값)처리
		boolean result = false;
		
		// 트랜잭션 / 예외처리 객체 생성
		ExceptionMetadata emd = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		// DB 연결
		Connection con = DbUtil.connect();
		
		// sql 처리객체
		
		PreparedStatement pstmt = null;
		
		// SQL 구문
		String sql = "INSERT INTO member VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?,?,now())";
		
		//SQL, 인자(선)처리
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPassword());
			pstmt.setString(3, member.getMemberNickname());
			pstmt.setString(4, member.getMemberName());
			pstmt.setString(5, String.valueOf(member.getMemberGender()));
			pstmt.setString(6, member.getMemberEmail());
			pstmt.setString(7, member.getMemberPhone());
			pstmt.setDate(8, member.getMemberBirth());
			pstmt.setString(9, member.getMemberZip());
			pstmt.setString(10, member.getMemberAddressBasic());
			pstmt.setString(11, member.getMemberAddressDetail());
			
			// SQL 실행,리턴값 처리, 예외처리
			if(pstmt.executeUpdate() == 1) {
				System.out.println("회원 정보 저장 성공");
				result = true;
			}else {
				System.out.println("회원 정보 저장 실패");
			}
			con.commit();
		} catch (SQLException e) {
			emd.printErr(e, con, true);
		}finally {
			DbUtil.close(con, pstmt, null);
		}
				
		return result;	
	}

	@Override
	public List<MemberVo> getAllMembers() {
		
		// 리턴(반환값)처리
		// List 객체 사용
		List<MemberVo> members = new ArrayList<>(); 
		
		// 개별 회원정보 객체 선언
		MemberVo member = null;
		
		String MethodName = new Exception().getStackTrace()[0].getMethodName();
		
		Connection con = DbUtil.connect();
		
		PreparedStatement pstmt = null;
		
		// SQL 결과셋 객체(select) 
		ResultSet rs = null;
		
		String maria = "SELECT * FROM member";
		
		try {
			pstmt = con.prepareStatement(maria);
			
			// SQL 결과셋 객체 생성
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				// 개별 회원정보 생성
				member = new MemberVo();
				
				// SQL 결과셋을 VO로 이관
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPassword(rs.getString("member_password"));
				member.setMemberNickname(rs.getString("member_nickname"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberGender(rs.getString("member_gender").charAt(0));
				member.setMemberEmail(rs.getString("member_email"));
				member.setMemberPhone(rs.getString("member_phone"));
				member.setMemberBirth(rs.getDate("member_birth"));
				member.setMemberZip(rs.getString("member_zip"));
				member.setMemberAddressBasic(rs.getString("member_address_basic"));
				member.setMemberAddressDetail(rs.getString("member_address_detail"));
				member.setMemberJoindate(rs.getDate("member_joindate"));
				
				// VO -> List로 이관(ADD) : 개별 회원 정보 추가
				members.add(member);
				
			}
		} catch (SQLException e) {
			System.out.println(MethodName + " : " + e.getMessage());
		}finally {
			DbUtil.close(con, pstmt, rs);
			
		}
		return members;
	}

	@Override
	public MemberVo getMember(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateMember(MemberVo member) {
		
		boolean result = false;
		
		String MethodName = new Exception().getStackTrace()[0].getMethodName();
		
		Connection con = DbUtil.connect();
		
		PreparedStatement pstmt = null;
		
		String maria = "UPDATE member SET member_password = ?,member_nickname = ?,"
				+ "member_zip = ? WHERE member_id = ? ";
		
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(maria);
			
			pstmt.setString(1, member.getMemberPassword());
			pstmt.setString(2, member.getMemberNickname());
			pstmt.setString(3, member.getMemberZip());
			pstmt.setString(4, member.getMemberId());
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("멤버 정보 수정 완료");
				result = true;
			}else {
				System.out.println("멤버 정보 수정 실패");
			}
			con.commit();
		} catch (SQLException e) {
			System.out.println(MethodName + " : " + e.getMessage());
		}finally {
			DbUtil.close(con, pstmt, null);
		}
		return result;
	}

	@Override
	public boolean deleteMember(String memberId) {
		
		// 리턴값 설정
		boolean result = false;
		
		// 실행메서드명 
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		
		//DB연결
		Connection con = DbUtil.connect();
		
		// SQL 처리객체
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM member WHERE member_id = ?";
		
		try {
			// 수동 커밋 모드 설정
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			if(pstmt.executeUpdate()==1) {
				System.out.println("회원 정보 삭제 완료");
				result = true;
			}else {
				System.out.println("회원 정보 삭제 실패");
			}
			con.commit();
		} catch (SQLException e) {
			System.out.println(methodName + " : " + e.getMessage());
		}finally {
			DbUtil.close(con, pstmt, null);
		}
		
		return result;
	}

	@Override
	public boolean isMember(String memberId) {
		
		// 반환값 처리
		boolean result = false;
		
		String MethodName = new Exception().getStackTrace()[0].getMethodName();
		
		//Db연결
		Connection con = DbUtil.connect();
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		String maria = "SELECT count(*) FROM member WHERE member_id = ?";
		
		try {
			pstmt = con.prepareStatement(maria);
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				// ID 가 존재 : 1 , ID없음 : 0
				// ID가 존재하면 true,없으면 false를 result 값에 담는다
				result = rs.getInt(1) == 1 ? true : false;
			}
			
			
		} catch (SQLException e) {
			
			System.out.println(MethodName + " : " + e.getMessage());
		}finally {
			DbUtil.close(con, pstmt, rs);
		}
		
		return result;
	}

	@Override
	public String isMember(String memberId, String memberPassword) {
		
		String result = "";
		
		String MethodName = new Exception().getStackTrace()[0].getMethodName();
		
		Connection con = DbUtil.connect();
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		String maria = "SELECT count(*) FROM member WHERE member_id =? AND member_password=?";
		
		try {
			pstmt = con.prepareStatement(maria);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPassword);
			
			if(this.isMember(memberId)==true) {
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result = rs.getInt(1)==1 ? "일치하는 회원이 존재합니다" : "일치하는 회원이 존재하지 않습니다";
				}
				}else {
					result = "일치하는 회원이 존재하지 않습니다";
				
				
			}
		} catch (SQLException e) {
			System.out.println(MethodName + " : " + e.getMessage());
		}finally {
			DbUtil.close(con, pstmt, rs);
		}
		
		return result;
	}

	@Override
	public List<MemberVo> getMembersByPaging(int page, int limit) {
		
		List<MemberVo> members = new ArrayList<>();
		
		// 개별회원정보 객체 선언
		MemberVo member =null;
		
		String MethodName = new Exception().getStackTrace()[0].getMethodName();
		
		Connection con = DbUtil.connect();
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		String maria = "SELECT * FROM ("
				+ "SELECT ROW_NUMBER() OVER(ORDER BY member_id, RE_STEP) ,"
				+ " m.* FORM(SELECT * FORM member ) m ) "
				+ "WHERE page = ? ";
		
		try {
			pstmt = con.prepareStatement(maria);
			
			pstmt.setInt(1, limit); // 한 페이지당 출력 인원수
			pstmt.setInt(2, page); // 현재 페이지
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				member = new MemberVo();
				
				member.setMemberId(rs.getString("member_Id"));
 				member.setMemberPassword(rs.getString("member_Password"));
 				member.setMemberNickname(rs.getString("member_Nickname"));
 				member.setMemberName(rs.getString("member_Name"));
 				member.setMemberGender(rs.getString("member_Gender").charAt(0)); // char로 변환
 				member.setMemberEmail(rs.getString("member_Email"));
 				member.setMemberPhone(rs.getString("member_Phone"));
 				member.setMemberBirth(rs.getDate("member_Birth"));
 				member.setMemberZip(rs.getString("member_Zip"));
 				member.setMemberAddressBasic(rs.getString("member_Address_Basic"));
 				member.setMemberAddressDetail(rs.getString("member_Address_Detail"));
 				member.setMemberJoindate(rs.getDate("member_JoinDate"));
 				
 				members.add(member);
			}
		} catch (SQLException e) {
			System.out.println(MethodName + " : " + e.getMessage());
		}finally {
			DbUtil.close(con, pstmt, rs);
		}
		
		return members;
	}

	@Override
	public boolean isEnableEmail(String memberEmail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnableEmail(String memberId, String memberEmail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnablePhone(String memberPhone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnablePhone(String memberId, String memberPhone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MemberVo> getMembersBySearching(String searchKey, String searchValue, boolean isEquivalentSearch,
			String sortDirection, int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	// 싱글턴객체 생성
	private static MemberDaoImpl INSTANCE =null;
	
	private MemberDaoImpl() {}
	
	public static MemberDao getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MemberDaoImpl();
		}
		return INSTANCE;
	}

}
