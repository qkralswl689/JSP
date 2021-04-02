/**
 * 
 */
package com.javateam.member.dao;

import java.util.List;

import com.javateam.member.domain.MemberVO;
import com.javateam.member.domain.RoleVO;

/**
 * 회원정보객체 DAO : CRUD 메서드(DML, DQL)
 * 
 * @author javateam
 * @version 1.0
 */
public interface MemberDAO {
	
	/**
	 * 회원정보 레코드(튜플) 삽입(create)
	 * 
	 * @param member 회원정보
	 * @return 저장 오류 메시지
	 * @throws Exception 예외처리 
	 */
	String insertMember(MemberVO member) throws Exception;
	
	/**
	 * 개별 회원정보 레코드(튜플) 조회(read)
	 * 
	 * @param memberId 회원 아이디
	 * @return 회원정보
	 * @throws Exception 예외처리
	 */
	MemberVO getMember(String memberId) throws Exception;
	
	/**
	 * 개별 회원정보 레코드(튜플) 수정(갱신) (update)
	 * 
	 * @param member 회원정보
 	 * @return 저장 오류 메시지 
	 * @throws Exception 예외처리
	 */
	String updateMember(MemberVO member) throws Exception;
	
	/**
	 * 개별 회원정보 레코드(튜플) 삭제 (delete)
	 * 
	 * @param memberId 회원 아이디
	 * @return 저장 오류 메시지
	 * @throws Exception 예외처리
	 */
	String deleteMember(String memberId) throws Exception;
	
	/**
	 * 전체 회원정보  레코드(튜플) 조회(read)
	 * 
	 * @return 전체 회원정보
	 * @throws Exception 예외처리
	 */
	List<MemberVO> getAllMembers() throws Exception;
	
	/**
	 * 주어진 SQL(DQL)에 적용되는 행(row)의 수
	 *  
	 * @param sql SQL구문
	 * @return 적용 행(row) 수
	 * @throws Exception 예외처리
	 */
	int getRowCount(String sql) throws Exception;
	
	/**
	 * 전체 회원정보  레코드(튜플) 조회(read) : 배열
	 * 
	 * @return 전체 회원정보(배열)
	 * @throws Exception 예외처리
	 */
	MemberVO[] getAllMembersByArray() throws Exception;
	
	/**
	 * 주어진 회원 아이디의 회원정보가 있는지 점검
	 * 
	 * usage) 아이디 중복 점검 등에서 사용
	 * 
	 * @param memberId 회원 아이디
	 * @return 회원 존재 여부 
	 * @throws Exception 예외처리
	 */
	boolean isMember(String memberId) throws Exception;
	
	/**
	 * 이메일을 타 회원들과 중복하지 않고 사용가능한지 여부 점검
	 * 
	 * usage) 회원 가입 이메일 중복 점검
	 * 
	 * @param email 이메일
	 * @return 이메일 사용가능 여부
	 * @throws Exception 예외처리
	 */
	boolean isEnableEmail(String email) throws Exception;	
	
	/**
	 * 주어진 회원 아이디의 이메일을 타 회원들과 중복하지 않고 사용가능한지 여부 점검
	 * 
	 * usage) 회원 가입/변경시 이메일 중복 점검
	 * 
	 * @param memberId 회원 아이디
	 * @param email 이메일
	 * @return 이메일 사용가능 여부
	 * @throws Exception 예외처리
	 */
	boolean isEnableEmail(String memberId, String email) throws Exception;
	
	/**
	 * 회원 아이이와 패쓰워드를 활용하여 로그인을 점검
	 * 
	 * ex) 로그인 메시지는 아래와 같이 적용합니다.
	 * 
	 * 1) 회원 아이디가 없을 경우 : 해당되는 회원정보가 없습니다.
	 * 2) 패쓰워드가 틀릴 경우 : 패쓰워드가 잘못되었습니다.
	 * 3) 정상적인 로그인(모두 맞을 경우) : (회원 아이디) 님이 로그인 하셨습니다.
	 * 
	 * @param memberId 회원 아이디
	 * @param memberPassword 회원 패쓰워드
	 * @return 로그인 메시지
	 * @throws Exception 예외처리
	 */
	String checkLogin(String memberId, String memberPassword) throws Exception; 
	
	/**
	 * 페이징을 활용하여 일정한 수의 회원들만을 화면에 출력
	 * 
	 * @param page 현재 페이지
	 * @param limit 한번에 출력할 수 있는 회원정보
	 * @return 회원정보들
	 * @throws Exception 예외처리
	 */
	List<MemberVO> getMembersByPaging(int page, int limit) throws Exception;
	
