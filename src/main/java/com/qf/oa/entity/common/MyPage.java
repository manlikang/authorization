package com.qf.oa.entity.common;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

public class MyPage <T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pageCount;
	private int currentPage = 1;
	private List<T> list;
	private int total;
	private int pageSize = 4;
	private String url;
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public MyPage(List<T> list) {
		Page<T> page = (Page<T>) list;
		this.currentPage = page.getPageNum();
		this.pageCount = page.getPages();
		this.pageSize = page.getPageSize();
		this.total = (int) page.getTotal();
		this.list = page.getResult();
		// TODO Auto-generated constructor stub
	}
	public MyPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
