package com.jian.project1.model;

public class BoardPagingVO {
	
	// 카테고리 get으로 받아올 것
	private int category;
	
	// 현재 페이지 get으로 받아올 것
	private int curPage;
	
	// 블럭당 페이지 갯수 미리 저장해 놓을 것(5)
	private int pageCountPerBlock;
	
	// 한 페이지당 게시글 갯수 (5)
	private int itemCountPerPage;
	
	// 총 게시글 갯수 (저장)
	private int totalCountOfItem;
	
	
	// ------------------------------- //
	
	
	// 현재 페이지 블럭
	private int curPageBlock;
	
	// 블럭 안에서의 시작 페이지
	private int startPageInCurBlock;
	
	// 블럭 안에서의 마지막 페이지
	private int endPageInCurBlock;
	
	// 총 마지막 페이지
	private int lastPage;
	
	
	
	// 쿼리에서 사용할 start, end
	private int start;
	private int end;
	
	
	
	
	// 생성자 메서드
	public BoardPagingVO() {
		
	}

	public BoardPagingVO(int category, int totalCountOfItem, int curPage, int itemCountPerPage, int pageCountPerBlock) {
		// 현재 페이지는 get 방식으로 받아오고 아래 두 값은 직접 넣어준다.
		// 그 값들로 나머지 값들 계산
		setCategory(category);
		setCurPage(curPage);
		setTotalCountOfItem(totalCountOfItem);
		setItemCountPerPage(itemCountPerPage);
		setPageCountPerBlock(pageCountPerBlock);
		
		// 메서드 선언
		// 밑에서 직접 구현
		clacLastPage(getTotalCountOfItem(), getItemCountPerPage());
		clacCurPageBlock(getCurPage(), getPageCountPerBlock());
		clacStartPageInCurBlock(getCurPageBlock(), getPageCountPerBlock());
		clacEndPageInCurBlock(getCurPageBlock(), getPageCountPerBlock());
		calcStartEndPage(getCurPage(),getItemCountPerPage());
		
		chkEndPageInCurBlock(getEndPageInCurBlock(), getLastPage());
	}
	
	
	public void clacLastPage(int totalCountOfItem, int itemCountPerPage) {
		setLastPage((int)Math.ceil((double)totalCountOfItem / (double)itemCountPerPage));
	}
	
	
	public void clacCurPageBlock(int curPage, int pageCountPerBlock) {
		setCurPageBlock((int)Math.ceil(((double)curPage / (double)pageCountPerBlock)));
	}
	
	
	public void clacStartPageInCurBlock(int curPageBlock, int pageCountPerBlock) {
		setStartPageInCurBlock((curPageBlock-1) * pageCountPerBlock + 1);
	}
	
	
	public void clacEndPageInCurBlock(int curPageBlock, int pageCountPerBlock) {
		setEndPageInCurBlock(curPageBlock * pageCountPerBlock);
	}
	
	
	public void calcStartEndPage(int curPage, int itemCountPerPage) {
		setEnd(curPage * itemCountPerPage);
		setStart(getEnd() - itemCountPerPage);
	}
	
	
	public void chkEndPageInCurBlock(int endPageInCurBlock, int lastPage) {
		if(endPageInCurBlock > lastPage) {
			setEndPageInCurBlock(lastPage);
		}
	}
	
	// ------------------------------------------ //
	
	
	public int getCurPage() {
		return curPage;
	}


	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}


	public int getPageCountPerBlock() {
		return pageCountPerBlock;
	}


	public void setPageCountPerBlock(int pageCountPerBlock) {
		this.pageCountPerBlock = pageCountPerBlock;
	}


	public int getItemCountPerPage() {
		return itemCountPerPage;
	}


	public void setItemCountPerPage(int itemCountPerPage) {
		this.itemCountPerPage = itemCountPerPage;
	}


	public int getTotalCountOfItem() {
		return totalCountOfItem;
	}


	public void setTotalCountOfItem(int totalCountOfItem) {
		this.totalCountOfItem = totalCountOfItem;
	}


	public int getCurPageBlock() {
		return curPageBlock;
	}


	public void setCurPageBlock(int curPageBlock) {
		this.curPageBlock = curPageBlock;
	}


	public int getStartPageInCurBlock() {
		return startPageInCurBlock;
	}


	public void setStartPageInCurBlock(int startPageInCurBlock) {
		this.startPageInCurBlock = startPageInCurBlock;
	}


	public int getEndPageInCurBlock() {
		return endPageInCurBlock;
	}


	public void setEndPageInCurBlock(int endPageInCurBlock) {
		this.endPageInCurBlock = endPageInCurBlock;
	}


	public int getLastPage() {
		return lastPage;
	}


	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
	
	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	
	@Override
	public String toString() {
		return "BoardPagingVO [category=" + category + ", curPage=" + curPage + ", pageCountPerBlock="
				+ pageCountPerBlock + ", itemCountPerPage=" + itemCountPerPage + ", totalCountOfItem="
				+ totalCountOfItem + ", curPageBlock=" + curPageBlock + ", startPageInCurBlock="
				+ startPageInCurBlock + ", endPageInCurBlock=" + endPageInCurBlock + ", lastPage="
				+ lastPage + ", start=" + start + ", end=" + end + "]";
	}

	
	
	
	
}
