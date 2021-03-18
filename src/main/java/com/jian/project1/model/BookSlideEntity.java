package com.jian.project1.model;

public class BookSlideEntity {
	
	private int bookPk;
	private String bookTitle;
	private String communityInfo;
	private String bookImg;
	
	public int getBookPk() {
		return bookPk;
	}
	public void setBookPk(int bookPk) {
		this.bookPk = bookPk;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getCommunityInfo() {
		return communityInfo;
	}
	public void setCommunityInfo(String communityInfo) {
		this.communityInfo = communityInfo;
	}
	public String getBookImg() {
		return bookImg;
	}
	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}
	
}
