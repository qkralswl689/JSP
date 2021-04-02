/**
 * 
 */
package com.javateam.member.domain;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 회원정보 VO(값 객체)
 * 
 * @author javateam
 * @version 1.0
 */
public class MemberVO {
	
	/** 아이디 */
	private String memberId;
	/** 패쓰워드 */
	private String memberPassword;
	/** 별명 */
	private String memberNickname;
	/** 이름 */
	private String memberName;
	/** 성별 */
	private String memberGender;
	/** 이메일 */
	private String memberEmail;
	/** 연락처 */
	private String memberPhone;
	/** 생년월일 */
	private Date memberBirth;
	/** 우편번호 */
	private String memberZip;
	/** 기본주소 */
	private String memberAddressBasic;
	/** 상세 주소 */
	private String memberAddressDetail;
	/** 가입일 */
	private Date memberJoinDate;
	
	/**
	 * 기본 생성자
	 */
	public MemberVO() {
		
	}
	
	/**
	 * 멤버 필드 초기화 : 생성자
	 * 
	 * @param memberId 아이디
	 * @param memberPassword 패쓰워드
	 * @param memberNickname 별명
	 * @param memberName 이름
	 * @param memberGender 성별
	 * @param memberEmail 이메일
	 * @param memberPhone 연락처
	 * @param memberBirth 생년월일
	 * @param memberZip 우편번호
	 * @param memberAddressBasic 기본주소
	 * @param memberAddressDetail 상세주소
	 * @param memberJoinDate 가입일
	 */
	public MemberVO(String memberId, String memberPassword, String memberNickname, String memberName,
			String memberGender, String memberEmail, String memberPhone, Date memberBirth, String memberZip,
			String memberAddressBasic, String memberAddressDetail, Date memberJoinDate) {
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
		this.memberJoinDate = memberJoinDate;
	}
	
	/**
	 * Map 형태(request.getParameterMap())로 인자를 받는 경우 인자 입력처리 생성자
	 *  
	 * @param map 인자 맵(Map)
	 */
	public MemberVO(Map<String, String[]> map) {
		
		System.out.println("인자 입력 처리");
		
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		Field field; // reflection 정보 활용
		
		while (it.hasNext()) {
			
			String fldName = it.next();
			
			if (!fldName.equals("joinSubmitBtn"))  { // 버튼 필드 제외
				
			    try {
			    		// VO(MemberVO)와 1:1 대응되는 필드들 처리 
				    	try {
								field = this.getClass().getDeclaredField(fldName);
								System.out.println(fldName + " : "+field.getName());
								field.setAccessible(true);
								// System.out.println("field : "+field);
								
								// 생일 변환
								if (fldName.equals("memberBirth")) {
									field.set(this, java.sql.Date.valueOf(map.get(fldName)[0]));
								} else {
									field.set(this, map.get(fldName)[0]);
								}
								
						} catch (NoSuchFieldException e) {
							
							// 만약 VO와 1:1 대응되지 않는 인자일 경우는 이 부분에서 입력처리합니다.
							System.out.println("인자와 필드가 일치하지 않습니다."); 
							
						} // try
						
				} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) { 
					e.printStackTrace();
				} // try
		    
			} // if
		    
		} // while
		
	} //
	

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

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
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

	public Date getMemberJoinDate() {
		return memberJoinDate;
	}

	public void setMemberJoinDate(Date memberJoinDate) {
		this.memberJoinDate = memberJoinDate;
	}

	@Override
	public String toString() {
		return String.format(
				"MemberVO [memberId=%s, memberPassword=%s, memberNickname=%s, memberName=%s, memberGender=%s, memberEmail=%s, memberPhone=%s, memberBirth=%s, memberZip=%s, memberAddressBasic=%s, memberAddressDetail=%s, memberJoinDate=%s]",
				memberId, memberPassword, memberNickname, memberName, memberGender, memberEmail, memberPhone,
				memberBirth, memberZip, memberAddressBasic, memberAddressDetail, memberJoinDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(memberAddressBasic, memberAddressDetail, memberBirth, memberEmail, memberGender, memberId,
				memberJoinDate, memberName, memberNickname, memberPassword, memberPhone, memberZip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MemberVO)) {
			return false;
		}
		MemberVO other = (MemberVO) obj;
		return Objects.equals(memberAddressBasic, other.memberAddressBasic)
				&& Objects.equals(memberAddressDetail, other.memberAddressDetail)
				&& Objects.equals(memberBirth, other.memberBirth) && Objects.equals(memberEmail, other.memberEmail)
				&& Objects.equals(memberGender, other.memberGender) && Objects.equals(memberId, other.memberId)
				&& Objects.equals(memberJoinDate, other.memberJoinDate) && Objects.equals(memberName, other.memberName)
				&& Objects.equals(memberNickname, other.memberNickname)
				&& Objects.equals(memberPassword, other.memberPassword)
				&& Objects.equals(memberPhone, other.memberPhone) && Objects.equals(memberZip, other.memberZip);
	}

}