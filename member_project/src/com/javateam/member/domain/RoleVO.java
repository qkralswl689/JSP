package com.javateam.member.domain;

/**
 * 회원 롤(Role) VO
 * 
 * @author javateam 
 */
public class RoleVO {
	
	/** Role 아이디 : 자동 생성(seq) */
	private int roleId;
	
	/** 회원 아이디 */
	private String memberId;
	
	/** 회원 롤(role) : admin/member/guest */
	private String memberRole;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}

	@Override
	public String toString() {
		return "RoleVO [roleId=" + roleId + ", memberId=" + memberId + ", memberRole=" + memberRole + "]";
	}

}