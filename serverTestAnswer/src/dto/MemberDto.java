package dto;

public class MemberDto {
	
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberAddress;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	
	@Override
	public String toString() {
		return "MemberDto [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberAddress=" + memberAddress + "]";
	}
	
}