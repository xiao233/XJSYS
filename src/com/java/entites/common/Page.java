package com.java.entites.common;
/**
 * 分页信息
 * @author xjl
 * 2019-01-28 10:40:46
 */
public class Page {
	/**
	 * 每页大小
	 */
	private int pageSize=10;
	
	/**
	 * 当前页,从1开始
	 */
	private int pageCurr;
	
	/**
	 * 总的页数
	 */
	private int pageTotal;
	
	
	/**
	 * 总的数据条数
	 */
	private int dataTotal=-1;
	
	/**
	 * 数据库查询起始
	 */
	private int dataStart;
	
	public Page() {
		
	}
	

	public Page(int pageSize, int pageCurr) {
		super();
		if(pageSize<=0) {
			pageSize = 10;
		}
		if(pageCurr<=0) {
			pageSize = 1;
		}
		this.pageSize = pageSize;
		this.pageCurr = pageCurr;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		if(pageSize<=0) {
			pageSize = 10;
		}
		
		this.pageSize = pageSize;
	}


	public int getPageCurr() {
		if(pageCurr<=0) {
			pageCurr = 1;
		}
		return pageCurr;
	}


	public void setPageCurr(int pageCurr) {
		if(pageCurr<=0) {
			pageSize = 1;
		}
		this.pageCurr = pageCurr;
	}


	public int getPageTotal() {
		if(this.dataTotal>0) {
			pageTotal = (int) Math.ceil(dataTotal/pageSize);
		}
		return pageTotal;
	}


	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}


	public int getDataTotal() {
		return dataTotal;
	}


	public void setDataTotal(int dataTotal) {
		this.dataTotal = dataTotal;
	}


	public int getDataStart() {
		dataStart = (getPageCurr()-1)*getPageSize();
		return dataStart;
	}


	public void setDataStart(int dataStart) {
		this.dataStart = dataStart;
	}

}
