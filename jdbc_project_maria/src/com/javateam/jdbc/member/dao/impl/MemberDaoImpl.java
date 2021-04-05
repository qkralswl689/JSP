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
		// ����(��ȯ��)ó��
		boolean result = false;
		
		// Ʈ����� / ����ó�� ��ü ����
		ExceptionMetadata emd = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		// DB ����
		Connection con = DbUtil.connect();
		
		// sql ó����ü
		
		PreparedStatement pstmt = null;
		
		// SQL ����
		String sql = "INSERT INTO member VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?,?,now())";
		
		//SQL, ����(��)ó��
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
			
			// SQL ����,���ϰ� ó��, ����ó��
			if(pstmt.executeUpdate() == 1) {
				System.out.println("ȸ�� ���� ���� ����");
				result = true;
			}else {
				System.out.println("ȸ�� ���� ���� ����");
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
		
		// ����(��ȯ��)ó��
		// List ��ü ���
		List<MemberVo> members = new ArrayList<>(); 
		
		// ���� ȸ������ ��ü ����
		MemberVo member = null;
		
		String MethodName = new Exception().getStackTrace()[0].getMethodName();
		
		Connection con = DbUtil.connect();
		
		PreparedStatement pstmt = null;
		
		// SQL ����� ��ü(select) 
		ResultSet rs = null;
		
		String maria = "SELECT * FROM member";
		
		try {
			pstmt = con.prepareStatement(maria);
			
			// SQL ����� ��ü ����
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				// ���� ȸ������ ����
				member = new MemberVo();
				
				// SQL ������� VO�� �̰�
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
				
				// VO -> List�� �̰�(ADD) : ���� ȸ�� ���� �߰�
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
				System.out.println("��� ���� ���� �Ϸ�");
				result = true;
			}else {
				System.out.println("��� ���� ���� ����");
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
		
		// ���ϰ� ����
		boolean result = false;
		
		// ����޼���� 
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		
		//DB����
		Connection con = DbUtil.connect();
		
		// SQL ó����ü
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM member WHERE member_id = ?";
		
		try {
			// ���� Ŀ�� ��� ����
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			if(pstmt.executeUpdate()==1) {
				System.out.println("ȸ�� ���� ���� �Ϸ�");
				result = true;
			}else {
				System.out.println("ȸ�� ���� ���� ����");
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
		
		// ��ȯ�� ó��
		boolean result = false;
		
		String MethodName = new Exception().getStackTrace()[0].getMethodName();
		
		//Db����
		Connection con = DbUtil.connect();
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		String maria = "SELECT count(*) FROM member WHERE member_id = ?";
		
		try {
			pstmt = con.prepareStatement(maria);
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				// ID �� ���� : 1 , ID���� : 0
				// ID�� �����ϸ� true,������ false�� result ���� ��´�
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
					result = rs.getInt(1)==1 ? "��ġ�ϴ� ȸ���� �����մϴ�" : "��ġ�ϴ� ȸ���� �������� �ʽ��ϴ�";
				}
				}else {
					result = "��ġ�ϴ� ȸ���� �������� �ʽ��ϴ�";
				
				
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
		
		// ����ȸ������ ��ü ����
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
			
			pstmt.setInt(1, limit); // �� �������� ��� �ο���
			pstmt.setInt(2, page); // ���� ������
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				member = new MemberVo();
				
				member.setMemberId(rs.getString("member_Id"));
 				member.setMemberPassword(rs.getString("member_Password"));
 				member.setMemberNickname(rs.getString("member_Nickname"));
 				member.setMemberName(rs.getString("member_Name"));
 				member.setMemberGender(rs.getString("member_Gender").charAt(0)); // char�� ��ȯ
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

	// �̱��ϰ�ü ����
	private static MemberDaoImpl INSTANCE =null;
	
	private MemberDaoImpl() {}
	
	public static MemberDao getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MemberDaoImpl();
		}
		return INSTANCE;
	}

}
