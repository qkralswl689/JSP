package com.javateam.jdbc.member.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 예외처리 유틸리티(Utility)<br><br>
 *
 * 사용예시) DAO CRUD 메서드 도입부에 아래와 같은 객체 생성     
 *     
 *  ExceptionMetadata emd
 *     = new ExceptionMetadata(new Exception().getStackTrace()[0]);
 * <br><br> 
 * ....
 * <br><br> 
 * 
 * 예외처리 "catch" 구문 내부에 아래와 같이 처리<br><br>
 * 
 * 1) 실제로 트랜잭션이 의미가 있는 순수 DML(insert, update, delete)일 경우<br><br>
 *
 * emd.printErr(e, con, true); 
 * // 트랜젹션 취소 여부(트랜잭션을 취소할 수 있다는 것은 트랜잭션을 사용한다는 의미)
 *
 * 2) 실제로 트랜잭션 취소가 의미가 없는 DQL(select)일 경우<br><br>
 *
 * emd.printErr(e, con, true); 
 *
 * @author javateam
 */
public class ExceptionMetadata {
	
	private StackTraceElement ste; // 예외처리 리플렉션(reflection) 정보객체
	private String className; // 클래스명
	private String methodName; // 메서드명
	
	/**
	 * @param ste 예외처리 리플렉션(reflection) 정보객체
	 */
	public ExceptionMetadata(StackTraceElement ste) {
		this.ste = ste;
		this.className = ste.getClassName();
		this.methodName = ste.getMethodName();
	}
		
	public StackTraceElement getSte() {
		return ste;
	}
	
	public void setSte(StackTraceElement ste) {
		this.ste = ste;
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
	/**
	 * 예외처리 메시지 출력<br><br>
	 * 
	 * @param e 예외처리 객체
	 * @param con DB 연결 객체
	 * @param isRollback rollback(트랜잭션 취소) 여부
	 */
	public void printErr(Exception e, Connection con, boolean isRollback) {
		
		String temp[] = className.split("\\.");
		System.out.print(temp[temp.length-1]+"."+methodName + " ");
		System.out.println(e.getClass().getName().split("\\.")[2]+ " :\n");
		e.printStackTrace();
		
		// rollback
		if (isRollback == true) {
			try {
					con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} //
		}
		
	} //

}