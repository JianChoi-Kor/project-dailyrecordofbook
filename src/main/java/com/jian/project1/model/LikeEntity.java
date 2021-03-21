package com.jian.project1.model;

public class LikeEntity {
	
	private int likePk;
	private int cmtBoardPk;
	private int cmtSeq;
	private int userPk;
	
	
	public int getLikePk() {
		return likePk;
	}
	public void setLikePk(int likePk) {
		this.likePk = likePk;
	}
	public int getCmtBoardPk() {
		return cmtBoardPk;
	}
	public void setCmtBoardPk(int cmtBoardPk) {
		this.cmtBoardPk = cmtBoardPk;
	}
	public int getCmtSeq() {
		return cmtSeq;
	}
	public void setCmtSeq(int cmtSeq) {
		this.cmtSeq = cmtSeq;
	}
	public int getUserPk() {
		return userPk;
	}
	public void setUserPk(int userPk) {
		this.userPk = userPk;
	}
	
}
