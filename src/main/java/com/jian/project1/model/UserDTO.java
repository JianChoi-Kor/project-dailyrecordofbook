package com.jian.project1.model;

public class UserDTO extends UserEntity{
	private String newUserPw;
	private String newUserPwRe;
	
	public String getNewUserPw() {
		return newUserPw;
	}
	public void setNewUserPw(String newUserPw) {
		this.newUserPw = newUserPw;
	}
	public String getNewUserPwRe() {
		return newUserPwRe;
	}
	public void setNewUserPwRe(String newUserPwRe) {
		this.newUserPwRe = newUserPwRe;
	}
}
