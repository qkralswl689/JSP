package com.newlecture.web.entity;

import java.util.Date;

public class Notice {
	private int id ;
	private String title ;
	private Date regdate;
	private String writerId;
	private String hit;
	private String files;
	private String content;
	private boolean pub;
	
	// 기본 생성자 추가
	public Notice() {}
	
	public int getId() {
		return id;
	}
	
	// 생성자 
	public Notice(int id, String title, Date regdate, String writerId, String hit, String files, String content,
			boolean pub) {
		super();
		this.id = id;
		this.title = title;
		this.regdate = regdate;
		this.writerId = writerId;
		this.hit = hit;
		this.files = files;
		this.content = content;
		this.pub = pub;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean getPub() {
		return pub;
	}




	public void setPub(boolean pub) {
		this.pub = pub;
	}

	@Override
	public String toString() {
		return "id=" + id + ", title=" + title + ", regdate=" + regdate + ", writerId=" + writerId + ", hit=" + hit
				+ ", files=" + files + ", content=" + content + ", pub=" + pub + "]";
	}

}
