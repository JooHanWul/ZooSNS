package com.pinguin.dto;

public class replyDto {
	int uDate;
	String tDate, name, content, profileImg, id;

	public replyDto(int uDate, String tDate, String name, String content, String profileImg, String id) {
		super();
		this.uDate = uDate;
		this.tDate = tDate;
		this.name = name;
		this.content = content;
		this.profileImg = profileImg;
		this.id= id;
	}

	@Override
	public String toString() {
		return "replyDto [uDate=" + uDate + ", tDate=" + tDate + ", name=" + name + ", content=" + content
				+ ", profileImg=" + profileImg + ", id=" + id + "]";
	}

	public int getuDate() {
		return uDate;
	}

	public void setuDate(int uDate) {
		this.uDate = uDate;
	}

	public String gettDate() {
		return tDate;
	}

	public void settDate(String tDate) {
		this.tDate = tDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
