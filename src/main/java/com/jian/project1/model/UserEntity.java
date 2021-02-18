package com.jian.project1.model;

public class UserEntity {
	private int userPk;
	private String userEmail;
	private String userPw;
	private String salt;
	private String userNm;
	private String userPn;
	private String profileImg;
	private String regDt;
	private String searchInfo;
	private int redingVolume;
	
	public int getUserPk() {
		return userPk;
	}
	public void setUserPk(int userPk) {
		this.userPk = userPk;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserPn() {
		return userPn;
	}
	public void setUserPn(String userPn) {
		this.userPn = userPn;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getSearchInfo() {
		return searchInfo;
	}
	public void setSearchInfo(String searchInfo) {
		this.searchInfo = searchInfo;
	}
	public int getRedingVolume() {
		return redingVolume;
	}
	public void setRedingVolume(int redingVolume) {
		this.redingVolume = redingVolume;
	}
}
