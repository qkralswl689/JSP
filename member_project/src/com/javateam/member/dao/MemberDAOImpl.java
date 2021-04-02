/**
 * 
 */
package com.javateam.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javateam.member.domain.MemberVO;
import com.javateam.member.domain.RoleVO;
import com.javateam.member.util.DbUtil;
import com.javateam.member.util.ExceptionMetadata;

/**
 * DAO 구현(실체화, 현실화, implementation, realization, concrete) 
 *  클래스
 *  
 * usage) MemberDAO dao = new MemberDAO(); // (X)
 * MemberDAO dao = MemberDAOImpl.getInstance(); // (O)
 * : 싱글턴(Singleton:독신자) 패턴 적용 (보안)
 *  
 * @author javateam
 * @version 1.0
 */
public final class MemberDAOImpl implements MemberDAO {
	
	// 싱글턴 객체
	private static MemberDAOImpl INSTANCE = null;
	
	private MemberDAOImpl() {}
	
	public static final MemberDAOImpl getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new MemberDAOImpl();
		}
		
		return INSTANCE;
	}

	@Override
	public String insertMember(MemberVO member) { // throws Exception {

		// 예외처리 : reflection 적용한...
		ExceptionMetadata emd
         = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		String msg = "";
		 
		// 1. DB 연결
		Connection con = DbUtil.connect("sample", "sample");
		
		// 2. SQL 구문
		String sql = "INSERT INTO member_tbl VALUES " 
				   // + "(?,?,?,?,?,?,?,?,?,?,?)";
				   + "(?,?,?,?,?,?,?,?,?,?,sysdate)";
		
		// 3. SQL 해석
		PreparedStatement pstmt = null;
		
		try {
			// 트랜잭션 시작 : 승인(commit) 모드 수동 전환
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sql);
			
			// 4. SQL 인자 처리(PreparedStatement)
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPassword());
			pstmt.setString(3, member.getMemberNickname());
			pstmt.setString(4, member.getMemberName());
			pstmt.setString(5, member.getMemberGender());
			pstmt.setString(6, member.getMemberEmail());
			pstmt.setString(7, member.getMemberPhone());
			pstmt.setDate(8, (java.sql.Date)member.getMemberBirth());
			pstmt.setString(9, member.getMemberZip());
			pstmt.setString(10, member.getMemberAddressBasic() 
					    + "*" + member.getMemberAddressDetail());
			// pstmt.setDate(11, new java.sql.Date(System.currentTimeMillis()));
			
			// 5. SQL 실행 + 메시징(오류)
			if (pstmt.executeUpdate()==1) {
				System.out.println("회원정보 저장에 성공하였습니다.");
				msg = "회원정보 저장에 성공하였습니다.";
			} else {
				System.out.println("회원정보 저장에 실패하였습니다.");
				msg = "회원정보 저장에 실패하였습니다.";
			}
			
			// 트랜잭션 승인
			con.commit();
			
		 } catch(SQLException se) {
			emd.printErr(se, con, true);
	     } catch(Exception e) {
	    	emd.printErr(e, con, true);
		 } finally {
			// 6. DB 연결 자원 반납(close)
			DbUtil.close(con, pstmt, null);
		}
		
		return msg;
	} //

	@Override
	public MemberVO getMember(String memberId) { // throws Exception {

		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		// 1. 회원 정보 객체(VO) : 리턴
		MemberVO member = new MemberVO();
		
		// 2. DB 연결
		Connection con = DbUtil.connect("sample", "sample");
		
		// 3. SQL 구문
		String sql = "SELECT * FROM member_tbl " 
				   + "WHERE member_id=?";
		
		// 4. SQL 해석 + 결과셋(ResultSet)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			
			// 5. SQL 인자 처리(PreparedStatement)
			pstmt.setString(1, memberId);
			
			// 6. SQL 실행 + 메시징(오류)
			rs = pstmt.executeQuery();
			
			if (rs.next()) { // 회원정보가 존재할 경우
				
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPassword(rs.getString("member_password"));
				member.setMemberNickname(rs.getString("member_nickname"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberGender(rs.getString("member_gender"));
				member.setMemberEmail(rs.getString("member_email"));
				member.setMemberPhone(rs.getString("member_phone"));
				member.setMemberBirth(rs.getDate("member_birth"));
				member.setMemberZip(rs.getString("member_zip"));
				
				String addr[] = rs.getString("member_address").split("\\*");
				member.setMemberAddressBasic(addr[0]==null ? "" : addr[0]);
				member.setMemberAddressDetail(addr[1]==null ? "" : addr[1]);
				member.setMemberJoinDate(rs.getDate("member_joindate"));
				
			} else {
				System.out.println("해당되는 회원정보가 없습니다.");
			}
			
			con.commit();
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			// 7. DB 연결 자원 반납(close)
			DbUtil.close(con, pstmt, rs);
		}
		
		return member;
	}

	@Override
	public String updateMember(MemberVO member) throws Exception {

		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		String msg = "";
		
		// 1. DB 연결
		Connection con = DbUtil.connect("sample", "sample");
		
		// 2. SQL 구문
		String sql = "UPDATE member_tbl SET " 
				   + "	member_password=?, " 
				   + "	member_nickname=?, " 
				   + "	member_name=?, " 
				   + "	member_email=?, " 
				   + "	member_phone=?, " 
				   + "	member_zip=?, " 
				   + "	member_address=? " 
				   + "WHERE member_id=?";
		
		// 3. SQL 해석
		PreparedStatement pstmt = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			
			// 4. SQL 인자 처리(PreparedStatement)
			pstmt.setString(1, member.getMemberPassword());
			pstmt.setString(2, member.getMemberNickname());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberEmail());
			pstmt.setString(5, member.getMemberPhone());
			pstmt.setString(6, member.getMemberZip());
			pstmt.setString(7, member.getMemberAddressBasic() + "*" 
							 + member.getMemberAddressDetail());
			pstmt.setString(8, member.getMemberId());
			
			// 5. SQL 실행 + 메시징(오류)
			if (pstmt.executeUpdate()==1) {
				System.out.println("회원정보 수정에 성공하였습니다.");
				msg = "회원정보 수정에 성공하였습니다.";
			} else {
				System.out.println("회원정보 수정에 실패하였습니다.");
				msg = "회원정보 수정에 실패하였습니다.";
			}
			
			con.commit();
			
		 } catch(SQLException se) {
			 emd.printErr(se, con, true);
	     } catch(Exception e) {
	    	 emd.printErr(e, con, true);
		 } finally {
			 // 6. DB 연결 자원 반납(close)
			 DbUtil.close(con, pstmt, null);
		}
		
		return msg;
	}

	@Override
	public String deleteMember(String memberId) throws Exception {
		
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		String msg = "";
		
		// 1. DB 연결
		Connection con = DbUtil.connect("sample", "sample");
		
		// 2. SQL 구문
		String sql = "DELETE FROM member_tbl WHERE member_id=?";
		
		// 3. SQL 해석
		PreparedStatement pstmt = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			
			// 4. SQL 인자 처리(PreparedStatement)
			pstmt.setString(1, memberId);
			
			// 5. SQL 실행 + 메시징(오류)
			if (pstmt.executeUpdate()==1) {
				System.out.println("회원정보 삭제에 성공하였습니다.");
				msg = "회원정보 삭제에 성공하였습니다.";
			} else {
				System.out.println("회원정보 삭제에 실패하였습니다.");
				msg = "회원정보 삭제에 실패하였습니다.";
			}
			
			con.commit();
			
		 } catch(SQLException se) {
			 emd.printErr(se, con, true);
	     } catch(Exception e) {
	    	 emd.printErr(e, con, true);
		 } finally {
			 // 6. DB 연결 자원 반납(close)
			 DbUtil.close(con, pstmt, null);
		 }
		
		return msg;		
	} //

	@Override
	public List<MemberVO> getAllMembers() throws Exception {

		// 1. 예외/트랜잭션 처리 객체 생성
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		// 2. 회원 정보들 객체(List) : 리턴
		List<MemberVO> members = new ArrayList<>();
		
		// 중복행 출력의 오류 유발 방지 
		MemberVO member = null; // (O)
		// MemberVO member = new MemberVO(); // (X)
		// 중복행 출력 !
		
		// 3. DB 연결
		Connection con = DbUtil.connect("sample", "sample");
		
		// 4. SQL 구문
		String sql = "SELECT * FROM member_tbl";
		
		// 5. SQL 해석 + 결과셋(ResultSet)
		// 다수행 결과 : while(O) if(X)
		// while문 => 한 행( one row) => members.add(VO)
		// 주의) 한명의 VO 생성시 => 중복행 출력 ! (X) 오류 
		// 해법) 중복행 출력 방지 : member = new MemberVO();
		// rs 결과 => VO(member) 대입 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			
			// 6. SQL 실행 + 메시징(오류) 
			// : 1 과정에서 생성된 예외처리 객체 활용
			rs = pstmt.executeQuery();
			
			while (rs.next()) { // 회원정보가 존재할 경우
				
				member = new MemberVO();
				
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPassword(rs.getString("member_password"));
				member.setMemberNickname(rs.getString("member_nickname"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberGender(rs.getString("member_gender"));
				member.setMemberEmail(rs.getString("member_email"));
				member.setMemberPhone(rs.getString("member_phone"));
				member.setMemberBirth(rs.getDate("member_birth"));
				member.setMemberZip(rs.getString("member_zip"));
				
				String addr[] = rs.getString("member_address").split("\\*");
				member.setMemberAddressBasic(addr[0]==null ? "" : addr[0]);
				member.setMemberAddressDetail(addr[1]==null ? "" : addr[1]);
				member.setMemberJoinDate(rs.getDate("member_joindate"));
				
				members.add(member);
			} 
			
			con.commit();
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			// 7. DB 연결 자원 반납(close)
			DbUtil.close(con, pstmt, rs);
		}
		
		return members;
	} //

	@Override
	public int getRowCount(String sql) throws Exception {

		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		int result = 0;		
		Connection con = DbUtil.connect("sample", "sample");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) { 
				result = rs.getInt(1);
				System.out.println("result : "+result);
			}
			con.commit();
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(con, pstmt, rs);
		}
		
		return result;
	}

	/*
	@Override
	public MemberVO[] getAllMembersByArray() throws Exception {

		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		String sql = "SELECT * FROM member_tbl";
		MemberVO members[] = new MemberVO[this.getRowCount("SELECT count(*) FROM member_tbl")];
		MemberVO member = null;
		Connection con = DbUtil.connect("sample", "sample");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) { // 회원정보가 존재할 경우
				
				member = new MemberVO();
				
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPassword(rs.getString("member_password"));
				member.setMemberNickname(rs.getString("member_nickname"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberGender(rs.getString("member_gender"));
				member.setMemberEmail(rs.getString("member_email"));
				member.setMemberPhone(rs.getString("member_phone"));
				member.setMemberBirth(rs.getDate("member_birth"));
				member.setMemberZip(rs.getString("member_zip"));
				
				String addr[] = rs.getString("member_address").split("\\*");
				member.setMemberAddressBasic(addr[0]==null ? "" : addr[0]);
				member.setMemberAddressDetail(addr[1]==null ? "" : addr[1]);
				member.setMemberJoinDate(rs.getDate("member_joindate"));
			
				members[count] = member;
				count++;
			} 
			
			con.commit();
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(con, pstmt, rs);
		}
		
		return members;
	}
	*/
	
	@Override
	public MemberVO[] getAllMembersByArray() throws Exception {
		
		MemberVO[] members = new MemberVO[this.getRowCount("SELECT count(*) FROM member_tbl")];
		return this.getAllMembers().toArray(members);
	}

	@Override
	public boolean isMember(String memberId) throws Exception {
		
		return this.getRowCount("SELECT count(*) FROM member_tbl "
							  + "WHERE member_id='"+memberId+"'")==1 ? true : false;
	}

	/*
	@Override
	public boolean isMember(String memberId) {

		// 1. 예외처리 객체 생성
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		// 2. 리턴값 변수 선언 및 리턴 처리(코드 끝부분)
		boolean flag = false;

		// 3. DB 연결
		Connection con = DbUtil.connect("sample", "sample");
		
		// 4. SQL 구문 : count(*) 오라클 함수 활용
		String sql = "SELECT count(*) FROM member_tbl WHERE member_id=?";
		
		// 5. SQL 해석/결과셋 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 6. 트랜잭션 수동 모드 설정
			con.setAutoCommit(false);
			
			// 7. SQL/인자 처리 및 실행
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				flag = rs.getInt(1)==1 ? true : false;
			}
			
			// 8. 트랜잭션 승인
			con.commit();
		
		// 9. 예외처리	 및 자원 반납
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(con, pstmt, rs);
		}
		
		return flag;
	}	
	*/
	
	@Override
	public boolean isEnableEmail(String memberId, String email) {

		// 1. 예외처리 객체 생성
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		// 2. 리턴값 변수 선언 및 리턴 처리(코드 끝부분)
		boolean flag = false;

		// 3. DB 연결
		Connection con = DbUtil.connect("sample", "sample");
		
		// 4. SQL 구문 : count(*) 오라클 함수 활용
		/*
		String sql = "SELECT count(*) FROM " 
				   + "   (SELECT member_email FROM member_tbl " 
				   + "	  WHERE member_id != ?) "
				   + "WHERE member_email = ?";
		*/
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT count(*) FROM ")
		  .append("   (SELECT member_email FROM member_tbl ")
		  .append("    WHERE member_id != ?) ")
		  .append("WHERE member_email = ?");
		
		// 5. SQL 해석/결과셋 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 6. 트랜잭션 수동 모드 설정
			con.setAutoCommit(false);
			
			// 7. SQL/인자 처리 및 실행
			// pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sb.toString()); // StringBuilder
			pstmt.setString(1, memberId);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				flag = rs.getInt(1)==0 ? true : false;
			}
			
			// 8. 트랜잭션 승인
			con.commit();
		
		// 9. 예외처리	 및 자원 반납
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(con, pstmt, rs);
		}
		
		return flag;
	}
	
	/*
	@Override
	public String checkLogin(String memberId, String memberPassword) throws Exception {

		// 1. 예외처리 객체 생성
	       
	    // 2. 리턴값 변수(에러 메시지) 선언 및 리턴 처리(코드 끝부분)
	   
	    // 3. DB 연결
	       
	    // 4. SQL 구문
	       
	    // 5. SQL 해석/결과셋 객체 생성    
	    
	    // 6. isMember 메서드를 활용하여 회원인지 여부 점검 : 범위 넓은 조건문(if) 적용     
	   
	    // 7. 트랜잭션 수동 모드 설정
	           
	    // 8. SQL/인자 처리 및 실행
	       
	    // 9. 트랜잭션 승인
	       
	    // 10. 예외처리 및 자원 반납
		
		return null;
	}
	*/

	@Override
	public String checkLogin(String memberId, String memberPassword) throws Exception {

		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		String msg = "";
		Connection con = DbUtil.connect("sample", "sample");
		String sql = "SELECT * FROM member_tbl " 
				   + "WHERE member_id=? AND member_password=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if (this.isMember(memberId)==false) {
			
			msg = "해당되는 회원정보가 없습니다.";
			
		} else {
		
			try {
				con.setAutoCommit(false);
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, memberId);
				pstmt.setString(2, memberPassword);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) { // 회원정보가 존재할 경우
					
					msg = memberId + " 님이 로그인 하셨습니다";
					
				} else {
					msg = "패쓰워드가 잘못되었습니다.";
				}
				
				con.commit();
				
			} catch(SQLException se) {
				emd.printErr(se, con, true);
			} catch(Exception e) {
				emd.printErr(e, con, true);
			} finally {
				DbUtil.close(con, pstmt, rs);
			}
		} // if 
		
		return msg;
	} //

	@Override
	public List<MemberVO> getMembersByPaging(int page, int limit) {
		
		// 1. 예외/트랜잭션 처리 객체 생성
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		// 2. 회원 정보들 객체(List) : 리턴
		List<MemberVO> members = new ArrayList<>();
		MemberVO member = null;
		
		// 3.DB 연결
		Connection con = DbUtil.connect("sample", "sample");
		
		// 4.SQL 구문 : 페이징 적용
		// 첫번째 인자 : 한번에 가져올 수 있는 인원수
		// 두번째 인자 : 현재 페이지
		String sql = "SELECT * "  
				   + "FROM (SELECT ROWNUM, "  
				   + "             m.*,  "  
				   + "             FLOOR((ROWNUM - 1) / ? + 1) page "  
				   + "      FROM ( " 
				   + "             SELECT * FROM member_tbl "  
				   + "             ORDER BY member_id ASC"  
				   + "           ) m  "  
				   + "      )   "  
				   + "WHERE page = ?";
		
		// 5.SQL 해석 + 결과셋(ResultSet)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		    // 6. 트랜잭션 수동 모드 설정
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  limit);
			pstmt.setInt(2,  page);
			
			// 7.SQL 실행 + 메시징(오류) 
			rs = pstmt.executeQuery();
			
			while (rs.next()) { // 회원정보가 존재할 경우
				
				member = new MemberVO();
				
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPassword(rs.getString("member_password"));
				member.setMemberNickname(rs.getString("member_nickname"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberGender(rs.getString("member_gender"));
				member.setMemberEmail(rs.getString("member_email"));
				member.setMemberPhone(rs.getString("member_phone"));
				member.setMemberBirth(rs.getDate("member_birth"));
				member.setMemberZip(rs.getString("member_zip"));
				
				String addr[] = rs.getString("member_address").split("\\*");
				member.setMemberAddressBasic(addr[0]==null ? "" : addr[0]);
				member.setMemberAddressDetail(addr[1]==null ? "" : addr[1]);
				member.setMemberJoinDate(rs.getDate("member_joindate"));
				
				members.add(member);
			}
			 
			// 8. 트랜잭션 승인
			con.commit();
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			// 9.DB 연결 자원 반납(close)
			DbUtil.close(con, pstmt, rs);
		}
		
		return members;
	} //

	@Override
	public List<MemberVO> getMembersByField(String fld, Object value, boolean isLike) {
		
		// 1. 예외/트랜잭션 처리 객체 생성
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		// 2. 회원 정보들 객체(List) : 리턴
		List<MemberVO> members = new ArrayList<>();
		MemberVO member = null;
		
		// 3.DB 연결
		Connection con = DbUtil.connect("sample", "sample");
		
		// 4.유사 검색 여부에 따른 연산자 및 필드값 부분 처리, SQL 구문
		String expr = isLike == true ? "like '%"+value+"%'" : "= '"+value+"'";	
		
		String sql = "SELECT * FROM member_tbl " 
				   + "WHERE " + fld + " " + expr;
				
		// 5.SQL 해석 + 결과셋(ResultSet)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		    // 6. 트랜잭션 수동 모드 설정
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			
			// 7.SQL 실행 + 메시징(오류) 
			rs = pstmt.executeQuery();
			
			while (rs.next()) { // 회원정보가 존재할 경우
				
				member = new MemberVO();
				
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPassword(rs.getString("member_password"));
				member.setMemberNickname(rs.getString("member_nickname"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberGender(rs.getString("member_gender"));
				member.setMemberEmail(rs.getString("member_email"));
				member.setMemberPhone(rs.getString("member_phone"));
				member.setMemberBirth(rs.getDate("member_birth"));
				member.setMemberZip(rs.getString("member_zip"));
				
				String addr[] = rs.getString("member_address").split("\\*");
				member.setMemberAddressBasic(addr[0]==null ? "" : addr[0]);
				member.setMemberAddressDetail(addr[1]==null ? "" : addr[1]);
				member.setMemberJoinDate(rs.getDate("member_joindate"));
								
				members.add(member);
			}
			 
			// 8. 트랜잭션 승인
			con.commit();
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			// 9.DB 연결 자원 반납(close)
			DbUtil.close(con, pstmt, rs);
		}
		
		return members;
	}

	@Override
	public boolean isEnableEmail(String email) throws Exception {

		return this.getRowCount("SELECT count(*) FROM member_tbl "
				  + "WHERE member_email='"+email+"'")==0 ? true : false;
	}

	/*
	@Override
	public boolean isEnableEmail(String email) throws Exception {

		// 1. 예외처리 객체 생성
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		// 2. 리턴값 변수 선언 및 리턴 처리(코드 끝부분)
		boolean flag = false;

		// 3. DB 연결
		Connection con = DbUtil.connect("sample", "sample");
		
		// 4. SQL 구문 : count(*) 오라클 함수 활용
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT count(*) FROM member_tbl ")
		  .append("WHERE member_email = ?");
		
		// 5. SQL 해석/결과셋 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 6. 트랜잭션 수동 모드 설정
			con.setAutoCommit(false);
			
			// 7. SQL/인자 처리 및 실행
			// pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sb.toString()); // StringBuilder
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				flag = rs.getInt(1)==0 ? true : false;
			}
			
			// 8. 트랜잭션 승인
			con.commit();
		
		// 9. 예외처리  및 자원 반납
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(con, pstmt, rs);
		}
		
		return flag;
	} */
	
	@Override
	public boolean isEnablePhone(String phone) throws Exception {

		return this.getRowCount("SELECT count(*) FROM member_tbl "
				  + "WHERE member_phone='"+phone+"'")==0 ? true : false;
	}

	@Override
	public List<MemberVO> getMembersByFieldAndPaging(String fld, Object value, boolean isLike, int page, int limit)
			throws Exception {

		// 1. 예외/트랜잭션 처리 객체 생성
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		// 2. 회원 정보들 객체(List) : 리턴
		List<MemberVO> members = new ArrayList<>();
		MemberVO member = null;
		
		// 3.DB 연결
		Connection con = DbUtil.connect("sample", "sample");
		
		// 4.유사 검색 여부에 따른 연산자 및 필드값 부분 처리, SQL 구문
		String expr = isLike == true ? "like '%"+value+"%'" : "= '"+value+"'";	
		
		String sql = "SELECT * "  
				   + "FROM (SELECT ROWNUM, "  
				   + "             m.*,  "  
				   + "             FLOOR((ROWNUM - 1) / ? + 1) page "  
				   + "      FROM ( " 
				   + "             SELECT * FROM member_tbl "  
				   + "             WHERE " + fld + " " + expr
				   + "             ORDER BY member_id ASC"  
				   + "           ) m  "  
				   + "      )   "  
				   + "WHERE page = ?";
				
		// 5.SQL 해석 + 결과셋(ResultSet)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		    // 6. 트랜잭션 수동 모드 설정
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, limit);
			pstmt.setInt(2, page);
			
			// 7.SQL 실행 + 메시징(오류) 
			rs = pstmt.executeQuery();
			
			while (rs.next()) { // 회원정보가 존재할 경우
				
				member = new MemberVO();
				
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPassword(rs.getString("member_password"));
				member.setMemberNickname(rs.getString("member_nickname"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberGender(rs.getString("member_gender"));
				member.setMemberEmail(rs.getString("member_email"));
				member.setMemberPhone(rs.getString("member_phone"));
				member.setMemberBirth(rs.getDate("member_birth"));
				member.setMemberZip(rs.getString("member_zip"));
				
				String addr[] = rs.getString("member_address").split("\\*");
				member.setMemberAddressBasic(addr[0]==null ? "" : addr[0]);
				member.setMemberAddressDetail(addr[1]==null ? "" : addr[1]);
				member.setMemberJoinDate(rs.getDate("member_joindate"));
								
				members.add(member);
			}
			 
			// 8. 트랜잭션 승인
			con.commit();
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			// 9.DB 연결 자원 반납(close)
			DbUtil.close(con, pstmt, rs);
		}
		
		return members;
	}

	@Override
	public boolean isEnablePhone(String memberId, String phone) {

		// 1. 예외처리 객체 생성
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		// 2. 리턴값 변수 선언 및 리턴 처리(코드 끝부분)
		boolean flag = false;

		// 3. DB 연결
		Connection con = DbUtil.connect("sample", "sample");
		
		// 4. SQL 구문 : count(*) 오라클 함수 활용
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT count(*) FROM ")
		  .append("   (SELECT member_phone FROM member_tbl ")
		  .append("    WHERE member_id != ?) ")
		  .append("WHERE member_phone = ?");
		
		// 5. SQL 해석/결과셋 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 6. 트랜잭션 수동 모드 설정
			con.setAutoCommit(false);
			
			// 7. SQL/인자 처리 및 실행
			pstmt = con.prepareStatement(sb.toString()); // StringBuilder
			pstmt.setString(1, memberId);
			pstmt.setString(2, phone);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				flag = rs.getInt(1)==0 ? true : false;
			}
			
			// 8. 트랜잭션 승인
			con.commit();
		
		// 9. 예외처리	 및 자원 반납
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(con, pstmt, rs);
		}
		
		return flag;
	} //

	@Override
	public String getMemberIdByMemberInfo(String memberName, String email, String phone) {
		
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		String msg = "";
		Connection con = DbUtil.connect("sample", "sample");
		String sql = "SELECT member_id FROM member_tbl " 
				   + "WHERE member_name=? "
				   + "AND member_email=? "
				   + "AND member_phone=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberName);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) { // 회원 아이디가 존재할 경우
				msg = "회원 아이디는 <b>" + rs.getString("member_id") +"</b> 입니다.";
			} else {
				msg = "해당되는 회원정보가 없습니다.";
			}
			
			con.commit();
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(con, pstmt, rs);
		}
		
		return msg;
	} //

	@Override
	public String getMemberPwByMemberInfo(String memberId, String memberName, String email, String phone) {
		
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		String msg = "";
		Connection con = DbUtil.connect("sample", "sample");
		String sql = "SELECT member_password FROM member_tbl " 
				   + "WHERE member_id=? "
				   + "AND member_name=? "
				   + "AND member_email=? "
				   + "AND member_phone=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberName);
			pstmt.setString(3, email);
			pstmt.setString(4, phone);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) { // 회원 패쓰워드가 존재할 경우
				msg = "회원 패쓰워드는 <b>" + rs.getString("member_password") +"</b> 입니다.";
			} else {
				msg = "해당되는 회원정보가 없습니다.";
			}
			
			con.commit();
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(con, pstmt, rs);
		}
		
		return msg;
	}

	@Override
	public String getRoleByMemberId(String memberId) throws Exception {

		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		String msg = "";
		Connection con = DbUtil.connect("sample", "sample");
		String sql = "SELECT member_role FROM user_role " 
				   + "WHERE member_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) { 
				msg = rs.getString("member_role");
			} else {
				msg = "해당되는 회원 롤(role)정보가 없습니다.";
			}
			con.commit();
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(con, pstmt, rs);
		}
		
		return msg;
	} //

	@Override
	public void deleteRoleByMemberId(String memberId) throws Exception {

		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		Connection con = DbUtil.connect("sample", "sample");
		String sql = "DELETE FROM user_role WHERE member_id=?";
		PreparedStatement pstmt = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			if (pstmt.executeUpdate()==1) {
				System.out.println("회원 롤(Role)정보 삭제에 성공하였습니다.");
			} else {
				System.out.println("회원 롤(Role)정보 삭제에 실패하였습니다.");
			}
			con.commit();
			
		 } catch(SQLException se) {
			 emd.printErr(se, con, true);
	     } catch(Exception e) {
	    	 emd.printErr(e, con, true);
		 } finally {
			 DbUtil.close(con, pstmt, null);
		 }
	} //

	@Override
	public void insertRole(RoleVO roleVO) throws Exception {
		
		ExceptionMetadata emd
         = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.connect("sample", "sample");
		String sql = "INSERT INTO user_role VALUES (user_role_seq.nextval,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, roleVO.getMemberId());
			pstmt.setString(2, roleVO.getMemberRole());
			if (pstmt.executeUpdate()==1) {
				System.out.println("회원 롤(Role)정보 저장에 성공하였습니다.");
			} else {
				System.out.println("회원 롤(Role)정보 저장에 실패하였습니다.");
			}
			con.commit();
			
		 } catch(SQLException se) {
			emd.printErr(se, con, true);
	     } catch(Exception e) {
	    	emd.printErr(e, con, true);
		 } finally {
			DbUtil.close(con, pstmt, null);
		}
	} //

	@Override
	public void updateRole(RoleVO roleVO) throws Exception {
		
		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.connect("sample", "sample");
		String sql = "UPDATE user_role SET " 
				   + "	member_role=? " 
				   + "WHERE member_id=?";
		PreparedStatement pstmt = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, roleVO.getMemberRole());
			pstmt.setString(2, roleVO.getMemberId());
			
			if (pstmt.executeUpdate()==1) {
				System.out.println("회원 롤(role)정보 수정에 성공하였습니다.");
			} else {
				System.out.println("회원 롤(role)정보 수정에 실패하였습니다.");
			}
			con.commit();
			
		 } catch(SQLException se) {
			 emd.printErr(se, con, true);
	     } catch(Exception e) {
	    	 emd.printErr(e, con, true);
		 } finally {
			 DbUtil.close(con, pstmt, null);
		 }
	} //

	@Override
	public List<RoleVO> getRolesByPaging(int page, int limit) throws Exception {

		ExceptionMetadata emd
        = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		List<RoleVO> roles = new ArrayList<>();
		RoleVO role = null;
		Connection con = DbUtil.connect("sample", "sample");

		// 첫번째 인자 : 한번에 가져올 수 있는 인원수
		// 두번째 인자 : 현재 페이지
		String sql = "SELECT * "  
				   + "FROM (SELECT ROWNUM, "  
				   + "             m.*,  "  
				   + "             FLOOR((ROWNUM - 1) / ? + 1) page "  
				   + "      FROM ( " 
				   + "             SELECT * FROM user_role "  
				   + "             ORDER BY role_id ASC"  
				   + "           ) m  "  
				   + "      )   "  
				   + "WHERE page = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  limit);
			pstmt.setInt(2,  page);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) { // 회원정보가 존재할 경우
				role = new RoleVO();
				role.setRoleId(rs.getInt("role_id"));
				role.setMemberId(rs.getString("member_id"));
				role.setMemberRole(rs.getString("member_role"));
				roles.add(role);
			}
			con.commit();
			
		} catch(SQLException se) {
			emd.printErr(se, con, true);
		} catch(Exception e) {
			emd.printErr(e, con, true);
		} finally {
			DbUtil.close(con, pstmt, rs);
		}
		
		return roles;
	} //

	@Override
	public int getRolesNumber() throws Exception {
		return this.getRowCount("SELECT count(*) FROM user_role");
	} //
	
}