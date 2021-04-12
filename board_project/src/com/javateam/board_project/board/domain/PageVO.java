/**
 * 
 */
package com.javateam.board_project.board.domain;

/**
 * 페이지 객체(VO)
 * 
 * @author javateam
 *
 */
public class PageVO {
	
	private int page; // 현재 페이지
	private int maxPage; // 총 페이지 수
	private int startPage; // 시작 페이지
	private int endPage; // 끝 페이지
	private int listCount; // (페이지당) 총 페이지 수
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getMaxPage() {
		return maxPage;
	}
	
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public int getListCount() {
		return listCount;
	}
	
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

}