    /**
            * 특정 필드(이름, 아이디, 주소 등등)를 이용한 검색 조회
     * 
     * @param fld 검색할 필드
     * @param value 필드 값
     * @param isLike 유사 검색(like) 여부  usage) 유사 검색 : true, 동등 검색 : false  
     * @return 검색 결과(회원 정보들)
     * @throws Exception 예외처리
     */
	List<MemberVO> getMembersByField(String fld, Object value, boolean isLike) throws Exception;
	
	/**
	 * 연락처가 타 회원들과 중복하지 않고 사용가능한지 여부 점검
	 * 
	 * usage) 회원 가입  연락처 중복 점검
	 * 
	 * @param phone 연락처
	 * @return 연락처 사용가능 여부
	 * @throws Exception 예외처리
	 */
	boolean isEnablePhone(String phone) throws Exception;	
	
	/**
	  * 특정 필드(이름, 아이디, 주소 등등)를 이용한 검색 조회 : 페이징 지원
	* 
	* @param fld 검색할 필드
	* @param value 필드 값
	* @param isLike 유사 검색(like) 여부  usage) 유사 검색 : true, 동등 검색 : false  
	* @param page 현재 페이지
	* @param limit 한번에 출력할 수 있는 회원정보
	* @return 검색 결과(회원 정보들)
	* @throws Exception 예외처리
	*/
	List<MemberVO> getMembersByFieldAndPaging(String fld, 
											  Object value, 
											  boolean isLike, 
											  int page,
											  int limit) throws Exception;
	
	/**
	 * 연락처가 타 회원들과 중복하지 않고 사용가능한지 여부 점검
	 * 
	 * usage) 회원 수정  연락처 중복 점검
	 * 
	 * @param memberId 회원 아이디
	 * @param phone 연락처
	 * @return 연락처 사용가능 여부
	 * @throws Exception 예외처리
	 */
	boolean isEnablePhone(String memberId, String phone) throws Exception;
	
	/**
	 * 아이디 분실시 이름, 이메일, 연락처로 아이디 검색
	 * 
	 * @param memberName 회원 이름
	 * @param email 이메일
	 * @param phone 연락처
	 * @return 회원 아이디 혹은 메시지
	 * @throws Exception 예외처리
	 */
	String getMemberIdByMemberInfo(String memberName, String email, String phone) throws Exception;
	
	/**
	 * 패쓰워드 분실시 아이디, 이름, 이메일, 연락처로 패쓰워드 검색
	 * 
	 * @param memberId 회원 아이디
	 * @param memberName 회원 이름
	 * @param email 이메일
	 * @param phone 연락처
	 * @return 회원 아이디 혹은 메시지
	 * @throws Exception 예외처리
	 */
	String getMemberPwByMemberInfo(String memberId, String memberName, String email, String phone) throws Exception;

	
	/**
	 * 회원 롤(role)정보 가져오기
	 * 
	 * @param memberId 회원 아이디
	 * @return 회원 롤(role)
	 * @throws Exception 예외처리
	 */
	String getRoleByMemberId(String memberId) throws Exception;
	
	/**
	 * 회원 롤(Role)정보 삭제
	 * 
	 * @param memberId 회원 아이디
	 * @throws Exception 예외처리
	 */
	void deleteRoleByMemberId(String memberId) throws Exception;
	
	/**
	 * 회원 롤(Role)정보 생성
	 * 
	 * @param roleVO 회원 Role 객체
	 * @throws Exception 예외처리
	 */
	void insertRole(RoleVO roleVO) throws Exception;
	
	/**
	 * 회원 롤(Role)정보 수정
	 * 
	 * @param roleVO 회원 Role 객체
	 * @throws Exception 예외처리
	 */
	void updateRole(RoleVO roleVO) throws Exception;
	
	/**
	 * 페이징을 활용하여 일정한 수의 회원들의 등급(Role) 정보만을 화면에 출력
	 * 
	 * @param page 현재 페이지
	 * @param limit 한번에 출력할 수 있는 회원 등급(Role) 정보
	 * @return 회원 등급(Role) 정보들
	 * @throws Exception 예외처리
	 */
	List<RoleVO> getRolesByPaging(int page, int limit) throws Exception;
	
	/**
	 * 전체 회원 등급 정보 수를 구함
	 * 
	 * @return 전체 회원등듭 정보수
	 * @throws Exception 예외처리
	 */
	int getRolesNumber() throws Exception;
}