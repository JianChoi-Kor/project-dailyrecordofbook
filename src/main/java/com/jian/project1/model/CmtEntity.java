package com.jian.project1.model;

public class CmtEntity {
	private int cmtBoardPk;
	private int cmtSeq;
	private int writerPk;
	private String cmtContent;
	private String cmtRegDt;
	private String cmtModDt;
	private int cmtIsDel;
	
	
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
	public int getWriterPk() {
		return writerPk;
	}
	public void setWriterPk(int writerPk) {
		this.writerPk = writerPk;
	}
	public String getCmtContent() {
		return cmtContent;
	}
	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}
	public String getCmtRegDt() {
		return cmtRegDt;
	}
	public void setCmtRegDt(String cmtRegDt) {
		this.cmtRegDt = cmtRegDt;
	}
	public String getCmtModDt() {
		return cmtModDt;
	}
	public void setCmtModDt(String cmtModDt) {
		this.cmtModDt = cmtModDt;
	}
	public int getCmtIsDel() {
		return cmtIsDel;
	}
	public void setCmtIsDel(int cmtIsDel) {
		this.cmtIsDel = cmtIsDel;
	}

}
