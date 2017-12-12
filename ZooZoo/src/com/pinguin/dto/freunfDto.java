package com.pinguin.dto;

public class freunfDto {
	int uDate;
	String tDate, name, content, profileImg, tag;

	public freunfDto(int uDate, String tDate, String name, String content, String profileImg, String tag) {
		super();
		this.uDate = uDate;
		this.tDate = tDate;
		this.name = name;
		this.content = content;
		this.profileImg = profileImg;
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "freunfDto [uDate=" + uDate + ", tDate=" + tDate + ", name=" + name + ", content=" + content
				+ ", profileImg=" + profileImg + "]";
	}
	
	public String getId() {
		return this.tag.substring(0, tag.lastIndexOf("."));
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
