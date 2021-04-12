package com.javateam.board_project.board.domain;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * @author javateam
 *
 */
public class BoardVO {
	
	private int boardNum; // 글 번호
	private String boardName; // 글 작성자
	private String boardPass; // 글 비밀번호
	private String boardSubject; // 글 제목
	private String boardContent; // 글 내용
	private String boardFile; // 첨부 파일
	private int boardReRef; // 관련글 번호
	private int boardReLev; // 답글 레벨
	private int boardReSeq; // 관련글 중 출력 순서
	private int boardReadCount = 0; // 조회수
	private Date boardDate; // 작성일
	
	public BoardVO() {}
	
	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getBoardPass() {
		return boardPass;
	}

	public void setBoardPass(String boardPass) {
		this.boardPass = boardPass;
	}

	public String getBoardSubject() {
		return boardSubject;
	}

	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardFile() {
		return boardFile;
	}

	public void setBoardFile(String boardFile) {
		this.boardFile = boardFile;
	}

	public int getBoardReRef() {
		return boardReRef;
	}

	public void setBoardReRef(int boardReRef) {
		this.boardReRef = boardReRef;
	}

	public int getBoardReLev() {
		return boardReLev;
	}

	public void setBoardReLev(int boardReLev) {
		this.boardReLev = boardReLev;
	}

	public int getBoardReSeq() {
		return boardReSeq;
	}

	public void setBoardReSeq(int boardReSeq) {
		this.boardReSeq = boardReSeq;
	}

	public int getBoardReadCount() {
		return boardReadCount;
	}

	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	@Override
	public String toString() {
		return String.format(
				"BoardVO [boardNum=%s, boardName=%s, boardPass=%s, boardSubject=%s, boardContent=%s, boardFile=%s, boardReRef=%s, boardReLev=%s, boardReSeq=%s, boardReadCount=%s, boardDate=%s]",
				boardNum, boardName, boardPass, boardSubject, boardContent, boardFile, boardReRef, boardReLev,
				boardReSeq, boardReadCount, boardDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(boardContent, boardDate, boardFile, boardName, boardNum, boardPass, boardReLev, boardReRef,
				boardReSeq, boardReadCount, boardSubject);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BoardVO))
			return false;
		BoardVO other = (BoardVO) obj;
		return Objects.equals(boardContent, other.boardContent) && Objects.equals(boardDate, other.boardDate)
				&& Objects.equals(boardFile, other.boardFile) && Objects.equals(boardName, other.boardName)
				&& boardNum == other.boardNum && Objects.equals(boardPass, other.boardPass)
				&& boardReLev == other.boardReLev && boardReRef == other.boardReRef && boardReSeq == other.boardReSeq
				&& boardReadCount == other.boardReadCount && Objects.equals(boardSubject, other.boardSubject);
	}

	// 날짜 시분초 훼손 방지를 위한 조치
	public void setBoardDateByString(String boardDate) throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-M-d hh:mm:ss"); 
		this.boardDate = new Date(dt.parse(boardDate).getTime());
	}	
	
}