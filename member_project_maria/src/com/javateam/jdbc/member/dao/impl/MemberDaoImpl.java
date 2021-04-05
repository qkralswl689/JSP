package com.javateam.jdbc.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.javateam.jdbc.member.dao.MemberDao;
import com.javateam.jdbc.member.domain.MemberVo;
import com.javateam.jdbc.member.util.DbUtil;
import com.javateam.jdbc.member.util.ExceptionMetadata;

public class MemberDaoImpl implements MemberDao {

	@Override
	public boolean insertMember(MemberVo member) {
		// 府畔(馆券蔼)贸府
		boolean result = false;
		
		// 飘罚黎记 / 抗寇贸府 按眉 积己
		ExceptionMetadata emd = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		
		// DB 楷搬
		Connection con = DbUtil.connect();
		
		// sql 贸府按眉
		
		PreparedStatement pstmt = null;
		
		// SQL 备巩
		String sql = "INSERT INTO member VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		
		//SQL, 牢磊(急)贸府
		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return result;	
		
	}

	@Override
	public List<MemberVo> getAllMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVo getMember(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateMember(MemberVo member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMember(String memberId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMember(String memberId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String isMember(String memberId, String memberPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVo> getMembersByPaging(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
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

	// 教臂畔 按眉 积己
		private static MemberDaoImpl INSTANCE =null;
		
		private MemberDaoImpl() {}
		
		public static MemberDao getInstance() {
			if(INSTANCE == null) {
				INSTANCE = new MemberDaoImpl();
			}
			return INSTANCE;
		}

}
