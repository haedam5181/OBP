package com.office.calender.board.page;

public class Criteria {

	private int pageNum;		// 현재 페이지
	private int amount;			// 한페이지당 출력되는 아이템 수
	private int skip;			// skip 할 아이템 수
	
	public Criteria() {
		this(1, 20);
		this.skip = 0;
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skip = (pageNum - 1) * amount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}
	
	@Override
	public String toString() {
		return "Criteria [pageNum: " + this.pageNum + ", amount: " + this.amount + ", skip: " + this.skip + "]";
	}
	
	
	
}
