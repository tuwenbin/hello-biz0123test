package com.biz.std.vo;

import java.util.List;

public class PageStu {
	//Dao可以查出来
	private List records;//存放分页记录
	//界面可以传过来
	private int currentPageNum;//当前页码
	//DAO可以查出来
	private int totalRecords;//总记录数
	private int pageSize = 5;//每页显示的条数
	//算出来：根据总记录条数来算
	private int totalPage;//总页数
	//算出来：根据当前页码来算
	private int startIndex;//每页开始的记录的索引

	//记录显示的页码
	private int startPage;
	private int endPage;

	public PageStu(int currentPageNum, int totalRecords){
		this.currentPageNum = currentPageNum;
		this.totalRecords = totalRecords;

		//计算总页数
		totalPage = totalRecords%pageSize == 0 ? totalRecords/pageSize:(totalRecords/pageSize+1);
		//计算开始记录的索引
		startIndex = (currentPageNum - 1)*pageSize;

		//计算开始和结束页码
		if(totalPage <= 9){
			startPage = 1;
			endPage = totalPage;
		}else{
			startPage = currentPageNum - 4;
			endPage = currentPageNum + 4;
			if(startPage < 1){
				startPage = 1;
				endPage = startPage + 8;
			}
			if(endPage > totalPage){
				endPage = totalPage;
				startPage = endPage - 8;
			}
		}
	}

	public List getRecords() {
		return records;
	}

	public void setRecords(List records) {
		this.records = records;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}


}
