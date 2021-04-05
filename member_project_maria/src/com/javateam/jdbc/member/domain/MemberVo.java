package com.javateam.jdbc.member.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * Member 테이블에 대응되는 엔티티(Entity) 자바빈(javaBean)
 * : VO(값 객체) : POJO(Plain Old Java Object) 형태
 * : Plain &rarr; metadata(xml, annotation 등)이 없이 순수하게 자바 코드만으로 구성
 * : Old &rarr; EJB Entity Bean(XML base) ex) EJB 3.0은 annotation 지원
 * 에 비해서 예전 방식 
 * 
 * @author javateam
 */
public class MemberVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 멤버 필드
	/** 회원 아이디 */
	private String memberId;
	
	/** 회원 패쓰워드 */
	private String memberPassword;
	
	/** 회원 별명 */
	private String memberNickname;
	
	/** 회원 이름 */
	private String memberName;
	
	/** 회원 성별 */
	private char memberGender;
	
	/** 회원 이메일 */
	private String memberEmail;
	
	/** 회원 연락처(휴대폰) */
	private String memberPhone;
	
	/** 회원 생일 */
	private Date memberBirth;

	/** 회원 거주지 주소 우편번호 */
	private String memberZip;
	
	/** 회원 거주지 주소 기본 주소 */
	private String memberAddressBasic;
	
	/** 회원 거주지 주소 상세 주소 */
	private String memberAddressDetail;
	
	/** 회원 가입일 */
	private Date memberJoindate;
	
	// 기본 생성자 작성/오버로딩 생성자 작성
	public MemberVo() {}

	public MemberVo(String memberId, String memberPassword, String memberNickname, String memberName, char memberGender,
			String memberEmail, String memberPhone, Date memberJoindate) {
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.memberNickname = memberNickname;
		this.memberName = memberName;
		this.memberGender = memberGender;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberJoindate = memberJoindate;
	}

	public MemberVo(String memberId, String memberPassword, String memberNickname, String memberName, char memberGender,
			String memberEmail, String memberPhone, Date memberBirth, String memberZip, String memberAddressBasic,
			String memberAddressDetail, Date memberJoindate) {
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.memberNickname = memberNickname;
		this.memberName = memberName;
		this.memberGender = memberGender;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberBirth = memberBirth;
		this.memberZip = memberZip;
		this.memberAddressBasic = memberAddressBasic;
		this.memberAddressDetail = memberAddressDetail;
		this.memberJoindate = memberJoindate;
	}

	// getter/setter
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public char getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(char memberGender) {
		this.memberGender = memberGender;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public Date getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(Date memberBirth) {
		this.memberBirth = memberBirth;
	}

	public String getMemberZip() {
		return memberZip;
	}

	public void setMemberZip(String memberZip) {
		this.memberZip = memberZip;
	}

	public String getMemberAddressBasic() {
		return memberAddressBasic;
	}

	public void setMemberAddressBasic(String memberAddressBasic) {
		this.memberAddressBasic = memberAddressBasic;
	}

	public String getMemberAddressDetail() {
		return memberAddressDetail;
	}

	public void setMemberAddressDetail(String memberAddressDetail) {
		this.memberAddressDetail = memberAddressDetail;
	}

	public Date getMemberJoindate() {
		return memberJoindate;
	}

	public void setMemberJoindate(Date memberJoindate) {
		this.memberJoindate = memberJoindate;
	}

	
	// toString overriding
//	@Override
//	public String toString() {
//		return String.format(
//				"MemberVo : [%n "
//				+ " memberId=%s%n, "
//				+ "memberPassword=%s%n, "
//				+ "memberNickname=%s%n, "
//				+ "memberName=%s%n, "
//				+ "memberGender=%s%n, "
//				+ "memberEmail=%s%n, "
//				+ "memberPhone=%s%n, "
//				+ "memberBirth=%s%n, "
//				+ "memberZip=%s%n, "
//				+ "memberAddressBasic=%s%n, "
//				+ "memberAddressDetail=%s%n, "
//				+ "memberJoindate=%s]",
//				memberId, memberPassword, memberNickname, memberName, memberGender, memberEmail, memberPhone,
//				memberBirth, memberZip, memberAddressBasic, memberAddressDetail, memberJoindate);
//	}
	
	@Override
	public String toString() {
		return String.format(
				"MemberVo : ["
				+ "memberId=%s, "
				+ "memberPassword=%s, "
				+ "memberNickname=%s, "
				+ "memberName=%s, "
				+ "memberGender=%s, "
				+ "memberEmail=%s, "
				+ "memberPhone=%s, "
				+ "memberBirth=%s, "
				+ "memberZip=%s, "
				+ "memberAddressBasic=%s, "
				+ "memberAddressDetail=%s, "
				+ "memberJoindate=%s]",
				memberId, memberPassword, memberNickname, memberName, memberGender, memberEmail, memberPhone,
				memberBirth, memberZip, memberAddressBasic, memberAddressDetail, memberJoindate);
	}

	// hashcode/equals overriding
	@Override
	public int hashCode() {
		return Objects.hash(memberAddressBasic, memberAddressDetail, memberBirth, memberEmail, memberGender, memberId,
				memberJoindate, memberName, memberNickname, memberPassword, memberPhone, memberZip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof MemberVo))
			return false;
		MemberVo other = (MemberVo) obj;
		return Objects.equals(memberAddressBasic, other.memberAddressBasic)
				&& Objects.equals(memberAddressDetail, other.memberAddressDetail)
				&& Objects.equals(memberBirth, other.memberBirth) && Objects.equals(memberEmail, other.memberEmail)
				&& memberGender == other.memberGender && Objects.equals(memberId, other.memberId)
				&& Objects.equals(memberJoindate, other.memberJoindate) && Objects.equals(memberName, other.memberName)
				&& Objects.equals(memberNickname, other.memberNickname)
				&& Objects.equals(memberPassword, other.memberPassword)
				&& Objects.equals(memberPhone, other.memberPhone) && Objects.equals(memberZip, other.memberZip);
	}
	
}
